/*Authors:
 		Olivia McFarlane- 2301555
 		Kemone Laws- 2109446*/
package tiocs;

import rpls.Date;
import rpls.Name;

public class Ticket {
	private int ticketNo; //4
    private String driverTrn; //11 - 22
    private Date issueDueDate; //12 
    private String offenceCode; //2 digits - 4
    private String offenceDescription; //10 char- 20
    private double fineAmount; // 8
    private String licensePlateNo; // 12
    private Name driverFullName; // 78
    private Date driverDob; //12
    private String driverAddress; //80
    private String driverContact; //24
    private String badgeNum; //8
    private Name officerFullName; // 78
    private String officerStation; //20
    
    
    //primary constructor
    public Ticket(int ticketNo, String driverTrn, Date issueDueDate, String offenceCode, String offenceDescription,
                  double fineAmount, String licensePlateNo, Name driverFullName, Date driverDob,
                  String driverAddress, String driverContact, String badgeNum, Name officerFullName,
                  String officerStation) {
        this.ticketNo = ticketNo;
        this.driverTrn = driverTrn;
        this.issueDueDate = issueDueDate;
        this.offenceCode = offenceCode;
        this.offenceDescription = offenceDescription;
        this.fineAmount = fineAmount;
        this.licensePlateNo = licensePlateNo;
        this.driverFullName = driverFullName;
        this.driverDob = driverDob;
        this.driverAddress = driverAddress;
        this.driverContact = driverContact;
        this.badgeNum = badgeNum;
        this.officerFullName = officerFullName;
        this.officerStation = officerStation;
    }
    
    //default constructor
    public Ticket(){
    	this.ticketNo = 0;
        this.driverTrn = "000-000-000";
        this.issueDueDate = new Date();
        this.offenceCode = "00";
        this.offenceDescription = " ";
        this.fineAmount = 0.0;
        this.licensePlateNo = "0000AB";
        this.driverFullName = new Name();
        this.driverDob = new Date();
        this.driverAddress = "streetnum_streetname_city_parish";
        this.driverContact = "000-000-0000";
        this.badgeNum = "0000";
        this.officerFullName = new Name();
        this.officerStation = " ";
    }
    
    //copy constructor
    public Ticket(Ticket ticket) {
        this.ticketNo = ticket.ticketNo;
        this.driverTrn = ticket.driverTrn;
        this.issueDueDate = ticket.issueDueDate;
        this.offenceCode = ticket.offenceCode;
        this.offenceDescription = ticket.offenceDescription;
        this.fineAmount = ticket.fineAmount;
        this.licensePlateNo = ticket.licensePlateNo;
        this.driverFullName = ticket.driverFullName;
        this.driverDob = ticket.driverDob;
        this.driverAddress = ticket.driverAddress;
        this.driverContact = ticket.driverContact;
        this.badgeNum = ticket.badgeNum;
        this.officerFullName = ticket.officerFullName;
        this.officerStation = ticket.officerStation;
    }
    
    //Getters
    public int getTicketNo() {
        return ticketNo;
    }

    public String getDriverTrn() {
        return driverTrn;
    }

    public Date getIssueDueDate() {
        return issueDueDate;
    }

    public String getOffenceCode() {
        return offenceCode;
    }

    public String getOffenceDescription() {
        return offenceDescription;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public String getLicensePlateNo() {
        return licensePlateNo;
    }

    public Name getDriverFullName() {
        return driverFullName;
    }

    public Date getDriverDob() {
        return driverDob;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public String getBadgeNum() {
        return badgeNum;
    }
    
    public Name getOfficerFullName() {
        return officerFullName;
    }
    
    public String getOfficerStation() {
        return officerStation;
    }
    
    //Setters
    public void setTicketNo(int ticketNo) {
        this.ticketNo = ticketNo;
    }

    public void setDriverTrn(String driverTrn) {
        this.driverTrn = driverTrn;
    }

    public void setIssueDueDate(Date issueDueDate) {
        this.issueDueDate = issueDueDate;
    }

    public void setOffenceCode(String offenceCode) {
        this.offenceCode = offenceCode;
    }

    public void setOffenceDescription(String offenceDescription) {
        this.offenceDescription = offenceDescription;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public void setLicensePlateNo(String licensePlateNo) {
        this.licensePlateNo = licensePlateNo;
    }

    public void setDriverFullName(Name driverFullName) {
        this.driverFullName = driverFullName;
    }

    public void setDriverDob(Date driverDob) {
        this.driverDob = driverDob;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }

    public void setBadgeNum(String badgeNum) {
        this.badgeNum = badgeNum;
    }

    public void setOfficerFullName(Name officerFullName) {
        this.officerFullName = officerFullName;
    }

    public void setOfficerStation(String officerStation) {
        this.officerStation = officerStation;
    }

	@Override
	public String toString() { //Displays the values held by the attributes when called in the format below
		return "\nTicket Information\nTicket Number: " + ticketNo + "\nDriver TRN: " + driverTrn + "\nIssue Date: " + issueDueDate
				+ "\nOffence Code: " + offenceCode + "\nOffence Description: " + offenceDescription + "\nFine Amount: $"
				+ fineAmount + "\nLicense Plate Number: " + licensePlateNo + "\nDriver Full Name: " + driverFullName
				+ "\nDriver DOB: " + driverDob + "\nDriver Address: " + driverAddress + "\nDriver Contact: " + driverContact
				+ "\nBadge Number: " + badgeNum + "\nOfficer Full Name: " + officerFullName + "\nOfficer Station: "
				+ officerStation + "\n";
	}
   
}
