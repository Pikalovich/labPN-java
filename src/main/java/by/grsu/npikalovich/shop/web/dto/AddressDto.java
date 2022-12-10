package by.grsu.npikalovich.shop.web.dto;

import java.sql.Timestamp;

public class AddressDto {
	private Integer id;
	private String town;
	private Integer house;
	private Integer flat;
	private String street;
	private String name;

	public Integer getId() {
		return id;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
