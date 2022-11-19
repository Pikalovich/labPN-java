package by.grsu.npikalovich.shop.db.model;

public class Product {
	private Integer id ;
    private String title;
    private Integer price;
    private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "product [id=" + id + ", title=" + title + ", price=" + price + ", description=" + description + "]";
	}	

    
}
