package fitnessstudio.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // required for XML binding
public class Product {

	public String id;

	public String title;

	public String description;

	public Integer currentPrice;

}
