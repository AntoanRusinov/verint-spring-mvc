package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String sayHello() throws Exception {
		return "Hello!";
	}

	@RequestMapping(value = "/hi{name}", method = RequestMethod.GET)
	public String sayHelloToCaller(@RequestParam(value = "name", required = true) String name) {
		return "Hello " + name;
	}

}