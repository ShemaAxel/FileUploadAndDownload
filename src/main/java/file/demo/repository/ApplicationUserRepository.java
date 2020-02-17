package file.demo.repository;

import org.springframework.data.repository.CrudRepository;

import file.demo.model.ApplicationUser;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

	ApplicationUser findByUsername(String username);
}
