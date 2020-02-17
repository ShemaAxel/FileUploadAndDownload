package file.demo.controller;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import file.demo.model.ApplicationUser;
import file.demo.repository.ApplicationUserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController(ApplicationUserRepository applicationUserRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		logger.info("User Controller initialize constructor.");
		this.applicationUserRepository = applicationUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/sign-up")
	public void signUp(@RequestBody ApplicationUser user) {
		
		logger.info("Hitting the sign-up endpoint.");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		applicationUserRepository.save(user);
	}
	
}
