package gr.dimitriskiriakakis.jba.service;

import gr.dimitriskiriakakis.jba.entity.Item;
import gr.dimitriskiriakakis.jba.exception.RssException;
import gr.dimitriskiriakakis.jba.rss.ObjectFactory;
import gr.dimitriskiriakakis.jba.rss.TRss;
import gr.dimitriskiriakakis.jba.rss.TRssChannel;
import gr.dimitriskiriakakis.jba.rss.TRssItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Service;


@Service
public class RssService {

	
	public List<Item> getItems(String url) throws RssException{
		
		//StreamSource(file) available too
		return getItems(new StreamSource(url));
	}
	
	
	private List<Item> getItems(Source src) throws RssException{
		ArrayList<Item> list = new ArrayList<Item>();
		try {
			JAXBContext jaxb =JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unm = jaxb.createUnmarshaller();
			JAXBElement<TRss> jaxbel = unm.unmarshal(src, TRss.class);
			TRss rss = jaxbel.getValue();
			
			List<TRssChannel> channels = rss.getChannel();
			for(TRssChannel trc : channels){
				List<TRssItem> items = trc.getItem();
				for(TRssItem rssItem : items){
					Item item = new Item();
					item.setTitle(rssItem.getTitle());
					item.setDescription(rssItem.getDescription());
					item.setLink(rssItem.getLink());
					Date pubDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse(rssItem.getPubDate());
					item.setPublishedDate(pubDate);
					list.add(item);
				}
				
			}
			
		} catch (JAXBException e) {
			throw new RssException(e);
		} catch (ParseException e) {
			throw new RssException(e);
		}
		return list;
		
	}
}
