package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	// TODO
	
}