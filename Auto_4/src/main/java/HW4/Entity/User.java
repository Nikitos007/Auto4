package HW4.Entity;

import org.apache.commons.lang3.RandomStringUtils;

public class User {

	private String username;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String city;
	private String address;
	private String phone;

	public User() {
		username = RandomStringUtils.randomAlphabetic(10);
		email = RandomStringUtils.randomAlphabetic(10) + "@mail.ru";
		password = RandomStringUtils.randomAlphabetic(10);
		firstName = RandomStringUtils.randomAlphabetic(10);
		lastName = RandomStringUtils.randomAlphabetic(10);
		city = RandomStringUtils.randomAlphabetic(10);
		address = RandomStringUtils.randomAlphabetic(10);
		phone = RandomStringUtils.randomNumeric(10);			
	}
	
	public void newInfoOfUser() {
		email = RandomStringUtils.randomAlphabetic(10) + "@mail.ru";
		firstName = RandomStringUtils.randomAlphabetic(10);
		lastName = RandomStringUtils.randomAlphabetic(10);
		city = RandomStringUtils.randomAlphabetic(10);
		address = RandomStringUtils.randomAlphabetic(10);
		phone = RandomStringUtils.randomNumeric(10);	
	}
	
	public boolean equelsUsers(User user, User user2) {
		if (user.username.equals(user2.username)  && user.email.equals(user2.email)	&& user.firstName.equals(user2.firstName) && user.lastName.equals(user2.lastName)	
				&& user.city.equals(user2.city) && user.address.equals(user2.address) && user.phone.equals(user2.phone)) {
			return true;
		}
		return false;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


}
