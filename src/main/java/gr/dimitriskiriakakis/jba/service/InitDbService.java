package gr.dimitriskiriakakis.jba.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import gr.dimitriskiriakakis.jba.entity.Blog;
import gr.dimitriskiriakakis.jba.entity.Item;
import gr.dimitriskiriakakis.jba.entity.Role;
import gr.dimitriskiriakakis.jba.entity.User;
import gr.dimitriskiriakakis.jba.repository.BlogRepository;
import gr.dimitriskiriakakis.jba.repository.ItemRepository;
import gr.dimitriskiriakakis.jba.repository.RoleRepository;
import gr.dimitriskiriakakis.jba.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class InitDbService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@PostConstruct
	public void init(){
		
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		Blog mitsos = new Blog();
		mitsos.setName("mitsos");
		mitsos.setUrl("http://www.diaktinismos.gr");
		mitsos.setUser(userAdmin);
		blogRepository.save(mitsos);
		
		Item item1 = new Item();
		item1.setBlog(mitsos);
		item1.setTitle("first");
		item1.setLink("http://www.diaktinismos.gr");
		item1.setPublishedDate(new Date());
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setBlog(mitsos);
		item2.setTitle("second");
		item2.setLink("http://www.diaktinismos.gr");
		item2.setPublishedDate(new Date());
		itemRepository.save(item2);
		
		
		
		
		
		
		
		
	}
	

}
