/*Authors:
 		Olivia McFarlane- 2301555
 		Kemone Laws- 2109446
 		Javone-Anthony Gordon
 		Diwani Walters*/
package rpls;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.RandomAccessFile;

import tiocs.Ticket;

import java.util.Scanner;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Application {
	private String trn;
	private Name ownerName;
	private Name driverName;
	private Date dob;
	private String address;
	private String email;
	private String contact;
	private int numOfTicketOutstanding;
	private double ticketOwing;
	private String policeRecordReason;
	private String policeDeniedReason;
	
	//primary constructor
	public Application(String trn, Name ownerName,Name driverName, Date dob, String address, String email, String contact,
			int numOfTicketOutstanding, double ticketOwing, String policeRecordReason,
			String policeDeniedReason) {
		this.trn = trn;
		this.ownerName = ownerName;
		this.driverName = driverName;
		this.dob = dob;
		this.address = address;
		this.email = email;
		this.contact = contact;
		this.numOfTicketOutstanding = numOfTicketOutstanding;
		this.ticketOwing = ticketOwing;
		this.policeRecordReason = policeRecordReason;
		this.policeDeniedReason = policeDeniedReason;
	}
	
	public Application() { //default constructor
		this.trn = "000-000-000";
		this.ownerName = new Name();
		this.driverName = new Name();
		this.dob = new Date();
		this.address = "0 streetName city parish";
		this.email = "___@gmail.com";
		this.contact = "000-000-0000";
		this.numOfTicketOutstanding = 0;
		this.ticketOwing = 0.0;
		this.policeRecordReason = "N/A";
		this.policeDeniedReason = "N/A";
	}
	
	public Application(Application obj) { //copy constructor
		this.trn = obj.trn;
		this.ownerName = obj.ownerName;
		this.driverName = obj.driverName;
		this.dob = obj.dob;
		this.address = obj.address;
		this.email = obj.email;
		this.contact = obj.contact;
		this.numOfTicketOutstanding = obj.numOfTicketOutstanding;
		this.ticketOwing = obj.ticketOwing;
		this.policeRecordReason = obj.policeRecordReason;
		this.policeDeniedReason = obj.policeDeniedReason;
	}

	///getters
	public String getTrn() {
		return trn;
	}

	public Name getOwnerName() {
		return ownerName;
	}

	public Name getDriverName() {
		return driverName;
	}

	public Date getDob() {
		return dob;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getContact() {
		return contact;
	}

	public int getNumOfTicketOutstanding() {
		return numOfTicketOutstanding;
	}

	public double getTicketOwing() {
		return ticketOwing;
	}

	public String getPoliceRecordReason() {
		return policeRecordReason;
	}

	public String getPoliceDeniedReason() {
		return policeDeniedReason;
	}

	//setters
	public void setTrn(String trn) {
		this.trn = trn;
	}
	
	public void setOwnerName(Name ownerName) {
		this.ownerName = new Name(ownerName);
	}

	public void setDriverName(Name driverName) {
		this.driverName = new Name(driverName);
	}

	public void setDob(Date dob) {
		this.dob = new Date(dob);
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setNumOfTicketOutstanding(int numOfTicketOutstanding) {
		this.numOfTicketOutstanding = numOfTicketOutstanding;
	}

	public void setTicketOwing(double ticketOwing) {
		this.ticketOwing = ticketOwing;
	}

	public void setPoliceRecordReason(String policeRecordReason) {
		this.policeRecordReason = policeRecordReason;
	}

	public void setPoliceDeniedReason(String policeDeniedReason) {
		this.policeDeniedReason = policeDeniedReason;
	}

	@Override
	public String toString() { //Displays the values held by the attributes when called in the format below
		return "This Application's Information:\nTRN: " + trn+ "\nOwner Name: " + ownerName + "\nDriver Name: " + driverName + "\nDOB: " + dob + "\nAddress: " + address
				+ "\nEmail: " + email + "\nContact: " + contact + "\nNumber of Outstanding Ticket: " + numOfTicketOutstanding
				+ "\nOverall Amount Owing: $" + ticketOwing + "\nPolice Record Reason: "
				+ policeRecordReason + "\nPolice Denied Reason: " + policeDeniedReason;
	}
	
	public void searchApp()//searches for an application
	{
		Scanner input= new Scanner(System.in);
		Ticket ticket= new Ticket();
		String search= " ";
		boolean validTrn = false;
		boolean due= false;
		boolean found= false;
		boolean foundTrn= false;
		
		//Validation for search attribute
		while(!validTrn){
			try{
				System.out.println("\nEnter the TRN on the Application you would like to view: ");        //Applicant's/Owner TRN
				search= input.next();
				
				// Check if trn length is 11 characters
	            if (search.length()==11)  {
	                validTrn = true;  // Exit loop if email is valid
	            } else {
	                System.out.println("Invalid TRN. Please ensure the length of the trn is exactly 11 characters");
	            }
	            
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		try {
			//Read from the application file
			Scanner reader= new Scanner(new File("Application.txt"));
			
			while(reader.hasNext()) {
				this.trn= reader.next();
				
				String f= reader.next();
				String mi= reader.next();
				String l= reader.next();
				this.ownerName= new Name(f,mi,l);
				
				f= reader.next();
				mi= reader.next();
				l= reader.next();
				this.driverName= new Name(f,mi,l);
				
				int day= reader.nextInt();
				int month= reader.nextInt();
				int year= reader.nextInt();
				this.dob= new Date(day, month, year);
				
				this.address= reader.next();
				this.address= this.address.replace('_', ' ');
				this.email= reader.next();
				this.contact= reader.next();
				this.numOfTicketOutstanding= reader.nextInt();
				this.ticketOwing= reader.nextDouble();
				
				this.policeRecordReason= reader.next();
				this.policeDeniedReason= reader.next();
				
				//Check if the current record matches the TRN to inputed for search
				if(this.trn.equals(search)) {
					foundTrn= true;
			        try (Scanner reader1 = new Scanner(new File("ticketDatabase.txt"))){
			        	
			        	System.out.println("\nALL OF THE DRIVER'S OUSTANDING TICKETS WILL BE DISPLAYED BELOW\n");
			        	
			        	//Read from ticket file			
						while (reader1.hasNext()) {
						    ticket.setTicketNo((int) reader1.nextInt());
							ticket.setDriverTrn(reader1.next());

							// Read Issue Due Date
							int d = (int) reader1.nextInt();
							int m = (int) reader1.nextInt();
							int y = (int) reader1.nextInt();
							ticket.setIssueDueDate(new Date(d, m, y));

							ticket.setOffenceCode(reader1.next());
							ticket.setOffenceDescription(reader1.next());
							ticket.setFineAmount((double) reader1.nextDouble());
							ticket.setLicensePlateNo(reader1.next());

							f = reader1.next();
							mi = reader1.next();
							l = reader1.next();
							ticket.setDriverFullName(new Name(f, mi, l));

							d = (int) reader1.nextInt();
							m = (int) reader1.nextInt();
							y = (int) reader1.nextInt();
							ticket.setDriverDob(new Date(d, m, y));

							ticket.setDriverAddress(reader1.next().replace('_', ' '));
							ticket.setDriverContact(reader1.next());
							ticket.setBadgeNum(reader1.next());

							f = reader1.next();
							mi = reader1.next();
							l = reader1.next();
							ticket.setOfficerFullName(new Name(f, mi, l));
							ticket.setOfficerStation(reader1.next());
							ticket.setOfficerStation(ticket.getOfficerStation().replace('_', ' '));
							
							//checks if the Driver name entered is equal to the one read from the file 		
							if (ticket.getDriverFullName().getFirstName().equalsIgnoreCase(this.driverName.getFirstName()) &&
								ticket.getDriverFullName().getMiddleName().equalsIgnoreCase(this.driverName.getMiddleName()) &&
								  ticket.getDriverFullName().getLastName().equalsIgnoreCase(this.driverName.getLastName())){
								
								found= true;
								System.out.println("THESE MUST BE PAID BEFORE THE APPLICATION IS PROCESSED.");
								
								this.numOfTicketOutstanding= 0;
								this.ticketOwing= 0;
								
								//Display information for outstanding ticket for specific trn searched for
								System.out.println("Ticket Number\tTicket Amount");
							   if (ticket.getFineAmount()> 0) {
								   
								   this.numOfTicketOutstanding+= 1;
									this.ticketOwing += ticket.getFineAmount();
								   
							        System.out.println(ticket.getTicketNo()+ "\t\t$"+ ticket.getFineAmount());
								    System.out.println("----------------------------------------");
								    due= true;
							   } 
							}
						}
						
						if(!found) { //if the driver us not found or doesn't have any due tickets then the msgs below are shown
						    System.out.println("This driver doesn't exist in the Ticket Database\n");	
						} else if(!due) {
						    System.out.println("You don't have any outstanding tickets!!!\n");
						}
					reader1.close();
					} catch (FileNotFoundException e) {
				        e.printStackTrace();
				    }
			        
			        System.out.println(this.toString().replace('_', ' '));
				}//end of if
			}
			reader.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(!foundTrn) {
			System.out.println("This applicant doesn't exist in the Driver Database\n");
		}
	}
	
	public void createApp() //creates an application
	{
		Driver dri = new Driver();
		//Ticket tic = new Ticket();
		
		Scanner input = new Scanner(System.in);
		
		int d= 0;
		int m= 0;
		int y= 0;
		
		String fi= "";
		String mi= "";
		String la= "";
		
		String accidentResponse = "";
    	String policeRecordResponse = "";
    	String outstandingTicketsResponse = "";
		
		//int countOutstandingTickets = 0;
        //int totalTicketOwing = 0;
		
		//Get Application Data/Attributes
		boolean validTrn = false;
		boolean found = false;
		
		while(!validTrn){
			try{
				System.out.println("Enter Applicant's/ Owner's TRN: ");        //Applicant's/Owner TRN
				this.trn = input.next();
				
				// Check if trn length is 11 characters
	            if (this.trn.length()==11)  {
	                validTrn = true;  // Exit loop if email is valid
	            } else {
	                System.out.println("Invalid TRN. Please ensure the length of the trn is exactly 11 characters");
	            }
	            
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		try{
			System.out.println("Enter Applicant's/ Owner's First Name: ");          //Owner's Name
			fi= input.next();           
			System.out.println("Enter Applicant's/ Owner's Middle Name: ");               
			mi= input.next();
			System.out.println("Enter Applicant's/ Owner's Last Name: ");
			la= input.next();
			this.ownerName = new Name(fi, mi, la);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		// Validate Email Input
    	boolean validEmail = false;
    	
    	while(!validEmail){
			try{
				System.out.println("Enter Applicant's/ Owner's Email: ");           //Applicant's Email
				this.email = input.next();
				
				// Check if email contains '@' and ends with '.com'
	            if (this.email.contains("@") && this.email.endsWith(".com")) {
	                validEmail = true;  // Exit loop if email is valid
	            } else {
	                System.out.println("Invalid email. Please ensure it contains '@' and ends with '.com'.");
	            }
	            
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		try{
			System.out.println("Enter Driver's First Name: ");                       //Driver's Name
			fi = input.next();
			System.out.println("Enter Driver's Middle Name: ");
			mi = input.next();
			System.out.println("Enter Driver's Last Name: ");
			la = input.next();
			this.driverName = new Name(fi, mi, la);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		d = -1;
		while (d < 1 || d > 31) {
			try{
				System.out.println("Enter Applicant's/ Owner's day of Birth: ");                     //Applicant's DOB
				d = input.nextInt();
				
				if(d < 1 || d > 31)
				{
					throw new InputMismatchException("Day must be between 1 and 31.");
				}
			} catch (InputMismatchException e) {
				System.err.println("Invalid! Please enter a valid day (1-31).");
			}
		}
		
		m = -1;
		while (m < 1 || m > 12) {
			try{
				System.out.println("Enter Applicant's/ Owner's month of Birth: ");
				m = input.nextInt();
				
				if(m < 1 || m > 12)
				{
					throw new InputMismatchException("Month must be between 1 and 12.");
				}
			} catch (InputMismatchException e) {
	            System.err.println("Invalid! Please enter a valid month (1-12).");
	        }
        }
		
		y = -1;
		while (y < 1900 || y > 2006) {
			try{
				System.out.println("Enter Applicant's/ Owner's year of Birth: ");
				y = input.nextInt();
				
				if(y < 1900 || y > 2006)
				{
					throw new InputMismatchException("Year must be between 1900 and 2006.");
				}
			} catch (InputMismatchException e) {
	            System.err.println("Invalid! Please enter a valid year (1900-2006).");
	        }
        }
		
		this.dob = new Date(d, m, y);
		
		try{
			input.nextLine(); //Consumes leftover new line character                                    //Applicant's Address
			System.out.println("Enter Applicant's/ Owner's Address: ");
			this.address = input.nextLine();
			address= address.replace(' ', '_'); //replaces the space entered by the user with a '_' 
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		try{
			System.out.println("Enter Applicant's/ Owner's Contact (e.g. \""+ this.contact+ "\"): ");             //Applicant's Contact Number
			this.contact = input.next();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
				
		//Qualification Questions for Red Plate license
		try{
			System.out.println("\nDid the driver cause any accident(s) within the last two years? (Yes/No)");
	        accidentResponse = input.next().toLowerCase();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		try{
	        System.out.println("Does the driver have a negative police record? (Yes/No)");
	        policeRecordResponse = input.next().toLowerCase();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
    	
		//Read the record from driver file
		File drifile = new File("driverDatabase.txt");
		
		try(Scanner fileScanner = new Scanner(drifile)){
			while(fileScanner.hasNext()){
				dri.setTrn(fileScanner.next());
				fi= fileScanner.next();     //Fullname
				mi= fileScanner.next();
				la= fileScanner.next();
				dri.setFullName(new Name(fi, mi, la));
				d=fileScanner.nextInt();     //DOB
				m= fileScanner.nextInt();
				y= fileScanner.nextInt();
				dri.setDob(new Date(d, m, y));
				dri.setAddress(fileScanner.next());
				dri.setContactNo(fileScanner.next());
				d=fileScanner.nextInt();     //PaymentDueDate
				m= fileScanner.nextInt();
				y= fileScanner.nextInt();
				dri.setPaymentDueDate(new Date(d, m, y));   
				d=fileScanner.nextInt();     //CourtDate
				m= fileScanner.nextInt();
				y= fileScanner.nextInt();
				dri.setCourtDate(new Date(d, m, y));        
				dri.setCourtLocation(fileScanner.next());
				dri.setTotalUnpaidTickets(fileScanner.nextInt());
				dri.setTotalFineAmount(fileScanner.nextDouble());
				
				//take data from driver file and save in applicant using Name
				if (dri.getFullName().getFirstName().equalsIgnoreCase(this.driverName.getFirstName()) &&
				    dri.getFullName().getMiddleName().equalsIgnoreCase(this.driverName.getMiddleName()) &&
				    dri.getFullName().getLastName().equalsIgnoreCase(this.driverName.getLastName())) 
				{
					found = true;
					
					this.numOfTicketOutstanding = dri.getTotalUnpaidTickets();
					this.ticketOwing = dri.getTotalFineAmount();
					
					if(dri.getTotalUnpaidTickets() > 0)
					{
						outstandingTicketsResponse =  "yes";                              //check if what is in the file is greater than 0, if it is make the response yes or no accordingly
					}else {
						outstandingTicketsResponse =  "no";
					}
				
					if (accidentResponse.equals("yes") && outstandingTicketsResponse.equals("yes") && policeRecordResponse.equals("yes")) {
						this.policeDeniedReason = "The driver has caused accident(s) recently, the driver has outstanding tickets and the driver has a negative police record";
						policeDeniedReason= policeDeniedReason.replace(' ', '_');                       //replaces the space entered by the user with a '_'
					} else if(outstandingTicketsResponse.equals("yes") && policeRecordResponse.equals("yes")) {
						this.policeDeniedReason = "The driver has outstanding tickets and the driver has a negative police record";
						policeDeniedReason= policeDeniedReason.replace(' ', '_');
					} else if (accidentResponse.equals("yes") && policeRecordResponse.equals("yes")) {
						this.policeDeniedReason = "The driver has caused accident(s) recently and the driver has a negative police record";
						policeDeniedReason= policeDeniedReason.replace(' ', '_');
					} else if (accidentResponse.equals("yes") && outstandingTicketsResponse.equals("yes")) {
						this.policeDeniedReason = "The driver has caused accident(s) recently and the driver has outstanding tickets";
						policeDeniedReason= policeDeniedReason.replace(' ', '_');
					} else if (policeRecordResponse.equals("yes")) {
						this.policeDeniedReason = "The driver has a negative police record";            //inputting the policeDeniedReason into attribute
						policeDeniedReason= policeDeniedReason.replace(' ', '_');
					} else if (outstandingTicketsResponse.equals("yes")) {
						this.policeDeniedReason = "The driver has outstanding tickets";        //inputting the policeDeniedReason into attribute
						policeDeniedReason= policeDeniedReason.replace(' ', '_');
					} else if (accidentResponse.equals("yes")) {
						this.policeDeniedReason = "The driver has caused accidents in the last 2 years";          //inputting the policeDeniedReason into attribute
						policeDeniedReason= policeDeniedReason.replace(' ', '_');				
					}
				}
				
				
			}
			//Close File
			fileScanner.close();
			
			if(!found){
				System.out.println("\nInvalid Application. Driver has to exist in the system to be in an application.");
				return;
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		boolean tryAgain = false; 
        while(!tryAgain){	
	        //Results based on qualification questions
	        if (accidentResponse.equals("yes") || policeRecordResponse.equals("yes") || outstandingTicketsResponse.equals("yes"))
	        {
				String denyRea = "\nPlease change the proposed driver within 5 to 10 business days, otherwise, the application will be denied";
				System.out.println(denyRea);
				//this.policeDeniedReason = denyRea;       //inputting the policeDeniedReason into attribute
				System.out.println("\nReport of Status of Application...");
				System.out.println(toString().replace('_', ' '));
				
				tryAgain = true;
			} else if (accidentResponse.equals("no") && policeRecordResponse.equals("no") && outstandingTicketsResponse.equals("no")) {
				String acptRea ="You are issued the permit because the driver did not have any accidents in the past 2 years, the driver does not have a negative police record record and the driver does not have any outstanding tickets.";
				System.out.println(acptRea);
				//this.policeDeniedReason = acptRea;        //inputting the policeDeniedReason into attribute
				System.out.println("\nReport of Status of Application...");
				System.out.println(toString().replace('_', ' '));
				
				tryAgain = true;
			} else {
				System.out.println("Invalid data entered. Please enter either yes or no");
			}
		}
			
		//Create Application
		FileWriter createFile = null;
		
		try{
			createFile = new FileWriter(new File("Application.txt"), true);
			
			String record = trn + "\t" + ownerName + "\t" + driverName + "\t" + 
							dob.getDay()+ " "+ dob.getMonth()+ " "+ dob.getYear() + "\t" + address + "\t" + email + "\t" + contact + "\t" +
							numOfTicketOutstanding + "\t" + ticketOwing + 
							"\t" + policeRecordReason + "\t" + policeDeniedReason + "\n";
			createFile.write(record);	
			
			// Display message indicating application has been created
        	System.out.println("\nApplication has been successfully created and saved to Application.txt.");
			
			createFile.close();
		} catch (IOException e){
			System.err.println(e.getMessage());
		} catch (Exception e){
			System.err.println(e.getMessage());
		}	
	}
	
	public void updateApp(){
		Scanner input = new Scanner(System.in);
		
		File oldFil = new File("Application.txt");
		File newFil = new File("temApp.txt");
		
		String searchTrn = "";
		boolean validTrn = false;
		
		Driver dri = new Driver();
		
		String accidentResponse = "";
    	String policeRecordResponse = "";
    	String outstandingTicketsResponse = "";
    	
    	int d= 0;
		int m= 0;
		int y= 0;
		
		String fi= "";
		String mi= "";
		String la= "";
		
		boolean found = false;
		boolean updatedSuccessfully = false;  //Check if update occurred
		
		//Allow user to enter the trn for the application they want to update and also validation on input
		while(!validTrn){
			try{
				System.out.println("Enter the Owner's TRN for the application you'd like to update: ");        //Applicant's/Owner TRN
				searchTrn = input.next();
				
				// Check if trn length is 11 characters
	            if (searchTrn.length()==11)  {
	                validTrn = true;  // Exit loop if email is valid
	            } else {
	                System.out.println("Invalid TRN. Please ensure the length of the trn is exactly 11 characters");
	            }
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		//Read record from application file
		try(Scanner fileScanner1 = new Scanner(oldFil);
			FileWriter newAppFile = new FileWriter(newFil)) {
			
			while(fileScanner1.hasNext()){
				this.trn= fileScanner1.next();           //Owner TRN
				fi= fileScanner1.next();               //Owner Name
				mi= fileScanner1.next();
				la= fileScanner1.next();
				this.ownerName= new Name(fi,mi,la);
				fi= fileScanner1.next();                 //Driver Name
				mi= fileScanner1.next();
				la= fileScanner1.next();
				this.driverName= new Name(fi,mi,la);
				d= fileScanner1.nextInt();            //Owner DOB
				m= fileScanner1.nextInt();
				y= fileScanner1.nextInt();
				this.dob= new Date(d, m, y);
				this.address= fileScanner1.next();                      //Owner Address
				this.address= this.address.replace(' ', '_');
				this.email= fileScanner1.next();                        //Owner Email
				this.contact= fileScanner1.next();                     //Owner Contact
				this.numOfTicketOutstanding= fileScanner1.nextInt();   //Owner Num Outstanding Tickets
				this.ticketOwing= fileScanner1.nextDouble();           //Owner Ticket Owing
				this.policeRecordReason= fileScanner1.next();         //Driver Police Record Reason
				this.policeDeniedReason= fileScanner1.next();         //Driver Police Denied Reason
			
				//Check if the current record matches the TRN to update
				if(this.trn.equals(searchTrn)) {
					found = true;
					
					//Accept Updated values
					try{
						System.out.println("Enter Owner's updated First Name: ");          //Owner's Name
						fi= input.next();           
						System.out.println("Enter Owner's updated Middle Name: ");               
						mi= input.next();
						System.out.println("Enter Owner's updated Last Name: ");
						la= input.next();
						this.ownerName = new Name(fi, mi, la);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					
					// Validate Email Input
			    	boolean validEmail = false;
			    	
			    	while(!validEmail){
						try{
							System.out.println("Enter Applicant's updated Email: ");           //Applicant's Email
							this.email = input.next();
							
							// Check if email contains '@' and ends with '.com'
				            if (this.email.contains("@") && this.email.endsWith(".com")) {
				                validEmail = true;  // Exit loop if email is valid
				            } else {
				                System.out.println("Invalid email. Please ensure it contains '@' and ends with '.com'.");
				            }
				            
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
					}
					
					try{
						System.out.println("Enter Driver's updated First Name: ");                       //Driver's Name
						fi = input.next();
						System.out.println("Enter Driver's updated Middle Name: ");
						mi = input.next();
						System.out.println("Enter Driver's updated Last Name: ");
						la = input.next();
						this.driverName = new Name(fi, mi, la);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					
					d = -1;
					while (d < 1 || d > 31) {
						try{
							System.out.println("Enter Applicant's updated day of Birth: ");                     //Applicant's DOB
							d = input.nextInt();
							
							if(d < 1 || d > 31)
							{
								throw new InputMismatchException("Day must be between 1 and 31.");
							}
						} catch (InputMismatchException e) {
							System.err.println("Invalid! Please enter a valid day (1-31).");
						}
					}
					
					m = -1;
					while (m < 1 || m > 12) {
						try{
							System.out.println("Enter Applicant's updated month of Birth: ");
							m = input.nextInt();
							
							if(m < 1 || m > 12)
							{
								throw new InputMismatchException("Month must be between 1 and 12.");
							}
						} catch (InputMismatchException e) {
				            System.err.println("Invalid! Please enter a valid month (1-12).");
				        }
			        }
					
					y = -1;
					while (y < 1900 || y > 2006) {
						try{
							System.out.println("Enter Applicant's updated year of Birth: ");
							y = input.nextInt();
							
							if(y < 1900 || y > 2006)
							{
								throw new InputMismatchException("Year must be between 1900 and 2006.");
							}
						} catch (InputMismatchException e) {
				            System.err.println("Invalid! Please enter a valid year (1900-2006).");
				        }
			        }
					
					this.dob = new Date(d, m, y);
					
					try{
						input.nextLine(); //Consumes leftover new line character                                    //Applicant's Address
						System.out.println("Enter Applicant's updated Address: ");
						this.address = input.nextLine();
						address= address.replace(' ', '_'); //replaces the space entered by the user with a '_' 
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					
					try{
						System.out.println("Enter Applicant's updated Contact (e.g. \""+ this.contact+ "\"): ");             //Applicant's Contact Number
						this.contact = input.next();
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					
					try{
						System.out.println("Enter the Driver's Police Record Reason: ");                    //Driver's Police Record Reason                       
						this.policeRecordReason = input.next();
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					
					//Qualification Questions for Red Plate license
					try{
						System.out.println("\nDid the driver cause any accident(s) within the last two years? (Yes/No)");
				        accidentResponse = input.next().toLowerCase();
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					
					try{
				        System.out.println("Does the driver have a negative police record? (Yes/No)");
				        policeRecordResponse = input.next().toLowerCase();
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
			    	
					//Read the record from driver file
					File drifile = new File("driverDatabase.txt");
					
					try(Scanner fileScanner = new Scanner(drifile)){
						while(fileScanner.hasNext()){
							try{
								dri.setTrn(fileScanner.next());
								fi= fileScanner.next();     //Fullname
								mi= fileScanner.next();
								la= fileScanner.next();
								dri.setFullName(new Name(fi, mi, la));
								d=fileScanner.nextInt();     //DOB
								m= fileScanner.nextInt();
								y= fileScanner.nextInt();
								dri.setDob(new Date(d, m, y));
								dri.setAddress(fileScanner.next());
								dri.setContactNo(fileScanner.next());
								d=fileScanner.nextInt();     //PaymentDueDate
								m= fileScanner.nextInt();
								y= fileScanner.nextInt();
								dri.setPaymentDueDate(new Date(d, m, y));   
								d=fileScanner.nextInt();     //CourtDate
								m= fileScanner.nextInt();
								y= fileScanner.nextInt();
								dri.setCourtDate(new Date(d, m, y));        
								dri.setCourtLocation(fileScanner.next());
								dri.setTotalUnpaidTickets(fileScanner.nextInt());
								dri.setTotalFineAmount(fileScanner.nextDouble());
							} catch (InputMismatchException e){
								System.err.println("Error reading file: " + e.getMessage());
								input.next(); //Skip invalid token
							}
							
							//take data from driver file and save in applicantion using Name
							if (dri.getFullName().getFirstName().equalsIgnoreCase(this.driverName.getFirstName()) &&
							    dri.getFullName().getMiddleName().equalsIgnoreCase(this.driverName.getMiddleName()) &&
							    dri.getFullName().getLastName().equalsIgnoreCase(this.driverName.getLastName())) 
							{
								this.numOfTicketOutstanding = dri.getTotalUnpaidTickets();
								this.ticketOwing = dri.getTotalFineAmount();
								
								if(dri.getTotalUnpaidTickets() > 0)
								{
									outstandingTicketsResponse =  "yes";                              //check if what is in the file is greater than 0, if it is make the response yes or no accordingly
								}else {
									outstandingTicketsResponse =  "no";
								}
								
								// Initialize policeDeniedReason to an empty string or a default message
								this.policeDeniedReason = "N/A";
								
								//Assigning value to policeDeniedReason based on accident response
								if (accidentResponse.equals("yes") && outstandingTicketsResponse.equals("yes") && policeRecordResponse.equals("yes")) {
									this.policeDeniedReason = "The driver has caused accident(s) recently, the driver has outstanding tickets and the driver has a negative police record";
									policeDeniedReason= policeDeniedReason.replace(' ', '_');                       //replaces the space entered by the user with a '_'
								} else if(outstandingTicketsResponse.equals("yes") && policeRecordResponse.equals("yes")) {
									this.policeDeniedReason = "The driver has outstanding tickets and the driver has a negative police record";
									policeDeniedReason= policeDeniedReason.replace(' ', '_');
								} else if (accidentResponse.equals("yes") && policeRecordResponse.equals("yes")) {
									this.policeDeniedReason = "The driver has caused accident(s) recently and the driver has a negative police record";
									policeDeniedReason= policeDeniedReason.replace(' ', '_');
								} else if (accidentResponse.equals("yes") && outstandingTicketsResponse.equals("yes")) {
									this.policeDeniedReason = "The driver has caused accident(s) recently and the driver has outstanding tickets";
									policeDeniedReason= policeDeniedReason.replace(' ', '_');
								} else if (policeRecordResponse.equals("yes")) {
									this.policeDeniedReason = "The driver has a negative police record";            //inputting the policeDeniedReason into attribute
									policeDeniedReason= policeDeniedReason.replace(' ', '_');
								} else if (outstandingTicketsResponse.equals("yes")) {
									this.policeDeniedReason = "The driver has outstanding tickets";        //inputting the policeDeniedReason into attribute
									policeDeniedReason= policeDeniedReason.replace(' ', '_');
								} else if (accidentResponse.equals("yes")) {
									this.policeDeniedReason = "The driver has caused accidents in the last 2 years";          //inputting the policeDeniedReason into attribute
									policeDeniedReason= policeDeniedReason.replace(' ', '_');				
								}
							}
							
							
						}
						//Close File
						fileScanner.close();
						
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					
					//seeting boolean to let us know know this specific record has been updated
					updatedSuccessfully = true;
				}
				
				//Write updated record to new file
				newAppFile.write(trn + "\t" +ownerName + "\t" + driverName + "\t" + 
							dob.getDay()+ " "+ dob.getMonth()+ " "+ dob.getYear() + "\t" + address + "\t" + email + "\t" + contact + "\t" +
							numOfTicketOutstanding + "\t" + ticketOwing + 
							"\t" + policeRecordReason + "\t" + policeDeniedReason + "\n");
			
			}
			
			fileScanner1.close();     //doesn't have to close,it automatically closes
			newAppFile.close();       //doesn't have to close,it automatically closes
			
			oldFil.delete();           //should be outside try if didnt close the first 2 above, but inside if we closed them
			newFil.renameTo(oldFil);   //should be outside try if didnt close the first 2 above, but inside if we closed them
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		//Display message if the application has been updated or the trn search for was not found
		if(updatedSuccessfully) {
		    System.out.println("Your application has been successfully updated!");
		} else if (!found) {
		    System.out.println("This applicant doesn't exist in the Application Database\n");
		}
	}
	
	public void deleteApp(){
		Scanner input= new Scanner(System.in);
		Ticket ticket= new Ticket();
		String search= " ";
		boolean validTrn = false;
		boolean due= false;
		boolean found= false;
		boolean foundTrn= false;
		
		//Old file and new file without the deleted record
		File temp= new File("tempApp.txt");
		File origin=new File("Application.txt");
		
		//Validating TRN entered for search
		while(!validTrn){
			try{
				System.out.println("\nEnter the TRN on the Application you would like to DELETE: ");        //Applicant's/Owner TRN
				search= input.next();
				
				// Check if trn length is 11 characters
	            if (search.length()==11)  {
	                validTrn = true;  // Exit loop if email is valid
	            } else {
	                System.out.println("Invalid TRN. Please ensure the length of the trn is exactly 11 characters");
	            }
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		try (Scanner reader= new Scanner(origin);
			FileWriter wrote= new FileWriter(temp, true)){
			//Read record from PPV file
			
			
			while(reader.hasNext()) {
				this.trn= reader.next();
				
				String f= reader.next();
				String mi= reader.next();
				String l= reader.next();
				this.ownerName= new Name(f,mi,l);
				
				f= reader.next();
				mi= reader.next();
				l= reader.next();
				this.driverName= new Name(f,mi,l);
				
				int day= reader.nextInt();
				int month= reader.nextInt();
				int year= reader.nextInt();
				this.dob= new Date(day, month, year);
				
				this.address= reader.next();
				this.address= this.address.replace('_', ' ');
				this.email= reader.next();
				this.contact= reader.next();
				this.numOfTicketOutstanding= reader.nextInt();
				this.ticketOwing= reader.nextDouble();
				
				this.policeRecordReason= reader.next();
				this.policeDeniedReason= reader.next();
				
				//Check if the current record matches the TRN to delete
				if(this.trn.equals(search)) {
					foundTrn= true;
			        
					System.out.println("\nThis Application is DELETED\n");
			        System.out.println(this.toString().replace('_', ' '));
				}
				else {
						//If TRN does not match(i.e- it's not the record to be deleted - Write the record to the temporary file)
						
						String record = trn + "\t" + ownerName + "\t" + driverName + "\t" + 
								dob.getDay()+ " "+ dob.getMonth()+ " "+ dob.getYear() + "\t" + address + "\t" + email + "\t" + contact + "\t" +
								numOfTicketOutstanding + "\t" + ticketOwing + 
								"\t" + policeRecordReason + "\t" + policeDeniedReason + "\n";
						
						wrote.write(record.replace(' ', '_'));	
				}
			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(!foundTrn) {
			System.out.println("This applicant doesn't exist in the Driver Database\n");
		}
		
		//Delete original file and rename the temporary file that contains every record except the deleted record which was not wriiten - i.e. the updated file
		origin.delete();
		temp.renameTo(origin);
	}
	
	  
	public void rejectApp ()
	{
		Scanner input = new Scanner(System.in);
		
		File old = new File ("Application.txt");
		File newF = new File ("temp.txt");
		
		String searchTRN = null;
		
		String newDeniedReason = null;		

		
		
		boolean validTRN = false;
		boolean found = false;
		
		while(!validTRN)
		{
			try
			{
				System.out.println("Enter the Owner's TRN for the application you would like to reject: ");
				searchTRN = input.next();
				
				if (searchTRN.length() == 11)
				{
					validTRN = true; 
				}
				else
				{
					System.out.println("Invalid TRN. Please ensure the length of the trn is exactly 11 characters");
				}
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
		}
			
			
			try (Scanner reader = new Scanner(old))
				
				{
					while(reader.hasNext())
					{
						this.trn= reader.next();           //Owner TRN
						String fi= reader.next();               //Owner Name
						String mi= reader.next();
						String la= reader.next();
						this.ownerName= new Name(fi,mi,la);
						fi= reader.next();                 //Driver Name
						mi= reader.next();
						la= reader.next();
						this.driverName= new Name(fi,mi,la);
						int d= reader.nextInt();            //Owner DOB
						int m= reader.nextInt();
						int y= reader.nextInt();
						this.dob= new Date(d, m, y);
						this.address= reader.next();                      //Owner Address
						this.address= this.address.replace('_', ' ');
						this.email= reader.next();                        //Owner Email
						this.contact= reader.next();                     //Owner Contact
						this.numOfTicketOutstanding= reader.nextInt();   //Owner Num Outstanding Tickets
						this.ticketOwing= reader.nextDouble();           //Owner Ticket Owing
						this.policeRecordReason= reader.next();         //Driver Police Record Reason
						this.policeDeniedReason= reader.next();         //Driver Police Denied Reason
					
						if(searchTRN.equals(this.trn))
						{
							newDeniedReason = "Application Rejected!" ;		
							this.policeDeniedReason = newDeniedReason;	
							found = true;			
						}	
						
						String rightDeniedReason = this.policeDeniedReason.replace(' ', '_');
						String rightAddress = this.address.replace(' ', '_');
						
						try(FileWriter writer = new FileWriter(newF,true))
						{
							String record = trn+ "\t" + ownerName + "\t" + driverName + "\t" + 
							dob.getDay()+ " "+ dob.getMonth()+ " "+ dob.getYear() + "\t" + rightAddress + "\t" + email + "\t" + contact + "\t" +
							numOfTicketOutstanding + "\t" + ticketOwing + 
							"\t" + policeRecordReason + "\t" + rightDeniedReason + "\n";	
							
							writer.write(record);			
							
										 
						}catch (Exception e) {
							System.err.println("Error writinng to the file ->" +e.getMessage());
						}	
						
					
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				old.delete();
				newF.renameTo(old);	
				
				if (found) {
		            System.out.println("Application rejected successfully!");
		        } else {
		            System.out.println("TRN not found. No application was rejected.");
		        }
							
		}		
	} 
	
	
	

