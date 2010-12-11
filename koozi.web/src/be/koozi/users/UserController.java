package be.koozi.users;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import be.koozi.gae.security.GaeUserAuthentication;
import be.koozi.tools.MarshallingViewKey;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class UserController {

	private UserDao userDao;
	private UserRoleDao userRoleDao;
	private UserService userService;

	private final static String ADMIN = "tola2000@gmail.com";

	public UserController() {
	}

	@Autowired
	public UserController(UserDao userDao, UserRoleDao userRoleDao, UserService userService) {
		this.userDao = userDao;
		this.userRoleDao = userRoleDao;
		this.userService = userService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap model) {

		return "home";
	}

	@RolesAllowed("ROLE_NEW_USER")
	@RequestMapping(value = "/users/new", method = RequestMethod.GET)
	public String registrationForm(ModelMap model) {
		model.addAttribute(new RegistrationForm());
		return "users/register";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public String findUser(ModelMap model, @PathVariable("userId") String userId, @RequestParam(value = "view", required = false) String view) {
		model.addAttribute("user", userDao.find(userId));
		if (view == null)
			return "users/user";
		else
			return "users/user." + view;

	}

	@RolesAllowed("ROLE_USER")
	@MarshallingViewKey("user")
	@RequestMapping(value = "/users/currentuser", method = RequestMethod.GET)
	public String findUser(ModelMap model, @RequestParam(value = "view", required = false) String view) {
		com.google.appengine.api.users.User gaeUser = userService.getCurrentUser();
		User user = userDao.find(gaeUser.getUserId());
		model.addAttribute("user", user);
		model.addAttribute("isCurrentuser", true);
		if (view == null)
			return "users/currentUser";
		else
			return "users/currentUser." + view;

	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String findUsers(ModelMap model, @RequestParam(value = "view", required = false) String view) {
		model.addAttribute("userList", userDao.findAll());
		if (view == null)
			return "users/users";
		else
			return "users/users." + view;

	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	public String deleteUser(ModelMap model, @PathVariable("userId") String userId) {
		userDao.delete(userId);
		return "redirect:/users";
	}

	@RolesAllowed("ROLE_USER")
	@RequestMapping(value = "/users/currentUser", method = RequestMethod.DELETE)
	public String deleteCurrentUser(ModelMap model) {
		com.google.appengine.api.users.User user = userService.getCurrentUser();
		userDao.delete(user.getUserId());
		return "redirect:/";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
	public String updateUser(ModelMap model, @PathVariable("userId") String userId, @RequestParam("forename") String forename, @RequestParam("surname") String surname) {
		User user = userDao.find(userId);
		user.setForename(forename);
		user.setSurname(surname);
		userDao.update(user);
		return "redirect:/users/" + user.getUserId();
	}

	@RolesAllowed("ROLE_USER")
	@RequestMapping(value = "/users/currentuser", method = RequestMethod.PUT)
	public String updateUser(ModelMap model, @RequestParam("forename") String forename, @RequestParam("surname") String surname) {
		com.google.appengine.api.users.User gaeUser = userService.getCurrentUser();

		User user = userDao.find(gaeUser.getUserId());
		user.setForename(forename);
		user.setSurname(surname);
		userDao.update(user);
		return "redirect:/users/currentuser";
	}

	@RolesAllowed("ROLE_NEW_USER")
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String register(RegistrationForm form, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();

		User user = new User(currentUser.getUserId(), currentUser.getNickname(), currentUser.getEmail(), form.getForename(), form.getSurname(), true);

		userDao.create(user);

		if (user.getNickname().equalsIgnoreCase("Tola2000")) {
			UserRole userRole = new UserRole(Role.ROLE_ADMIN, user.getUserId());
			userRoleDao.create(userRole);
		}

		UserRole userRole = new UserRole(Role.ROLE_USER, currentUser.getUserId());
		userRoleDao.create(userRole);

		Collection<GrantedAuthority> userRoles = userRoleDao.findRoles(currentUser.getUserId());

		// Update the context with the full authentication
		SecurityContextHolder.getContext().setAuthentication(new GaeUserAuthentication(user, userRoles, authentication.getDetails()));

		return "redirect:/users/currentuser";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/users/{userId}/roles", method = RequestMethod.POST)
	public String createUserRole(ModelMap model, @RequestParam(value = "view", required = false) String view, @PathVariable("userId") String userId, @RequestParam("role") String roleName) {
		UserRole userRole = new UserRole(Role.get(roleName), userId);
		userRoleDao.create(userRole);
		model.addAttribute("userRole", userRole);
		return "redirect:/users/" + userId;

	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/users/{userId}/roles/{roleId}", method = RequestMethod.DELETE)
	public String deleteUserRole(ModelMap model, @PathVariable("userId") String userId, @PathVariable("roleId") Long roleId) {
		userRoleDao.delete(roleId);
		return "redirect:/users/" + userId;
	}

	@MarshallingViewKey("userRolesList")
	@RequestMapping(value = "/users/{userId}/roles", method = RequestMethod.GET)
	public String findUserRoles(ModelMap model, @PathVariable("userId") String userId, @RequestParam(value = "view", required = false) String view) {
		model.addAttribute("userRolesList", userRoleDao.findByUser(userId));
		model.addAttribute("userId", userId);
		if (view == null)
			return "/users/roles";
		else
			return "/users/roles." + view;

	}

	@RolesAllowed("ROLE_USER")
	@RequestMapping(value = "/users/currentuser/roles", method = RequestMethod.GET)
	public String findCurrentUserRoles(ModelMap model) {
		com.google.appengine.api.users.User gaeUser = userService.getCurrentUser();
		model.addAttribute("userRolesList", userRoleDao.findByUser(gaeUser.getUserId()));
		return "/users/roles";

	}

	@RequestMapping(value = "/users/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String logoutUrl = UserServiceFactory.getUserService().createLogoutURL("/");
		request.getSession().invalidate();
		SecurityContextHolder.getContext().setAuthentication(null);
		response.sendRedirect(logoutUrl);

	}
}
