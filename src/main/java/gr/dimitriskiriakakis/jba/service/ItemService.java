package gr.dimitriskiriakakis.jba.service;

import gr.dimitriskiriakakis.jba.entity.Item;
import gr.dimitriskiriakakis.jba.repository.ItemRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> getItems(){
		//Return 0st page, 20 results, DESC order by publishedDate
		return itemRepository.findAll(new PageRequest(0, 20, Direction.DESC, "publishedDate")).getContent();
	}

}
