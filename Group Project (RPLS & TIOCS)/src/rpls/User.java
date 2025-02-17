/*Authors:
 		Olivia McFarlane- 2301555
 		Kemone Laws- 2109446
 		Javone-Anthony Gordon
 		Diwani Walters*/
package rpls;

public abstract class User {
	protected Name fullName;
	protected String trn;
	protected String role;
	
	public User(Name fullName, String trn, String role) { //primary constructor
		this.fullName = fullName;
		this.trn = trn;
		this.role = role;
	}
	
	public User() { //default constructor
		this.fullName = new Name();
		this.trn = "000-000-000";
		this.role = "No_Role";
	}
	
	public User(User obj) { //copy constructor
		this.fullName = obj.fullName;
		this.trn = obj.trn;
		this.role = obj.role;
	}

	public Name getFullName() {
		return fullName;
	}

	//getters
	public String getTrn() {
		return trn;
	}

	public String getRole() {
		return role;
	}

	//setters
	public void setFullName(Name fullName) {
		this.fullName = new Name(fullName);
	}

	public void setTrn(String trn) {
		this.trn = trn;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() { //Displays the values held by the attributes when called in the format below
		return "User's Name: " + fullName + "\nUser's TRN: " + trn + "\nUser's Role: " + role;
	}	
	
	public abstract boolean log(); //abstract method log which as a result makes the class abstract
	
}
