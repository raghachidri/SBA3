package com.wellsfargo.fsd.ims.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



public class UserModel {

	@NotNull(message = "User ID is mandatory")
	private Integer userId;
	
	@NotNull(message = "First Name is mandatory")
	@NotBlank(message = "First Name is mandatory")
	@Size(min=5,max=30,message = "First Name is expected to be between 5 to 30 chars in length")
	private String firstName;
	
	@NotNull(message = "Last Name is mandatory")
	@NotBlank(message = "Last Name is mandatory")
	@Size(min=3,max=25,message = "First Name is expected to be between 3 to 25 chars in length")
	private String lastName;
	
	 @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
		        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
		        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
		             message="Invalid Email ID")
	private String email;
	
	@NotNull(message = "Mobile Number is mandatory")
	@Pattern(regexp="\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", message="Please provide a valid mobile number")
	private String mobile;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	

	public UserModel(@NotNull(message = "User ID is mandatory") Integer userId,
			@NotNull(message = "First Name is mandatory") @NotBlank(message = "First Name is mandatory") @Size(min = 5, max = 30, message = "First Name is expected to be between 5 to 30 chars in length") String firstName,
			@NotNull(message = "Last Name is mandatory") @NotBlank(message = "Last Name is mandatory") @Size(min = 3, max = 25, message = "First Name is expected to be between 3 to 25 chars in length") String lastName,
			@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid Email ID") String email,
			@NotNull(message = "Mobile Number is mandatory") @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", message = "Please provide a valid mobile number") String mobile) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", mobile=" + mobile + "]";
	}
	
	public UserModel() {
		//left unimplemented
	}
}
