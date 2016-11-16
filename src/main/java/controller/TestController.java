package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/a")
public class TestController {

	@RequestMapping(method = RequestMethod.GET, value = "/b")
	public String sayHello() {
		return "Hello!";
	}

}