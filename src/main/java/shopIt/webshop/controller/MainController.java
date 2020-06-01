package shopIt.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("webapp")
public class MainController {

	@GetMapping("/home")
	public String homepage() {
		return "webapp/home";
	}
	
	
	
	
}
