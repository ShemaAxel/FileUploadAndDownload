package file.demo.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import file.demo.model.ApplicationUser;
import file.demo.repository.ApplicationUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
    private ApplicationUserRepository applicationUserRepository;

    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	LOGGER.info("Findind the user .......");
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
        	LOGGER.error("No user found .........");
            throw new UsernameNotFoundException(username);
        }
    	LOGGER.error("Returning the user and roles assigned......");
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

	private Collection<? extends GrantedAuthority> emptyList() {
		// TODO Auto-generated method stub'
	    ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    authorities.add(new SimpleGrantedAuthority("USER"));
	    authorities.add(new SimpleGrantedAuthority("ADMIN"));
	    
		return authorities;
	}
	
}
