package shopIt.webshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import shopIt.webshop.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	// that's it ... no need to write any code LOL!
	
	// add a method to sort by last name

}
