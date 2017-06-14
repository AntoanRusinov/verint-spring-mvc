package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "getUserById", method = RequestMethod.GET)
	public User getUser(Long userId) {
		return userService.getUserById(userId);
	}

	@RequestMapping(value = "deleteUserById", method = RequestMethod.POST)
	public void deleteUser(Long userId) {
		userService.deleteUser(userId);
	}
	
	// just add and implement other methods
}
