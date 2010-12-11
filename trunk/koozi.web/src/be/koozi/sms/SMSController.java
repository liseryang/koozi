package be.koozi.sms;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.appengine.api.users.UserService;

import be.koozi.product.Product;
import be.koozi.users.User;

@Controller
public class SMSController {

	private SMSService smsService;
	private SMSDao smsDao;
	private SMSResultDao smsResultDao;
	private UserService userService;
	
	public SMSController() {}

	@Autowired
	public SMSController(SMSService smsService, SMSDao smsDao, SMSResultDao smsResultDao,  UserService userService) {
		if (smsService == null)
			throw new RuntimeException("SMSService can not be null.");
		this.smsService = smsService;
		this.smsDao = smsDao;
		this.smsResultDao = smsResultDao;
		this.userService = userService;
	}

	@RolesAllowed("ROLE_SUPER_USER")
	@RequestMapping(value = "/sms/new", method = RequestMethod.GET)
	public String newSms(ModelMap model) {
		return "sms/sms.new";
	}

	@RolesAllowed("ROLE_SUPER_USER")
	@RequestMapping(value = "/sms", method = RequestMethod.POST)
	public String sendSms(ModelMap model, @RequestParam("to") String to, @RequestParam("content") String content) {
		String[] toList = to.split(";");
		for (int i = 0; i < toList.length; i++) {
			com.google.appengine.api.users.User gaeUser = userService.getCurrentUser();
			SMS sms = new SMS(content, new PhoneNumber(toList[i]),gaeUser.getUserId());
			smsDao.create(sms);
			//SMSResult smsResult = smsService.sendSMS(sms);
			SMSResult smsResult = new SMSResult(SMSStatusCode.OK, "SMS is send.", "xxx", sms.getId());
			model.addAttribute("smsResult", smsResult);
			//smsResultDao.create(smsResult);
			sms.setSmsResult(smsResult);
			smsDao.update(sms);
		}
		return "redirect:/sms/currentUser";
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/sms", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findSMS(ModelMap model, @RequestParam(value = "user", required = false) String userId) {
		Collection<SMS> smsList = null;
		
		if("currentUser".equalsIgnoreCase(userId))
		{
			userId =  userService.getCurrentUser().getUserId();
		}
		if(userId != null)
		{
			smsList = smsDao.findByUserId(userId);
		}else
			smsList = smsDao.findAll();
		
		model.addAttribute("smsList", smsList);
		model.addAttribute("user", userService.getCurrentUser());
		return "sms/smses";
	}
	
	@RolesAllowed("ROLE_SUPER_USER")
	@RequestMapping(value = "/sms/currentUser", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findSMSCurrentUser(ModelMap model) {
		Collection<SMS> smsList = null;
		String	userId =  userService.getCurrentUser().getUserId();
		smsList = smsDao.findByUserId(userId);
		
		model.addAttribute("smsList", smsList);
		model.addAttribute("user", userService.getCurrentUser());
		return "sms/smses";
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/sms/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findSms(@PathVariable("id") Long id,  ModelMap model) {
		SMS sms = smsDao.find(id);
		SMSResult smsResult = sms.getSmsResult();
		//SMSResult smsResult = smsResultDao.findBySMS(sms.getId());
		model.addAttribute("sms", sms);
		model.addAttribute("smsResult", smsResult);
		return "sms/sms";
	}
	

}
