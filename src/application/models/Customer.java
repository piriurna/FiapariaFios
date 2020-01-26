package application.models;

public class Customer {

	private int id;
	private String name, cellphone, email, address, cpf, accountNumber;
	
	
	public Customer(int id, String name, String cellphone, String email, String address, String cpf,
			String accountNumber) {
		this.id = id;
		this.name = name;
		this.cellphone = cellphone;
		this.email = email;
		this.address = address;
		this.cpf = cpf;
		this.accountNumber = accountNumber;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	
	public String toString() {
		return getName();
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCellphone() {
		return cellphone;
	}


	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}	
}
