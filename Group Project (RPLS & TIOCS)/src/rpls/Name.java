/*Authors:
 		Olivia McFarlane- 2301555
 		Kemone Laws- 2109446
 		Javone-Anthony Gordon
 		Diwani Walters*/
package rpls;

public class Name {

	private String firstName;
	private String middleName;
	private String lastName;
	
	
	//primary constructor
	public Name(String firstName, String middleName, String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	//default constructor
	public Name() { 
		this.firstName = "No_First_Name";
		this.middleName = "No_Middle_Name";
		this.lastName = "No_Last_Name";
	}
	
	//copy consructor
	public Name(Name obj) {
		this.firstName = obj.firstName;
		this.middleName = obj.middleName;
		this.lastName = obj.lastName;
	}
	
	//getters	
	public String getFirstName() {
		return firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	//setters
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
		
	@Override
	public String toString() {//displays the values stored in these attributes by objects of this class
		return firstName + " " + middleName + " " + lastName;
	}
}
