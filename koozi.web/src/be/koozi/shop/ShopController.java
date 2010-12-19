package be.koozi.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopController {

	@RequestMapping(value = "/", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findHome(ModelMap model) {
		return "/home/page";
	}

	@RequestMapping(value = "/home", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findHome2(ModelMap model) {
		return "/home/page";
	}

	@RequestMapping(value = "/home/{page}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
	public String findProducts(ModelMap model, @PathVariable("page") String page) {
		return "/pages/" + page;
	}
}
