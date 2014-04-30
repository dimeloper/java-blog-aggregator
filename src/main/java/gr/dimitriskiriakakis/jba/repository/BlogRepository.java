package gr.dimitriskiriakakis.jba.repository;

import java.util.List;

import gr.dimitriskiriakakis.jba.entity.Blog;
import gr.dimitriskiriakakis.jba.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByUser(User user);
}
