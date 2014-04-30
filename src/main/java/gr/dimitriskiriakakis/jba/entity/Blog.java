package gr.dimitriskiriakakis.jba.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Entity
public class Blog {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	//Hibernate Validator Annotation
	@URL(message="Invalid URL!")
	@Size(min=1, message="Invalid URL!")
	private String url;
	
	//Hibernate Validator Annotation
	@Size(min=1, message="Name must be at least 1 character!")
	private String name;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	//On Delete Items are Deleted too
	@OneToMany(mappedBy="blog" , cascade=CascadeType.REMOVE)
	private List<Item> items;
	
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
