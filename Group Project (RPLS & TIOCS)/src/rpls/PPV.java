/*Authors:
 		Olivia McFarlane- 2301555
 		Kemone Laws- 2109446
 		Javone-Anthony Gordon
 		Diwani Walters*/
package rpls;

public class PPV {
	
	private String licenceNo;
	private Name ownerName;
	private Name driverName;
	private Name pastOwner;
	private Name pastDriver;
	private Date issueDate;
	private Date expDate;
	
	
	public PPV() { //default constructor
		this.licenceNo = "0000AB";
		this.ownerName = new Name();
		this.driverName = new Name();
		this.pastOwner = new Name();
		this.pastDriver = new Name();
		this.issueDate= new Date();
		this.expDate= new Date();
	}
	
	//primary constructor
	public PPV(String licenceNo, Name ownerName, Name driverName, Name pastOwner, Name pastDriver, Date issueDate, Date expDate) {
		this.licenceNo = licenceNo;
		this.ownerName = ownerName;
		this.driverName = driverName;
		this.pastOwner = pastOwner;
		this.pastDriver = pastDriver;
		this.issueDate= issueDate;
		this.expDate= expDate;
	}
	
	public PPV(PPV obj) { //copy constructor
		this.licenceNo = obj.licenceNo;
		this.ownerName = obj.ownerName;
		this.driverName = obj.driverName;
		this.pastOwner = obj.pastOwner;
		this.pastDriver = obj.pastDriver;
		this.issueDate= obj.issueDate;
		this.expDate= obj.expDate;
	}

	//getters
	public String getLicenceNo() {
		return licenceNo;
	}

	public Name getOwnerName() {
		return ownerName;
	}

	public Name getDriverName() {
		return driverName;
	}

	public Name getPastOwner() {
		return pastOwner;
	}

	public Name getPastDriver() {
		return pastDriver;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	//setters
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public void setOwnerName(Name ownerName) {
		this.ownerName = new Name(ownerName);
	}

	public void setDriverName(Name driverName) {
		this.driverName = new Name(driverName);
	}

	public void setPastOwner(Name pastOwner) {
		this.pastOwner = new Name(pastOwner);
	}

	public void setPastDriver(Name pastDriver) {
		this.pastDriver = new Name(pastDriver);
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = new Date(issueDate);
	}

	public void setExpDate(Date expDate) {
		this.expDate = new Date(expDate);
	}

	@Override
	public String toString() {//Displays the values held by the attributes when called in the format below
		return "Private Passenger Information:\nLicence Plate Number: " + licenceNo + "\nOwner Name: " + ownerName + "\nDriver Name: " + driverName
				+ "\nPrevious Owner: " + pastOwner + "\nPrevious Driver: " + pastDriver + "\nBadge Issued Date: " + issueDate + "\nBadge Expiration Date: "
				+ expDate;
	}
	
}
