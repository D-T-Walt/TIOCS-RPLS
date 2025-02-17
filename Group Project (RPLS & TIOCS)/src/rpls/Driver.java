/*Authors:
 		Diwani Walters- 2303848
 		Javone-Anthony Gordon- 2206126*/
package rpls;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import tiocs.Ticket;
import java.time.LocalDate;


public class Driver extends User {
	private Date dob;
	private String address;
	private String contactNo;
	private Date paymentDueDate;
	private Date courtDate;
	private String courtLocation;
	private int totalUnpaidTickets;
	private double totalFineAmount;
		
public Driver() //default constructor
	{
		super();
		this.dob = new Date();
		this.address = "streetnum streetname city parish";
		this.contactNo = "000-000-0000";
		this.paymentDueDate = new Date();
		this.courtDate = new Date();
		this.courtLocation = "parish";
		this.totalUnpaidTickets = 0;
		this.totalFineAmount = 0.0;
	}
	
	//primary constructor
	public Driver(Name fullName, String trn, String role, Date dob, String address, String contactNo, Date paymentDueDate, Date courtDate, String courtLocation, int totalUnpaidTickets, double totalFineAmount)
	{
		super(fullName, trn, role);
		this.dob = dob;
		this.address = address;
		this.contactNo = contactNo;
		this.paymentDueDate = paymentDueDate;
		this.courtDate = courtDate;
		this.courtLocation = courtLocation;
		this.totalUnpaidTickets = totalUnpaidTickets;
		this.totalFineAmount = totalFineAmount;
	}
	
	//copy constructor
	public Driver(Driver dri)
	{
		super(dri);
		this.dob = dri.dob;
		this.address = dri.address;
		this.contactNo = dri.contactNo;
		this.paymentDueDate = dri.paymentDueDate;
		this.courtDate = dri.courtDate;
		this.courtLocation = dri.courtLocation;
		this.totalUnpaidTickets = dri.totalUnpaidTickets;
		this.totalFineAmount = dri.totalFineAmount;
	}
	
