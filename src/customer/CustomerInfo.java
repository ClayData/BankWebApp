package customer;

public class CustomerInfo {

	private String name;
	private String gender;
	private int salary;
	private String contact;
	
	public CustomerInfo(String name, String gender, int salary, String contact) {
		super();
		this.name = name;
		this.gender = gender;
		this.salary = salary;
		this.contact = contact;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getId() {
//		return id;
//	}
//  Will just be return the sql query
//	public void setId(String id) {
//		this.id = id;
//	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}	
	

}
