package gr.dimitriskiriakakis.jba.repository;

import gr.dimitriskiriakakis.jba.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);

}
