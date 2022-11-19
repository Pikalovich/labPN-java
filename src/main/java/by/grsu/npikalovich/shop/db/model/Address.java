package by.grsu.npikalovich.shop.db.model;

public class Address {
	private Integer id ;
    private String town;
    private Integer house;
    private Integer flat;
    private String street ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public Integer getHouse() {
		return house;
	}
	public void setHouse(Integer house) {
		this.house = house;
	}
	public Integer getFlat() {
		return flat;
	}
	public void setFlat(Integer flat) {
		this.flat = flat;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Override
	public String toString() {
		return "adress [id=" + id + ", town=" + town + ", house=" + house + ", flat=" + flat + ", street=" + street
				+ "]";
	}
    
    
    
}