	// Getters and Setters 
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Date getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(Date paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public Date getCourtDate() {
        return courtDate;
    }

    public void setCourtDate(Date courtDate) {
        this.courtDate = courtDate;
    }

    public String getCourtLocation() {
        return courtLocation;
    }

    public void setCourtLocation(String courtLocation) {
        this.courtLocation = courtLocation;
    }

    public int getTotalUnpaidTickets() {
        return totalUnpaidTickets;
    }

    public void setTotalUnpaidTickets(int totalUnpaidTickets) {
        this.totalUnpaidTickets = totalUnpaidTickets;
    }

    public double getTotalFineAmount() {
        return totalFineAmount;
    }

    public void setTotalFineAmount(double totalFineAmount) {
        this.totalFineAmount = totalFineAmount;
    }
    
    public boolean log(){ //login method
    	File file = new File("driverDatabase.txt"); 
    	Scanner input= new Scanner(System.in);
    	String searchTrn;
    	
    	System.out.println("\nEnter your TRN: ");
    	searchTrn= input.next();
    	
    	// Check if the file exists
        if (!file.exists()) {
            System.err.println("Error: The file does not exist.");
            input.close();
            return false;
        }

        // Try to open the file for reading
        try (Scanner fileScanner = new Scanner(file)) { //reads the driver file to find a trn that matches the entered one, thus granting access
            while (fileScanner.hasNext()) { 
            	this.trn= fileScanner.next();
            	
            	String f= fileScanner.next();
            	String mi= fileScanner.next();
            	String l= fileScanner.next();
            	
            	this.fullName= new Name(f, mi, l);
            	
            	int d= (int) fileScanner.nextInt();
            	int m= (int) fileScanner.nextInt();
            	int y= (int) fileScanner.nextInt();
            	
				this.dob= new Date(d, m, y);
				this.address= fileScanner.next();
				this.address= address.replace('_', ' ');
				
				this.contactNo= fileScanner.next();
				
				d= (int) fileScanner.nextInt();
            	m= (int) fileScanner.nextInt();
            	y= (int) fileScanner.nextInt();
				
				this.paymentDueDate= new Date(d, m, y);
				
				 d= (int) fileScanner.nextInt();
            	 m= (int) fileScanner.nextInt();
            	 y= (int) fileScanner.nextInt();
				
				this.courtDate= new Date(d, m, y);
				this.courtLocation= fileScanner.next();
				this.totalUnpaidTickets= (int) fileScanner.nextInt();
				this.totalFineAmount= (double) fileScanner.nextDouble();
          
          		//Narrative to check if the trn entered is found, if so user is logged into system
				if(searchTrn.equals(trn)) {
					return true;
				}
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
        }
        
        //If trn entered is not in the records
		return false;
	}
    
    public void addDriver() { //add driver to the file/system
        Scanner input = new Scanner(System.in);
        boolean validTRN= false;

        try {
        	//Accept driver's attribute information
        	while(!validTRN)
    		{
    			try
    			{
    				System.out.println("Enter the Driver's TRN: ");
    				this.trn = input.next();
    				
    				if (this.trn.length() == 11)
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
        	
        	System.out.println("Enter driver's First Name");
        	this.fullName.setFirstName(input.next());
        	
        	System.out.println("Enter driver's Middle Name");
        	this.fullName.setMiddleName(input.next());
        	
        	System.out.println("Enter driver's Last Name");
        	this.fullName.setLastName(input.next());
        	
            System.out.println("Enter driver's Date of Birth");
			
			
			//validation check of the entered data
            this.dob.setDay(-1);
            while (dob.getDay() < 1 || dob.getDay() > 31) {
                try {
                    System.out.println("Day (1-31): ");
                    this.dob.setDay(input.nextInt());
                    if (dob.getDay() < 1 || dob.getDay() > 31) {
                        throw new InputMismatchException("Day must be between 1 and 31.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Invalid! Please enter a valid day (1-31).");
                }
            }

            this.dob.setMonth(-1);
            while (dob.getMonth() < 1 || dob.getMonth() > 12) {
                try {
                    System.out.println("Month (1-12): ");
                    this.dob.setMonth(input.nextInt());
                    
                    if (dob.getMonth() < 1 || dob.getMonth() > 12) {
                        throw new InputMismatchException("Month must be between 1 and 12.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Invalid! Please enter a valid month (1-12).");
                }
            }

            this.dob.setYear(-1);
            while (dob.getYear() < 1900 || dob.getYear() > 2006) {
                try {
                    System.out.println("Year (e.g., 1990): ");
                    this.dob.setYear(input.nextInt());
                    
                    if (dob.getYear() < 1900 || dob.getYear() > 2006) {
                        throw new InputMismatchException("Year must be between 1900 and 2006.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Invalid! Please enter a valid year (1900-2006).");
                }
            }

			//Narrative to accept address(after accepting it is stored in file with '_' separating each word/number)
            input.nextLine(); //Consumes leftover new line character to prevent that leftover newline character to be read instead of the address
            System.out.println("Enter the driver's Address (e.g. \""+ this.address+ "\"): ");
            this.address = input.nextLine();	//reads entire line with spaces 
            address= address.replace(' ', '_'); //replaces the space entered by the user with a '_' 

            System.out.println("Enter the driver's Contact Number (e.g. \""+ this.contactNo+ "\"): ");
            this.contactNo = input.next();

            // Writing the data to a file
            try (FileWriter write = new FileWriter(new File("driverDatabase.txt"), true)) {
            	
                String record = trn +"\t"+ fullName.toString()+"\t"+ dob.getDay()+ " "+ dob.getMonth()+ " "+ dob.getYear() + "\t" + address + "\t" + contactNo + "\t"+ paymentDueDate.getDay() + " "+ paymentDueDate.getMonth()+ " "+ paymentDueDate.getYear() +"\t"+ courtDate.getDay() + " "+ courtDate.getMonth()+ " "+ courtDate.getYear() +"\t "+ courtLocation +"\t"+ totalUnpaidTickets +"\t"+ totalFineAmount+ "\n";
                
                write.write(record);
                write.close();
                
                System.out.println("Driver data saved successfully.");
            } catch (IOException e) {
                System.err.println("Error while writing to file: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }
    }
    
    public void displayAllDrivers() { //displays all drivers in the file/system
        File file = new File("driverDatabase.txt");      //Initialize file

        // Check if the file exists
        if (!file.exists()) {
            System.err.println("Error: The file does not exist.");
            return;
        }

        // Try to open the file for reading
        try (Scanner fileScanner = new Scanner(file)) {
            System.out.println("Displaying all driver data from the file:");
            System.out.println("-------------------------------------------------------------------------------------");
            // Read and display the data line by line
            while (fileScanner.hasNext()) {
            	this.trn= fileScanner.next();
            	
            	String f= fileScanner.next();
            	String mi= fileScanner.next();
            	String l= fileScanner.next();
            	
            	this.fullName= new Name(f, mi, l);
            	
            	int d= (int) fileScanner.nextInt();
            	int m= (int) fileScanner.nextInt();
            	int y= (int) fileScanner.nextInt();
            	
				this.dob= new Date(d, m, y);
				this.address= fileScanner.next();
				this.address= address.replace('_', ' '); //replaces _ in the file with a blank space for user readabilty 
				
				this.contactNo= fileScanner.next();
				
				d= (int) fileScanner.nextInt();
            	m= (int) fileScanner.nextInt();
            	y= (int) fileScanner.nextInt();
				
				this.paymentDueDate= new Date(d, m, y);
				
				 d= (int) fileScanner.nextInt();
            	 m= (int) fileScanner.nextInt();
            	 y= (int) fileScanner.nextInt();
				
				this.courtDate= new Date(d, m, y);
				this.courtLocation= fileScanner.next();
				this.totalUnpaidTickets= (int) fileScanner.nextInt();
				this.totalFineAmount= (double) fileScanner.nextDouble();
          
          		//Store driver information from to-string in record variable
				String record= this.toString();
				
				System.out.println(record+ "\n"); // Display the content of each line in the file
            }

            System.out.println("-------------------------------------------------------------------------------------");
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
        }
    }
    
	public void ticketCheck(){ //displays all tickets
		Ticket ticket= new Ticket();
        
        try (Scanner reader = new Scanner(new File("ticketDatabase.txt"))) { //reads from the ticket file
        	
        	System.out.println("\nALL YOUR TICKETS ISSUED WILL BE DISPLAYED BELOW\n");
			
			//Reading record from ticket file
			while (reader.hasNext()) {
			    ticket.setTicketNo((int) reader.nextInt());
				ticket.setDriverTrn(reader.next());

				// Read Issue Due Date
				int d = (int) reader.nextInt();
				int m = (int) reader.nextInt();
				int y = (int) reader.nextInt();
				ticket.setIssueDueDate(new Date(d, m, y));

				ticket.setOffenceCode(reader.next());
				ticket.setOffenceDescription(reader.next());
				ticket.setFineAmount((double) reader.nextDouble());
				ticket.setLicensePlateNo(reader.next());

				String f = reader.next();
				String mi = reader.next();
				String l = reader.next();
				ticket.setDriverFullName(new Name(f, mi, l));

				d = (int) reader.nextInt();
				m = (int) reader.nextInt();
				y = (int) reader.nextInt();
				ticket.setDriverDob(new Date(d, m, y));

				ticket.setDriverAddress(reader.next().replace('_', ' '));
				ticket.setDriverContact(reader.next());
				ticket.setBadgeNum(reader.next());

				f = reader.next();
				mi = reader.next();
				l = reader.next();
				ticket.setOfficerFullName(new Name(f, mi, l));
				ticket.setOfficerStation(reader.next());
				ticket.setOfficerStation(ticket.getOfficerStation().replace('_', ' '));

				//Assigning particular drivers' issueDueDate attribute based on ticket attribute values
				if (this.trn.equals(ticket.getDriverTrn())) {
				    LocalDate issueDueDate = LocalDate.of(
				        ticket.getIssueDueDate().getYear(),
				        ticket.getIssueDueDate().getMonth(),
				        ticket.getIssueDueDate().getDay()
				    );

				    // Calculate Payment Due Date
				    LocalDate paymentDueDate = issueDueDate.plusDays(21);
				    this.paymentDueDate = new Date(paymentDueDate.getDayOfMonth(), paymentDueDate.getMonthValue(), paymentDueDate.getYear());

				    LocalDate today = LocalDate.now();
				    String status;

					//Ticket fine amount was already paid
				    if (ticket.getFineAmount() == 0) {
				        status = "Paid";
				        ticket.setOfficerStation("N/A");
				       		        
				    //Assign court date if tickets was issued and the due date had passed (ticket not yet paid)
				    } else if (ticket.getFineAmount() > 0 && paymentDueDate.isBefore(today)) {
				        // Calculate Court Date
				        LocalDate courtDate = paymentDueDate.plusDays(5);
				        this.courtDate = new Date(courtDate.getDayOfMonth(), courtDate.getMonthValue(), courtDate.getYear());

				        status = "You have a Court Date";

				        if (courtDate.isBefore(today)) {
				            status = "Warrant Outstanding";
				        }
				        
				    } else {
				        status = "Unpaid";
				        ticket.setOfficerStation("N/A");
				    }
				    
				    //Display ticket and other related necessary information
					System.out.println("Issue Date: "+ ticket.getIssueDueDate().toString() + "\nPayment Due Date: "
					+ this.paymentDueDate.toString()+ "\nTicket Number: "+ ticket.getTicketNo()+ "\nOffence Code: "
							+ ticket.getOffenceCode()+ "\nOffence Description: "+ ticket.getOffenceDescription()
							+ "\nFine Amount: $"+ ticket.getFineAmount()+ "\nStatus: "+ status+ "\nCourt Date: "
							+ this.courtDate+ "\nCourt Location: "+ ticket.getOfficerStation()+ " Parish Court");
				    System.out.println("-------------------------------------------------------------------------------------");
				}
			}

	        System.out.println("\nTotal Unpaid Tickets: " + this.totalUnpaidTickets);
	        System.out.println("\nTotal Fine Amount: $" + this.totalFineAmount);

	        reader.close();
	        } catch (FileNotFoundException e) {
	            System.err.println("Error: File not found.");
	        }
	}
	  
	public void onlinePayment() { //allows users to make payment for their tickets
	    Scanner input = new Scanner(System.in);
	    int selectedTicketNo = 0;
	    Ticket selectedTicket = null;
	    double oldFineAmount = 0.0;
	    Ticket ticket= new Ticket();
	    String record= null;
	    File origin= new File("ticketDatabase.txt");
	    File made= new File("ticketTemp.txt");
	    
	    //Accepting ticket number for search to match to make online payment
	    System.out.print("Enter the ticket Number you want to pay: ");
	    selectedTicketNo= (int) input.nextInt();
	    
		try (Scanner reader = new Scanner(origin)) {
		    	//read record from ticket file
				while (reader.hasNext()) {
				    ticket.setTicketNo((int) reader.nextInt());
					ticket.setDriverTrn(reader.next());
		
					// Read Issue Due Date
					int d = (int) reader.nextInt();
					int m = (int) reader.nextInt();
					int y = (int) reader.nextInt();
					ticket.setIssueDueDate(new Date(d, m, y));
		
					ticket.setOffenceCode(reader.next());
					ticket.setOffenceDescription(reader.next());
					ticket.setFineAmount((double) reader.nextDouble());
					ticket.setLicensePlateNo(reader.next());
		
					String f = reader.next();
					String mi = reader.next();
					String l = reader.next();
					ticket.setDriverFullName(new Name(f, mi, l));
		
					d = (int) reader.nextInt();
					m = (int) reader.nextInt();
					y = (int) reader.nextInt();
					ticket.setDriverDob(new Date(d, m, y));
		
					ticket.setDriverAddress(reader.next());
					ticket.setDriverContact(reader.next());
					ticket.setBadgeNum(reader.next());
		
					f = reader.next();
					mi = reader.next();
					l = reader.next();
					ticket.setOfficerFullName(new Name(f, mi, l));
					ticket.setOfficerStation(reader.next());
					ticket.setOfficerStation(ticket.getOfficerStation());
		
					//Update the fine amount to indicate that payment has been made
					if (selectedTicketNo== ticket.getTicketNo()) {
						selectedTicket= ticket;
						oldFineAmount= ticket.getFineAmount();
						ticket.setFineAmount(00000.0);
					}
					
					try(FileWriter rote= new FileWriter(made, true)) {//write the new updated ticket information to a temporary file
						//Write to new record
						record = ticket.getTicketNo()  + "\t" + ticket.getDriverTrn()   + "\t" + ticket.getIssueDueDate().getDay()  + " " + 
								ticket.getIssueDueDate().getMonth() + " " + ticket.getIssueDueDate().getYear()  + "\t" + ticket.getOffenceCode()
								+ "\t" + ticket.getOffenceDescription()  + "\t" + String.format("%.2f", ticket.getFineAmount())  + "\t" + ticket.getLicensePlateNo()
								+ "\t" + ticket.getDriverFullName().toString()  + "\t" + ticket.getDriverDob().getDay()  + " " + 
								ticket.getDriverDob().getMonth()  + " " + ticket.getDriverDob().getYear()  + "\t" + ticket.getDriverAddress()  
								+ "\t" + ticket.getDriverContact()  + "\t" + ticket.getBadgeNum()  + "\t" + ticket.getOfficerFullName().toString()  
								+ "\t" + ticket.getOfficerStation()+ "\n";
		        
						rote.write(record);
					} catch (IOException e) {
						e.printStackTrace();
					}
									
				}
				
			} catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		
		//delete old record and replace with new one indicating the changes made in payment i.e. fine amount
		origin.delete();
		made.renameTo(origin);
		
	    updateDriverDatabase(selectedTicket, oldFineAmount);
	}
	
	public void viewPayable()//view tickets that are not due
	{
		Ticket ticket= new Ticket();
		boolean due= false;
	    
	    try (Scanner reader = new Scanner(new File("ticketDatabase.txt"))) { //read from the ticket file
	    	
	    	System.out.println("\nALL YOUR TICKETS THAT HAVE NOT PASS DUE WILL SHOW UP BELOW IF YOU HAVE ANY\n");
	    				
			while (reader.hasNext()) {
				
				try {
				    ticket.setTicketNo((int) reader.nextInt());
					ticket.setDriverTrn(reader.next());
		
					// Read Issue Due Date
					int d = (int) reader.nextInt();
					int m = (int) reader.nextInt();
					int y = (int) reader.nextInt();
					ticket.setIssueDueDate(new Date(d, m, y));
		
					ticket.setOffenceCode(reader.next());
					ticket.setOffenceDescription(reader.next());
					ticket.setFineAmount((double) reader.nextDouble());
					ticket.setLicensePlateNo(reader.next());
		
					String f = reader.next();
					String mi = reader.next();
					String l = reader.next();
					ticket.setDriverFullName(new Name(f, mi, l));
		
					d = (int) reader.nextInt();
					m = (int) reader.nextInt();
					y = (int) reader.nextInt();
					ticket.setDriverDob(new Date(d, m, y));
		
					ticket.setDriverAddress(reader.next().replace('_', ' '));
					ticket.setDriverContact(reader.next());
					ticket.setBadgeNum(reader.next());
		
					f = reader.next();
					mi = reader.next();
					l = reader.next();
					ticket.setOfficerFullName(new Name(f, mi, l));
					ticket.setOfficerStation(reader.next());
					ticket.setOfficerStation(ticket.getOfficerStation().replace('_', ' '));
		
					if (this.trn.equals(ticket.getDriverTrn())) { //checks if trn read from the file is equal to the user entered one
					    
					    //chnages issueDueDate to a local date to do the calculation below
					    LocalDate issueDueDate = LocalDate.of( 
					        ticket.getIssueDueDate().getYear(),
					        ticket.getIssueDueDate().getMonth(),
					        ticket.getIssueDueDate().getDay()
					    );
		
					    // Calculate Payment Due Date
					    LocalDate paymentDueDate = issueDueDate.plusDays(21);
					    this.paymentDueDate = new Date(paymentDueDate.getDayOfMonth(), paymentDueDate.getMonthValue(), paymentDueDate.getYear());
		
					    LocalDate today = LocalDate.now();
					    
					    if (ticket.getFineAmount() > 0 && today.isBefore(paymentDueDate)) {//check if ticket is not due then print
					        
					        System.out.println(ticket.toString());
						    System.out.println("-------------------------------------------------------------------------------------");
						    due= true;
					    } 
					}
				}catch (InputMismatchException e) {
			        System.err.println("Error reading file: " + e.getMessage());
			        reader.next(); // Skip invalid token
			    }
			}
			
			if(!due) {
			    System.out.println("You don't have any past due tickets!");
			}
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	private void updateDriverDatabase(Ticket selectedTicket, double oldFineAmount) { //updates driver database with info received from the method above(viewPayable)
	    File original = new File("driverDatabase.txt");
	    File temp = new File("tempDriver.txt");
	
	    try (Scanner origScanner = new Scanner(original);//reads from the driver file
	         FileWriter tempFile = new FileWriter(temp)) {
	        while (origScanner.hasNext()) {
	            // Read the driver's data
	            String driverTrn = origScanner.next();
	            String firstName = origScanner.next();
	            String middleName = origScanner.next();
	            String lastName = origScanner.next();
	
	            // Read the driver's date of birth
	            int dobDay = origScanner.nextInt();
	            int dobMonth = origScanner.nextInt();
	            int dobYear = origScanner.nextInt();
	
	            // Read the driver's address and contact information
	            String address = origScanner.next();
	            String contactNo = origScanner.next();
	
	            int paymentDay = origScanner.nextInt();
	            int paymentMonth = origScanner.nextInt();
	            int paymentYear = origScanner.nextInt();
	
	            int courtDay = origScanner.nextInt();
	            int courtMonth = origScanner.nextInt();
	            int courtYear = origScanner.nextInt();
	
	            String courtLocale = origScanner.next();
	
	            // Read the total unpaid tickets and fine amount
	            int totalUnpaidTickets = origScanner.nextInt();
	            double totalFineAmount = origScanner.nextDouble();
	
	            // Take data from driver file and save in ticket
	            if (selectedTicket.getDriverTrn().equals(driverTrn)) {
	                totalUnpaidTickets -= 1;
	                totalFineAmount -= oldFineAmount;
	            }
	
				//writes to the temporary file	
	            tempFile.write(driverTrn + "\t" + firstName + " " + middleName + " " + lastName + "\t"
	                    + dobDay + " " + dobMonth + " " + dobYear + "\t" + address + "\t" + contactNo + "\t"
	                    + paymentDay + " " + paymentMonth + " " + paymentYear + "\t" + courtDay + " " + courtMonth
	                    + " " + courtYear + "\t" + courtLocale + "\t" + totalUnpaidTickets + "\t" + totalFineAmount + "\n");
	        }
	
	        // Close the scanner and writer streams
	        origScanner.close();
	        tempFile.close();
	
	        // Delete the old driver file and rename the temporary file
	        if (original.delete()) {
	            if (temp.renameTo(original)) {
	                System.out.println("Driver file updated successfully.");
	            } else {
	                System.err.println("Failed to rename driver temp file.");
	            }
	        } else {
	            System.err.println("Failed to delete original driver file.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void checkPastDueDate()//view tickets that are passed their due date
	{
		Ticket ticket= new Ticket();
		boolean due= false;
        
        try (Scanner reader = new Scanner(new File("ticketDatabase.txt"))) { //reads from ticket file
        	
        	System.out.println("\nALL YOUR PAST DUE TICKETS WILL SHOW UP BELOW IF YOU HAVE ANY\n");
        				
			while (reader.hasNext()) {//continue reading as long as the file continues
			    ticket.setTicketNo((int) reader.nextInt());
				ticket.setDriverTrn(reader.next());

				// Read Issue Due Date
				int d = (int) reader.nextInt();
				int m = (int) reader.nextInt();
				int y = (int) reader.nextInt();
				ticket.setIssueDueDate(new Date(d, m, y));

				ticket.setOffenceCode(reader.next());
				ticket.setOffenceDescription(reader.next());
				ticket.setFineAmount((double) reader.nextDouble());
				ticket.setLicensePlateNo(reader.next());

				String f = reader.next();
				String mi = reader.next();
				String l = reader.next();
				ticket.setDriverFullName(new Name(f, mi, l));

				d = (int) reader.nextInt();
				m = (int) reader.nextInt();
				y = (int) reader.nextInt();
				ticket.setDriverDob(new Date(d, m, y));

				ticket.setDriverAddress(reader.next().replace('_', ' '));
				ticket.setDriverContact(reader.next());
				ticket.setBadgeNum(reader.next());

				f = reader.next();
				mi = reader.next();
				l = reader.next();
				ticket.setOfficerFullName(new Name(f, mi, l));
				ticket.setOfficerStation(reader.next());
				ticket.setOfficerStation(ticket.getOfficerStation().replace('_', ' '));

				if (this.trn.equals(ticket.getDriverTrn())) {
				    LocalDate issueDueDate = LocalDate.of(
				        ticket.getIssueDueDate().getYear(),
				        ticket.getIssueDueDate().getMonth(),
				        ticket.getIssueDueDate().getDay()
				    );

				    // Calculate Payment Due Date
				    LocalDate paymentDueDate = issueDueDate.plusDays(21);
				    this.paymentDueDate = new Date(paymentDueDate.getDayOfMonth(), paymentDueDate.getMonthValue(), paymentDueDate.getYear());

				    LocalDate today = LocalDate.now();
				    

				    String time = " "; 

				    if (ticket.getTicketNo() % 2 == 0) {
				        time = "9:00 am";
				    }
				    else {
				    	time = "1:00 pm";
				    }
				    
				    if (ticket.getFineAmount() == 0) {
				    	
				        
				    } else if (ticket.getFineAmount() > 0 && paymentDueDate.isBefore(today)) {
				        LocalDate courtDate = paymentDueDate.plusDays(5);
				        this.courtDate = new Date(courtDate.getDayOfMonth(), courtDate.getMonthValue(), courtDate.getYear()); 
				        
				        System.out.println(ticket.toString()+ "\nCourt Location: "+ ticket.getOfficerStation()+" Parish Court" +"\nCourt Date: "+ this.courtDate+ "\nCourt Time: "+ time+ "\n");
					    System.out.println("-------------------------------------------------------------------------------------");
					    due= true;
				    } 
				}
			}
			
			if(!due) {
			    System.out.println("You don't have any past due tickets!");

			}
			reader.close();
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public void viewTicket()//view all tickets payments not passed their due date
	{
		Ticket ticket= new Ticket();
		boolean due= false;
        
        try (Scanner reader = new Scanner(new File("ticketDatabase.txt"))) {
        	
        	System.out.println("\nALL YOUR TICKET PAYMENTS THAT HAVE NOT PASSED DUE WILL BE DISPLAYED HERE\n");
        				
			while (reader.hasNext()) {
			    ticket.setTicketNo((int) reader.nextInt());
				ticket.setDriverTrn(reader.next());

				// Read Issue Due Date
				int d = (int) reader.nextInt();
				int m = (int) reader.nextInt();
				int y = (int) reader.nextInt();
				ticket.setIssueDueDate(new Date(d, m, y));

				ticket.setOffenceCode(reader.next());
				ticket.setOffenceDescription(reader.next());
				ticket.setFineAmount((double) reader.nextDouble());
				ticket.setLicensePlateNo(reader.next());

				String f = reader.next();
				String mi = reader.next();
				String l = reader.next();
				ticket.setDriverFullName(new Name(f, mi, l));

				d = (int) reader.nextInt();
				m = (int) reader.nextInt();
				y = (int) reader.nextInt();
				ticket.setDriverDob(new Date(d, m, y));

				ticket.setDriverAddress(reader.next().replace('_', ' '));
				ticket.setDriverContact(reader.next());
				ticket.setBadgeNum(reader.next());

				f = reader.next();
				mi = reader.next();
				l = reader.next();
				ticket.setOfficerFullName(new Name(f, mi, l));
				ticket.setOfficerStation(reader.next());
				ticket.setOfficerStation(ticket.getOfficerStation().replace('_', ' '));

				if (this.trn.equals(ticket.getDriverTrn())) {
				    LocalDate issueDueDate = LocalDate.of(
				        ticket.getIssueDueDate().getYear(),
				        ticket.getIssueDueDate().getMonth(),
				        ticket.getIssueDueDate().getDay()
				    );

				    // Calculate Payment Due Date
				    LocalDate paymentDueDate = issueDueDate.plusDays(21);
				    this.paymentDueDate = new Date(paymentDueDate.getDayOfMonth(), paymentDueDate.getMonthValue(), paymentDueDate.getYear());

				    LocalDate today = LocalDate.now();
				    
				    
				    if (ticket.getFineAmount() == 0 && today.isBefore(paymentDueDate)) {
				        System.out.println(ticket.toString());
					    System.out.println("-------------------------------------------------------------------------------------");
					    due= true;
				    } 
				}
			}
			
			if(!due) {
			    System.out.println("You don't have any ticket payments that have not passed due :(\n");

			}
			reader.close();
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public void checkWarrant()//check if the driver has a warrant
	{
		Ticket ticket= new Ticket();
		boolean court= false;
        
        try (Scanner reader = new Scanner(new File("ticketDatabase.txt"))) {//reads from the ticket file
        				
			while (reader.hasNext()) {
			    ticket.setTicketNo((int) reader.nextInt());
				ticket.setDriverTrn(reader.next());

				// Read Issue Due Date
				int d = (int) reader.nextInt();
				int m = (int) reader.nextInt();
				int y = (int) reader.nextInt();
				ticket.setIssueDueDate(new Date(d, m, y));

				ticket.setOffenceCode(reader.next());
				ticket.setOffenceDescription(reader.next());
				ticket.setFineAmount((double) reader.nextDouble());
				ticket.setLicensePlateNo(reader.next());

				String f = reader.next();
				String mi = reader.next();
				String l = reader.next();
				ticket.setDriverFullName(new Name(f, mi, l));

				d = (int) reader.nextInt();
				m = (int) reader.nextInt();
				y = (int) reader.nextInt();
				ticket.setDriverDob(new Date(d, m, y));

				ticket.setDriverAddress(reader.next().replace('_', ' '));
				ticket.setDriverContact(reader.next());
				ticket.setBadgeNum(reader.next());

				f = reader.next();
				mi = reader.next();
				l = reader.next();
				ticket.setOfficerFullName(new Name(f, mi, l));
				ticket.setOfficerStation(reader.next());
				ticket.setOfficerStation(ticket.getOfficerStation().replace('_', ' '));

				if (this.trn.equals(ticket.getDriverTrn())) {//checks if enetered trn matches any in the file
				    LocalDate issueDueDate = LocalDate.of(
				        ticket.getIssueDueDate().getYear(),
				        ticket.getIssueDueDate().getMonth(),
				        ticket.getIssueDueDate().getDay()
				    );

				    // Calculate Payment Due Date
				    LocalDate paymentDueDate = issueDueDate.plusDays(21);
				    this.paymentDueDate = new Date(paymentDueDate.getDayOfMonth(), paymentDueDate.getMonthValue(), paymentDueDate.getYear());

				    LocalDate today = LocalDate.now();
				    
				    if (ticket.getFineAmount() > 0 && paymentDueDate.isBefore(today)) { //check if ticket is outstanding
				        // Calculate Court Date
				        LocalDate courtDate = paymentDueDate.plusDays(5);
				        this.courtDate = new Date(courtDate.getDayOfMonth(), courtDate.getMonthValue(), courtDate.getYear());

				        if (courtDate.isBefore(today)) {
				        	court= true;
				        	System.out.println("\n"+ ticket.getOfficerStation()+ " Police Station");
					        System.out.println("-------------------------------------------------------------------------------------");
				        }
				    }
				    
				}
			}
			
			if(court) {
				System.out.println("You have a warrant out for your arrest for not appearing in court!!!\nPlease turn yourself into the Police Station(s) listed above\n");
			}
			else {
				System.out.println("\nYou don't have a warrant out for your arrest!!!\n");
			}
			reader.close();
	        } catch (FileNotFoundException e) {
	            System.err.println("Error: File not found.");
	        }		
	}
	
	public void viewPaidTicket(){//view tickets that are paid
		Ticket ticket= new Ticket();
		boolean due= false;
        
        try (Scanner reader = new Scanner(new File("ticketDatabase.txt"))) {
        	
        	System.out.println("\nALL YOUR PAID TICKETS WILL BE DISPLAYED BELOW\n");
        				
			while (reader.hasNext()) {
			    ticket.setTicketNo((int) reader.nextInt());
				ticket.setDriverTrn(reader.next());

				// Read Issue Due Date
				int d = (int) reader.nextInt();
				int m = (int) reader.nextInt();
				int y = (int) reader.nextInt();
				ticket.setIssueDueDate(new Date(d, m, y));

				ticket.setOffenceCode(reader.next());
				ticket.setOffenceDescription(reader.next());
				ticket.setFineAmount((double) reader.nextDouble());
				ticket.setLicensePlateNo(reader.next());

				String f = reader.next();
				String mi = reader.next();
				String l = reader.next();
				ticket.setDriverFullName(new Name(f, mi, l));

				d = (int) reader.nextInt();
				m = (int) reader.nextInt();
				y = (int) reader.nextInt();
				ticket.setDriverDob(new Date(d, m, y));

				ticket.setDriverAddress(reader.next().replace('_', ' '));
				ticket.setDriverContact(reader.next());
				ticket.setBadgeNum(reader.next());

				f = reader.next();
				mi = reader.next();
				l = reader.next();
				ticket.setOfficerFullName(new Name(f, mi, l));
				ticket.setOfficerStation(reader.next());
				ticket.setOfficerStation(ticket.getOfficerStation().replace('_', ' '));

				if (this.trn.equals(ticket.getDriverTrn())) {
				    LocalDate issueDueDate = LocalDate.of(
				        ticket.getIssueDueDate().getYear(),
				        ticket.getIssueDueDate().getMonth(),
				        ticket.getIssueDueDate().getDay()
				    );

				    // Calculate Payment Due Date
				    LocalDate paymentDueDate = issueDueDate.plusDays(21);
				    this.paymentDueDate = new Date(paymentDueDate.getDayOfMonth(), paymentDueDate.getMonthValue(), paymentDueDate.getYear());				    
				    
				    if (ticket.getFineAmount() == 0) {
				        System.out.println(ticket.toString());
					    System.out.println("-------------------------------------------------------------------------------------");
					    due= true;
				    } 
				}
			}
			
			if(!due) {
			    System.out.println("You haven't paid any tickets!!!\n");			

			}
		reader.close();
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public void viewOutstandingTicket()//view any outstanding tickets
	{
		Ticket ticket= new Ticket();
		boolean due= false;
        
        try (Scanner reader = new Scanner(new File("ticketDatabase.txt"))) { //reads from the ticket file
        	
        	System.out.println("\nALL YOUR OUSTANDING TICKETS WILL BE DISPLAYED BELOW AND THE DUE DATE FOR PAYMENT\n");
        				
			while (reader.hasNext()) {
			    ticket.setTicketNo((int) reader.nextInt());
				ticket.setDriverTrn(reader.next());

				// Read Issue Due Date
				int d = (int) reader.nextInt();
				int m = (int) reader.nextInt();
				int y = (int) reader.nextInt();
				ticket.setIssueDueDate(new Date(d, m, y));

				ticket.setOffenceCode(reader.next());
				ticket.setOffenceDescription(reader.next());
				ticket.setFineAmount((double) reader.nextDouble());
				ticket.setLicensePlateNo(reader.next());

				String f = reader.next();
				String mi = reader.next();
				String l = reader.next();
				ticket.setDriverFullName(new Name(f, mi, l));

				d = (int) reader.nextInt();
				m = (int) reader.nextInt();
				y = (int) reader.nextInt();
				ticket.setDriverDob(new Date(d, m, y));

				ticket.setDriverAddress(reader.next().replace('_', ' '));
				ticket.setDriverContact(reader.next());
				ticket.setBadgeNum(reader.next());

				f = reader.next();
				mi = reader.next();
				l = reader.next();
				ticket.setOfficerFullName(new Name(f, mi, l));
				ticket.setOfficerStation(reader.next());
				ticket.setOfficerStation(ticket.getOfficerStation().replace('_', ' '));
				if (this.trn.equals(ticket.getDriverTrn())) {
				    LocalDate issueDueDate = LocalDate.of(
				        ticket.getIssueDueDate().getYear(),
				        ticket.getIssueDueDate().getMonth(),
				        ticket.getIssueDueDate().getDay()
				    );

				    // Calculate Payment Due Date
				    LocalDate paymentDueDate = issueDueDate.plusDays(21);
				    this.paymentDueDate = new Date(paymentDueDate.getDayOfMonth(), paymentDueDate.getMonthValue(), paymentDueDate.getYear());
				    
				    
				    if (ticket.getFineAmount()> 0) { //check if fine amount is greater than 0 meaning that they still have outstanding tickets
				        System.out.println(ticket.toString()+ "\nDue Date for Payment: "+ this.paymentDueDate);
					    System.out.println("-------------------------------------------------------------------------------------");
					    due= true;
				    } 
				}
			}
			if(!due) {
			    System.out.println("You don't have any outstanding tickets!!!\n");
				
			}
		reader.close();
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
	}
	
	@Override
	public String toString() { //tostring method to display information about driver wherever called
		return "Driver's Information" + "\nTRN: " + trn+ "\nName: " + fullName+ "\nDate of Birth: " + dob + "\nAddress: " + address + "\nContact Number: " + contactNo + "\nPayment Due Date: "
				+ paymentDueDate + "\nCourt Date: " + courtDate + "\nCourt Location: " + courtLocation
				+ "\nTotal Unpaid Tickets: " + totalUnpaidTickets + "\nTotal Fine Amount: $" + totalFineAmount;
	}
	
	
	
	
}
