package com.bilgeadam.boost.java.telephonebook.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty telephone;
	private StringProperty street;
	private StringProperty city;
	private IntegerProperty zip;
	private ObjectProperty<LocalDate> birthDay;
	
	public Person() {
		this.firstName = new SimpleStringProperty();
		
		this.lastName = new SimpleStringProperty();
		
		this.telephone = new SimpleStringProperty();
		
		this.street = new SimpleStringProperty();
		
		this.city = new SimpleStringProperty();
		
		this.zip = new SimpleIntegerProperty();
		
		this.birthDay = new SimpleObjectProperty<LocalDate>();
		
	}
	
	public Person(String firstName, String lastName) {
		this();
		this.firstName.set(firstName);
		
		this.lastName.set(lastName);
	}
	
	private Person(Builder builder) {
		this();
		
		this.firstName.set(builder.firstName);
		
		this.lastName.set(builder.lastName);
		
		this.telephone.set(builder.telephone);
		
		this.street.set(builder.street);
		
		this.city.set(builder.city);
		
		this.zip.set(builder.zip);
		
		this.birthDay.set(builder.birthDay);
	}
	
	@Override
	public String toString() {
		return "Person [getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getTelephone()="
				+ getTelephone() + ", getStreet()=" + getStreet() + ", getCity()=" + getCity() + ", getZip()="
				+ getZip() + ", getBirthDay()=" + getBirthDay() + "]";
	}
	
	public static class Builder {
		private String firstName = "";
		private String lastName = "";
		private String telephone = "";
		private String street = "";
		private String city = "";
		private Integer zip = 0;
		private LocalDate birthDay = LocalDate.MIN;
		
		public Builder firstname(String firstname) {
			this.firstName = firstname;
			
			return this;
		}
		
		public Builder lastname(String lastName) {
			this.lastName = lastName;
			
			return this;
			
		}
		
		public Builder telephone(String telephone) {
			this.telephone = telephone;
			
			return this;
			
		}
		
		public Builder street(String street) {
			this.street = street;
			
			return this;
			
		}
		
		public Builder city(String city) {
			this.city = city;
			
			return this;
			
		}
		
		public Builder zip(Integer zip) {
			this.zip = zip;
			
			return this;
			
		}
		
		public Builder birthDay(LocalDate birthDay) {
			this.birthDay = birthDay;
			
			return this;
			
		}
		
		public Person build() {
			return new Person(this);
			
		}
		
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public String getLastName() {
		return lastName.get();
	}
	
	public String getTelephone() {
		return telephone.get();
	}
	
	public String getStreet() {
		return street.get();
	}
	
	public String getCity() {
		return city.get();
	}
	
	public int getZip() {
		return zip.get();
	}
	
	public LocalDate getBirthDay() {
		return birthDay.get();
	}
	
	public StringProperty getFirstNameProperty() {
		return firstName;
	}
	
	public StringProperty getLastNameProperty() {
		return lastName;
	}
	
	public StringProperty getTelephoneProperty() {
		return telephone;
	}
	
	public StringProperty getStreetProperty() {
		return street;
	}
	
	public StringProperty getCityProperty() {
		return city;
	}
	
	public IntegerProperty getZipProperty() {
		return zip;
	}
	
	public ObjectProperty<LocalDate> getBirthDayProperty() {
		return birthDay;
	}
	
	public void setFirstName(String string) {
		this.firstName.set(string);
		;
	}
	
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public void setTelephone(String telephone) {
		this.telephone.set(telephone);
		;
	}
	
	public void setStreet(String street) {
		this.street.set(street);
		;
	}
	
	public void setCity(String city) {
		this.city.set(city);
	}
	
	public void setZip(Integer zip) {
		this.zip.set(zip);
	}
	
	public void setBirthDay(LocalDate birthday) {
		this.birthDay.set(birthday);
		
	}
	
}
