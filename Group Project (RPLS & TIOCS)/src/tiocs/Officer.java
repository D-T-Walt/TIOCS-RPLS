/*Authors:
 		Olivia McFarlane- 2301555
 		Kemone Laws- 2109446*/
package tiocs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import rpls.Date;
import rpls.Driver;
import rpls.Name;
import rpls.User;

public class Officer extends User {
	private String badgeNum;
	private String policeStation; 
	
	//primary constructor
	public Officer(Name fullName, String trn, String role, String badgeNum, String policeStation){
		super(fullName, trn, role);
		this.badgeNum = badgeNum;
		this.policeStation = policeStation;
	}
	
	public Officer(){//default constructor
		super();
		this.badgeNum = "0000";
		this.policeStation = "parish";
	}
	
	public Officer(Officer officer){//copy constructor
		super(officer);
		this.badgeNum = officer.badgeNum;
		this.policeStation = officer.policeStation;
	}
	
	//getters
	public String getBadgeNum(){
		return badgeNum;
	}
	
	public String getPoliceStation(){
		return policeStation;
	}
	
	public void setBadgeNum(String badgeNum){
		this.badgeNum = badgeNum;
	}
	
	//setters
	public void setPoliceStation(String policeStation){
		this.policeStation = policeStation;
	}

	public boolean log() {//allows the user to login
		//Allows officer to log in to the system based on TRN number
		File file = new File("officerDatabase.txt");
    	Scanner input= new Scanner(System.in);
    	String searchTrn;
    	
    	System.out.println("\nEnter your TRN Officer: ");
    	searchTrn= input.next();
    	
    	//Check if the file exists
        if (!file.exists()) {
            System.err.println("Error: The file does not exist.");
            input.close();
            return false;
        }

        //Open the file for reading
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
            	this.trn= fileScanner.next();
            	
            	String f= fileScanner.next();
            	String mi= fileScanner.next();
            	String l= fileScanner.next();
            	this.fullName= new Name(f, mi, l);
            	
            	this.badgeNum= fileScanner.next();
            	
				this.policeStation= fileScanner.next();
				this.policeStation= policeStation.replace('_', ' ');
          
				if(searchTrn.equals(trn)) {
					return true;
				}
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
        }
        
		return false;
	}
	
	public void addOfficer() { //add officer to the database/file
        Scanner input = new Scanner(System.in);
	
		//Accepting Officer's attribute information
        try {
        	
        	try{
	        	System.out.println("Enter the officer's TRN (e.g. \""+ this.trn+ "\"): ");
	            this.trn = input.next();
            } catch (Exception e) {
				System.out.println(e.getMessage());
			}
        	
        	System.out.println("Enter officer's First Name");
        	this.fullName.setFirstName(input.next());
        	
        	System.out.println("Enter officer's Middle Name");
        	this.fullName.setMiddleName(input.next());
        	
        	System.out.println("Enter officer's Last Name");
        	this.fullName.setLastName(input.next());
        	
            
            System.out.println("Enter the officer's Badge Number (e.g. \""+ this.badgeNum+ "\"): ");
            this.badgeNum = input.next(); 

            input.nextLine();
            System.out.println("Enter the Officer's Police Station Parish (e.g. \""+ this.policeStation+ "\") (Put a dot '.' after St followed by a space): ");
            this.policeStation = input.nextLine();
            this.policeStation= this.policeStation.replace(' ', '_');

            // Writing the officer's data to the officer file
            try (FileWriter write = new FileWriter(new File("officerDatabase.txt"), true)) {
            	
                String record = trn +"\t"+ fullName.toString()+"\t"+ badgeNum + "\t" + policeStation+ '\n';
                
                write.write(record);
                write.close();
                
                System.out.println("Officer data saved successfully.");
            } catch (IOException e) {
                System.err.println("Error while writing to file: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }
    }
	
	public void add()// add ticket to the database; assigns ticket to a driver
	{
		Ticket tic= new Ticket();
		
		Scanner input = new Scanner(System.in);
		int d= 0;
		int m=0;
		int y= 0;
		
		String fi= "";
		String mi= "";
		String la= "";
		
		try{
			System.out.println("Enter Driver TRN: ");
			tic.setDriverTrn(input.next());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		//Assigning IssueDate attribute to current date(today's date)
		LocalDate today = LocalDate.now();		
				
		int day = today.getDayOfMonth();
		int month = today.getMonthValue();
		int year = today.getYear();
		
		tic.setIssueDueDate(new Date(day, month, year));
    	
    	try{
			System.out.println("\nOffence Codes- \nO1 - Minimal\nO2 - Minor\nO3 - Major\nO4 - Critical");
			System.out.println("\nEnter Offence Code: ");
			tic.setOffenceCode(input.next());
			
			//Testing of Offence code input user ensters to ensure it's valididty
			while(!tic.getOffenceCode().equals("O1") && !tic.getOffenceCode().equals("O2") && !tic.getOffenceCode().equals("O3") && !tic.getOffenceCode().equals("O4"))
			{
				System.out.println("Invalid offence code. \nPlease reenter the offence code: ");
			    System.out.println("\nEnter Offence Code: ");
				tic.setOffenceCode(input.next());
			}
			
			System.out.println("Accepted Suitable Offence code");
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		try{
			System.out.println("\nEnter Offence Description (NOTE: Use one word): ");
			tic.setOffenceDescription(input.next());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		//Assigning Fine Amount based on Offence Code
		try{
			if (tic.getOffenceCode().equals("O1")) {
			    tic.setFineAmount(3000.00);  
			} else if (tic.getOffenceCode().equals("O2")) {
			    tic.setFineAmount(10000.00);  
			} else if (tic.getOffenceCode().equals("O3")) {
			    tic.setFineAmount(30000.00);  
			} else if (tic.getOffenceCode().equals("O4")) {
			    tic.setFineAmount(50000.00);  
			}
		} catch (NumberFormatException er) {
			System.err.println(er.getMessage());
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		try{
			System.out.println("\nEnter Licence Plate Number (e.g.: 0000AB): ");
			tic.setLicensePlateNo(input.next());
			while(tic.getLicensePlateNo().length() != 6){
				System.out.println("Invalid license plate number. It must be exactly 6 characters long.");
			    System.out.println("\nRe-enter Licence Plate Number: ");
			    tic.setLicensePlateNo(input.next());
		    }
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		//Read driver info from driver based on trn
		Driver dri= new Driver();
		
		File file = new File("driverDatabase.txt");
		
		try(Scanner fileScanner = new Scanner(file)){
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
				
				//take data from driver file and save in ticket
				if(dri.getTrn().equals(tic.getDriverTrn()))
				{
					tic.setDriverFullName(dri.getFullName());
					tic.setDriverDob(dri.getDob());
					tic.setDriverAddress(dri.getAddress());
					tic.setDriverContact(dri.getContactNo());
				}
			}
			
			fileScanner.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		
		//Setting badgenumber, fullname, police station, officer station attributes in ticket to values retrived from corresponding attributes in the Officer file
		tic.setBadgeNum(this.badgeNum);
		
		tic.setOfficerFullName(this.fullName);
		
		tic.setOfficerStation(this.policeStation);	
		
		tic.setOfficerStation(tic.getOfficerStation().replace('_', ' ')); 

		try {
			FileWriter write = new FileWriter(new File("ticketDatabase.txt"), true);
			int lastTicketNo= 0;
			
			try{
				Scanner reader = new Scanner(new File("ticketDatabase.txt"));
				
				//Reading record from Ticket file
				while(reader.hasNext()){
					lastTicketNo= (int) reader.nextInt();
					reader.next();
					reader.nextInt();
					reader.nextInt();
					reader.nextInt();
					reader.next();
					reader.next();
					reader.nextDouble();
					reader.next();
					reader.next();
					reader.next();
					reader.next();
					reader.nextInt();
					reader.nextInt();
					reader.nextInt();
					reader.next();
					reader.next();
					reader.next();
					reader.next();
					reader.next();
					reader.next();
					reader.next();
				}
				reader.close();
			}catch (FileNotFoundException e){
				lastTicketNo= 0;
			}
			
			//Assigning Ticket number based on the incrementing starting value 0			
			tic.setTicketNo(lastTicketNo+ 1);
			
			boolean confirm = false;
			int opt;
			
			//Allowing user to select which attribute they would like to update
			while(!confirm){
				System.out.println(tic.toString().replace('_', ' '));
				System.out.println("Confirm \n1. Yes \n2. No \nEnter 1 or 2: ");
				opt = (int) input.nextInt();
				if(opt == 1){
					confirm = true;
				}else{
					System.out.println("\nSelect the option you would like to change\n1. Driver TRN\n2. Offence Code\n3. Offence Description\n4. License Plate Number\nEnter Option:");
					opt = (int) input.nextInt();
					
					switch(opt){
						case 1:
							try{
								System.out.println("\nEnter Driver TRN: ");
								tic.setDriverTrn(input.next());
							} catch (Exception e) {
								System.err.println(e.getMessage());
							}
							
							File file2 = new File("driverDatabase.txt");
							try(Scanner fileScanner2 = new Scanner(file2)){
								while(fileScanner2.hasNext()){
									dri.setTrn(fileScanner2.next());
									fi= fileScanner2.next();     //Fullname
									mi= fileScanner2.next();
									la= fileScanner2.next();
									dri.setFullName(new Name(fi, mi, la));
									d=fileScanner2.nextInt();     //DOB
									m= fileScanner2.nextInt();
									y= fileScanner2.nextInt();
									dri.setDob(new Date(d, m, y));
									dri.setAddress(fileScanner2.next());
									dri.setContactNo(fileScanner2.next());
									d=fileScanner2.nextInt();     //PaymentDueDate
									m= fileScanner2.nextInt();
									y= fileScanner2.nextInt();
									dri.setPaymentDueDate(new Date(d, m, y));   
									d=fileScanner2.nextInt();     //CourtDate
									m= fileScanner2.nextInt();
									y= fileScanner2.nextInt();
									dri.setCourtDate(new Date(d, m, y));        
									dri.setCourtLocation(fileScanner2.next());
									dri.setTotalUnpaidTickets(fileScanner2.nextInt());
									dri.setTotalFineAmount(fileScanner2.nextDouble());
									
									//take data from driver file and save in ticket
									if(dri.getTrn().equals(tic.getDriverTrn()))
									{
										tic.setDriverFullName(dri.getFullName());
										tic.setDriverDob(dri.getDob());
										tic.setDriverAddress(dri.getAddress());
										tic.setDriverContact(dri.getContactNo());
									}
								}
								fileScanner2.close();
							}
						
							break;
							
						case 2:
							try{
								System.out.println("\nOffence Codes- \nO1- Minimal\nO2- Minor\nO3- Major\nO4- Critical");
								System.out.println("\nEnter Offence Code: ");
								tic.setOffenceCode(input.next());
								
								if(tic.getOffenceCode().equals("O1") ||tic.getOffenceCode().equals("O2") || tic.getOffenceCode().equals("O3") || tic.getOffenceCode().equals("O4"))
								{
									System.out.println("Accepted Suitable Offence code");
								}
								else {
								    System.out.println("Invalid offence code. \nPlease reenter the offence code: ");
								    System.out.println("\nEnter Offence Code: ");
									tic.setOffenceCode(input.next());
								}
							} catch (Exception e) {
								System.err.println(e.getMessage());
							}
							
							try{
								if (tic.getOffenceCode().equals("O1")) {
								    tic.setFineAmount(3000.00);  
								} else if (tic.getOffenceCode().equals("O2")) {
								    tic.setFineAmount(10000.00);  
								} else if (tic.getOffenceCode().equals("O3")) {
								    tic.setFineAmount(30000.00);  
								} else if (tic.getOffenceCode().equals("O4")) {
								    tic.setFineAmount(50000.00);  
								}
							} catch (NumberFormatException er) {
								System.err.println(er.getMessage());
							}catch (Exception e) {
								System.err.println(e.getMessage());
							}
							break;
							
						case 3:
							try{
								System.out.println("\nEnter Offence Description: NOTE: Use one word: ");
								tic.setOffenceDescription(input.next());
							} catch (Exception e) {
								System.err.println(e.getMessage());
							}
							break;
							
						case 4:
							try{
								System.out.println("\nEnter Licence Plate Number (e.g.: 0000AB): ");
								tic.setLicensePlateNo(input.next());
								
								while(tic.getLicensePlateNo().length() != 6){
									System.out.println("Invalid license plate number. It must be exactly 6 characters long.");
								    System.out.println("\nRe-enter Licence Plate Number: ");
								    tic.setLicensePlateNo(input.next());
							    }
							} catch (Exception e) {
								System.err.println(e.getMessage());
							}
							break;
							
						default:
			                System.out.println("Invalid option. Please select a valid option.\n");
			                break;
					}
				}
			}
			
			//UPDATE
			File original = new File("driverDatabase.txt");
			File temp = new File ("tempDriver.txt");
			
			try(Scanner origScanner = new Scanner(original);
				FileWriter tempFile = new FileWriter(temp)){
				while(origScanner.hasNext()){
					// Read the driver's data
		            String driverTrn = origScanner.next();
		            String firstName = origScanner.next();
		            String middleName = origScanner.next();
		            String lastName = origScanner.next();
		            
		            // Read the driver's date of birth
		            int dobDay = (int) origScanner.nextInt();
		            int dobMonth = (int) origScanner.nextInt();
		            int dobYear = (int) origScanner.nextInt();
		            
		            // Read the driver's address and contact information
		            String address = origScanner.next();
		            String contactNo = origScanner.next();
		            
		            int paymentDay= (int) origScanner.nextInt();
		            int paymentMonth= (int) origScanner.nextInt();
		            int paymentYear= (int) origScanner.nextInt();
		            				
					 int courtDay= (int) origScanner.nextInt();
					 int courtMonth= (int) origScanner.nextInt();
					 int courtYear= (int) origScanner.nextInt();
					
					String courtLocale= origScanner.next();
		            
		            // Read the total unpaid tickets and fine amount
		            int totalUnpaidTickets = (int) origScanner.nextInt();
		            double totalFineAmount = (double) origScanner.nextDouble();
		            
					//take data from driver file and save in ticket
					if(driverTrn.equals(tic.getDriverTrn()))
					{
						totalUnpaidTickets += 1;
						totalFineAmount += tic.getFineAmount();
					}
					
					//write to the temporary file
					tempFile.write(driverTrn + "\t" + firstName + " " + middleName + " " + lastName + "\t"
            				  + dobDay + " " + dobMonth + " " + dobYear + "\t" + address + "\t" + contactNo + "\t"
							+ paymentDay + " " + paymentMonth + " " + paymentYear + "\t"+ courtDay + " " + courtMonth 
							+ " " + courtYear + "\t"+ courtLocale+ "\t"   + totalUnpaidTickets + "\t" + totalFineAmount+ "\n");
				}
				
				//delete old file and renme temporary file to the same as the old
				origScanner.close();
				tempFile.close();
				
				original.delete();
				temp.renameTo(original);
				
			}catch (FileNotFoundException e) {
		        System.err.println("\nDriver database file not found: " + e.getMessage());
		    } catch (IOException e) {
		        System.err.println("\nError while writing the updated driver data: " + e.getMessage());
		    }
			
			//Write record to new file
			String record = tic.getTicketNo()  + "\t" + tic.getDriverTrn()   + "\t" + tic.getIssueDueDate().getDay()  + " " + 
							tic.getIssueDueDate().getMonth() + " " + tic.getIssueDueDate().getYear()  + "\t" + tic.getOffenceCode()
							+ "\t" + tic.getOffenceDescription()  + "\t" + String.format("%.2f", tic.getFineAmount())  + "\t" + tic.getLicensePlateNo()
							+ "\t" + tic.getDriverFullName().toString()  + "\t" + tic.getDriverDob().getDay()  + " " + 
							tic.getDriverDob().getMonth()  + " " + tic.getDriverDob().getYear()  + "\t" + tic.getDriverAddress()  
							+ "\t" + tic.getDriverContact()  + "\t" + tic.getBadgeNum()  + "\t" + tic.getOfficerFullName().toString()  
							+ "\t" + tic.getOfficerStation()+ "\n";
	        
			write.write(record);
            // Close the file after performing operations
            write.close();
            System.out.println("\nTicket added.");
            
        } catch (IOException e) {
            // Handle any file I/O exceptions
            System.err.println("Error while handling the file: " + e.getMessage());
        }
	}
	
	
	public void check(){//Allow Officer to select which senario they want to check tickets for
		Scanner input = new Scanner(System.in);
		
		int opt=1;
		String searchDriT;
		String searchParish;
		
		Ticket tic= new Ticket();
		
		int d= 0;
		int m= 0;
		int y= 0;
		
		String fi= "";
		String mi= "";
		String la= "";
		
		boolean checkt = false;
		
		
		while (opt != 4){
			System.out.println("\nDriver Check Menu\n1. Check Unpaid Past Due Tickets\n2. Check Outstanding Tickets by Parish\n3. Check All Offenders with Unpaid Tickets\n4. Exit");
			opt = input.nextInt();
			
			switch(opt)
			{
				case 1:
					System.out.println("Checking Past Due Tickets...\n");
					
					System.out.println("Enter the driver's TRN: ");
					searchDriT = input.next();
					
					//Read the record from ticket file
					File ticketFile = new File("ticketDatabase.txt");
					//Scanner fileScanner1 = null;
					
					try(Scanner fileScanner1 = new Scanner(ticketFile))
					{
						while (fileScanner1.hasNext()) 
						{
							tic.setTicketNo(fileScanner1.nextInt());
							tic.setDriverTrn(fileScanner1.next());
							d=fileScanner1.nextInt();                          //issueDueDate
							m= fileScanner1.nextInt();
							y= fileScanner1.nextInt();
							tic.setIssueDueDate(new Date(d, m, y)); 
							tic.setOffenceCode(fileScanner1.next());
							tic.setOffenceDescription(fileScanner1.next());
							tic.setFineAmount(fileScanner1.nextDouble());
							tic.setLicensePlateNo(fileScanner1.next());
							fi= fileScanner1.next();                          //driverFullName
							mi= fileScanner1.next();
							la= fileScanner1.next();
							tic.setDriverFullName(new Name(fi, mi, la));
							d=fileScanner1.nextInt();                           //driverDob
							m= fileScanner1.nextInt();
							y= fileScanner1.nextInt();
							tic.setDriverDob(new Date(d, m, y));
							tic.setDriverAddress(fileScanner1.next());
							tic.setDriverContact(fileScanner1.next());
							tic.setBadgeNum(fileScanner1.next());
							fi= fileScanner1.next();                          //officerFullName
							mi= fileScanner1.next();
							la= fileScanner1.next();
							tic.setOfficerFullName(new Name(fi, mi, la));
							tic.setOfficerStation(fileScanner1.next());
						
							//Checking the tickets for the specific driver searched for
							if(tic.getDriverTrn().equals(searchDriT))
							{
								//Today's Date
								LocalDate today = LocalDate.now();
								
								//Convert issueDueDate to LocalDate
						        LocalDate issueDueDate = LocalDate.of(tic.getIssueDueDate().getYear(), tic.getIssueDueDate().getMonth(), tic.getIssueDueDate().getDay());
						        
						        //PaymentDate - 21 days after issue date
						        LocalDate paymentDueDate = issueDueDate.plusDays(21);
						        
						        //Determining if driver has an overdue, unpaid/outstanding ticket
						        if(paymentDueDate.isBefore(today) && tic.getFineAmount() != 0.0)
						        {
									checkt = true;
								}
							}
						}
						
						//Close File
						fileScanner1.close();
						
						if(checkt == true)
						{
							System.out.println("\nThis Driver has an overdue ticket.");
						} else {
							System.out.println("\nThis Driver does not have an overdue ticket.");
						}
					    break;
					}catch (FileNotFoundException e1) {
						System.out.println(e1.getMessage());
					}
					
				case 2:
					System.out.println("Checking Outstanding Ticket by Parish...\n");
					
					System.out.println("Enter the Officer's Parish (Put a dot '.' after St followed by a space): ");
					searchParish = input.next();
					searchParish= searchParish.replace(' ', '_');
					
					//Read the record from ticket file
					File ticketFile1 = new File("ticketDatabase.txt");
					
					try(Scanner fileScanner3 = new Scanner(ticketFile1))
					{
						boolean found = false;
						
						while (fileScanner3.hasNext()) 
						{
							tic.setTicketNo(fileScanner3.nextInt());
							tic.setDriverTrn(fileScanner3.next());
							d=fileScanner3.nextInt();                          //issueDueDate
							m= fileScanner3.nextInt();
							y= fileScanner3.nextInt();
							tic.setIssueDueDate(new Date(d, m, y)); 
							tic.setOffenceCode(fileScanner3.next());
							tic.setOffenceDescription(fileScanner3.next());
							tic.setFineAmount(fileScanner3.nextDouble());
							tic.setLicensePlateNo(fileScanner3.next());
							fi= fileScanner3.next();                          //driverFullName
							mi= fileScanner3.next();
							la= fileScanner3.next();
							tic.setDriverFullName(new Name(fi, mi, la));
							d=fileScanner3.nextInt();                           //driverDob
							m= fileScanner3.nextInt();
							y= fileScanner3.nextInt();
							tic.setDriverDob(new Date(d, m, y));
							tic.setDriverAddress(fileScanner3.next());
							
							tic.setDriverContact(fileScanner3.next());
							tic.setBadgeNum(fileScanner3.next());
							fi= fileScanner3.next();                          //officerFullName
							mi= fileScanner3.next();
							la= fileScanner3.next();
							tic.setOfficerFullName(new Name(fi, mi, la));
							tic.setOfficerStation(fileScanner3.next());
							
							//Check if the officer station is the parish name searched for
		                    if (tic.getOfficerStation().toLowerCase().contains(searchParish.toLowerCase())) 
		                    {
		                        found = true;
		                        
		                        //Today's Date
								LocalDate today = LocalDate.now();
								
								//Convert issueDueDate to LocalDate
						        LocalDate issueDueDate = LocalDate.of(tic.getIssueDueDate().getYear(), tic.getIssueDueDate().getMonth(), tic.getIssueDueDate().getDay());
						        
						        /*
						        //Calculate the number of days between the issue date and today
	        					long daysBetween = ChronoUnit.DAYS.between(issueDueDate, today);
	        					*/
	        					
	        					//PaymentDate - 21 days after issue date
						        LocalDate paymentDueDate = issueDueDate.plusDays(21);
						        
						        //Calculate the number of days between the payment date and today
	        					long daysBetween = ChronoUnit.DAYS.between(paymentDueDate, today);
	        					
	        					//Display status of ticket(outstanding and overdue or outstanding and not yet due)
	                        	String overdueStatus = paymentDueDate.isBefore(today) ? "Outstanding and Overdue by " + daysBetween + " days" : "Outstanding But Not Yet Due";
	                        	
	                        	//Display information for Outstanding Tickets
	                        	if(tic.getFineAmount() != 0.0)
	                        	{
		        					//Print ticket details
			                        System.out.println("\nTicket No: " + tic.getTicketNo());
			                        System.out.println("Driver TRN: " + tic.getDriverTrn());
			                        System.out.println("Driver: " + tic.getDriverFullName().toString());
			                        System.out.println("Issue Date: " + tic.getIssueDueDate().toString());
			                        System.out.println("Offence: " + tic.getOffenceDescription());
			                        System.out.println("Fine Amount: $" + tic.getFineAmount());
			                        System.out.println("License Plate: " + tic.getLicensePlateNo());
			                        System.out.println("Address: " + tic.getDriverAddress().replace('_', ' '));
			                        System.out.println("Status: " + overdueStatus);
			                        System.out.println("----------------------------------");
		                        }
		                    }    
						}
						//Close File
						fileScanner3.close();
						
						if (!found) 
						{
	                    	System.out.println("No tickets were found for the specified parish.");
		                }
		                break;
					} catch (FileNotFoundException e1) {
						System.out.println(e1.getMessage());
					}
					
					break;
				case 3:
					System.out.println("Checking All Outstanding Tickets...\n");
					
					//Read the record from ticket file
					File ticketFile4 = new File("ticketDatabase.txt");
					
					try(Scanner fileScanner4 = new Scanner(ticketFile4))
					{
						boolean found = false;
						
						while (fileScanner4.hasNext()) 
						{
							tic.setTicketNo(fileScanner4.nextInt());
							tic.setDriverTrn(fileScanner4.next());
							d=fileScanner4.nextInt();                          //issueDueDate
							m= fileScanner4.nextInt();
							y= fileScanner4.nextInt();
							tic.setIssueDueDate(new Date(d, m, y)); 
							tic.setOffenceCode(fileScanner4.next());
							tic.setOffenceDescription(fileScanner4.next());
							tic.setFineAmount(fileScanner4.nextDouble());
							tic.setLicensePlateNo(fileScanner4.next());
							fi= fileScanner4.next();                          //driverFullName
							mi= fileScanner4.next();
							la= fileScanner4.next();
							tic.setDriverFullName(new Name(fi, mi, la));
							d=fileScanner4.nextInt();                           //driverDob
							m= fileScanner4.nextInt();
							y= fileScanner4.nextInt();
							tic.setDriverDob(new Date(d, m, y));
							tic.setDriverAddress(fileScanner4.next());
							tic.setDriverContact(fileScanner4.next());
							tic.setBadgeNum(fileScanner4.next());
							fi= fileScanner4.next();                          //officerFullName
							mi= fileScanner4.next();
							la= fileScanner4.next();
							tic.setOfficerFullName(new Name(fi, mi, la));
							tic.setOfficerStation(fileScanner4.next());
							
							//Today's Date
							LocalDate today = LocalDate.now();
							
							//Convert issueDueDate to LocalDate
					        LocalDate issueDueDate = LocalDate.of(tic.getIssueDueDate().getYear(), tic.getIssueDueDate().getMonth(), tic.getIssueDueDate().getDay());
					        
	    					//PaymentDate - 21 days after issue date
					        LocalDate paymentDueDate = issueDueDate.plusDays(21);
					        
					        // Check if the ticket is overdue
		                    if (tic.getFineAmount() != 0.0) 
		                    {
		                        found = true;
		                        
		                        // Print offender details
		                        System.out.println("\nTicket No: " + tic.getTicketNo());
		                        System.out.println("Driver TRN: " + tic.getDriverTrn());
		                        System.out.println("Driver Name: " + tic.getDriverFullName().toString());
		                        System.out.println("Driver Address: " + tic.getDriverAddress().replace('_', ' '));
		                        System.out.println("Status: Outstanding");
		                        System.out.println("----------------------------------------");
		                    }
						}
						//Close File
						fileScanner4.close();
						
						if (!found) {
		                    System.out.println("No outstanding tickets found.");
		                }
		                break;
				
					}catch (FileNotFoundException e1) {
						System.out.println(e1.getMessage());
					}
					
					break;
				//Invalid option to exit
				case 4:
					return;
				default:
	                System.out.println("\nInvalid option. Please select a valid option.");
	                break;
			}
		}    	
	}
	
	public void viewAll()//view all tickets sorted by the parish
	{
		Scanner input = new Scanner(System.in);
		
		int opt;
		int searchTic;
		
		List<Ticket> ticketList = new ArrayList<>();  // List to hold tickets
		
		int d= 0;
		int m= 0;
		int y= 0;
		
		String fi= "";
		String mi= "";
		String la= "";
		
		boolean found = false;
		
		System.out.println("\nView All Tickets Sorted by Parish");
				
		File ticketFile = new File("ticketDatabase.txt");
					
		try(Scanner viewAll = new Scanner(ticketFile))
		{	
			while (viewAll.hasNext()) 
			{
				Ticket tic= new Ticket(); // Create a new Ticket object for each iteration
				
				tic.setTicketNo(viewAll.nextInt());
				tic.setDriverTrn(viewAll.next());
				d=viewAll.nextInt();                          //issueDueDate
				m= viewAll.nextInt();
				y= viewAll.nextInt();
				tic.setIssueDueDate(new Date(d, m, y)); 
				tic.setOffenceCode(viewAll.next());
				tic.setOffenceDescription(viewAll.next());
				tic.setFineAmount(viewAll.nextDouble());
				tic.setLicensePlateNo(viewAll.next());
				fi= viewAll.next();                          //driverFullName
				mi= viewAll.next();
				la= viewAll.next();
				tic.setDriverFullName(new Name(fi, mi, la));
				d=viewAll.nextInt();                           //driverDob
				m= viewAll.nextInt();
				y= viewAll.nextInt();
				tic.setDriverDob(new Date(d, m, y));
				tic.setDriverAddress(viewAll.next());
				tic.setDriverContact(viewAll.next());
				tic.setBadgeNum(viewAll.next());
				fi= viewAll.next();                          //officerFullName
				mi= viewAll.next();
				la= viewAll.next();
				tic.setOfficerFullName(new Name(fi, mi, la));
				tic.setOfficerStation(viewAll.next());
		        
		        ticketList.add(tic);
		        
             }
             
            // Sort the list of tickets by officer station
            Collections.sort(ticketList, (ticket1, ticket2) -> ticket1.getOfficerStation().compareTo(ticket2.getOfficerStation()));     	
			
			// Print sorted tickets
            for (Ticket ticket : ticketList) {
                System.out.println(ticket.toString().replace('_', ' '));
                System.out.println("----------------------------------------");
            }
			
			//Close File
			viewAll.close();
			
			boolean update = true;
			Ticket tic= new Ticket();
			
			//Allow user to select if they want to update ticket
			while(update){
				System.out.println("Would you like to update a ticket?  \n1. No \n2. Yes  \nEnter 1 or 2: ");
				opt = (int) input.nextInt();
				if(opt == 1){
					System.out.println("\nExiting...");
					update = false;
				}else{
					System.out.println("\nEnter the ticket number to update: ");
					searchTic = (int) input.nextInt();
					
					// Search for the ticket by number
		            for (Ticket ticket : ticketList) {
		                if (ticket.getTicketNo() == searchTic) {
		                    found = true;
		
		                    // Select which attributes they want to update
		                    System.out.println("\nSelect the option you would like to change\n1. Offence Code\n2. Offence Description\n3. License Plate Number\nEnter Option:");
		                    
		                    try {
		                    	opt = (int) input.nextInt();
		                    } catch(InputMismatchException e){
                            	System.err.println("Invalid Entry\n Terminating System....");
                                return;               	
                            }
		                    
		                    //Enter the updated values of the attributes
		                    switch(opt){
								case 1:
									try{
										System.out.println("\nOffence Codes- \nO1- Minimal\nO2- Minor\nO3- Major\nO4- Critical");
										System.out.println("\nEnter Offence Code: ");
										ticket.setOffenceCode(input.next());
										
										while(!ticket.getOffenceCode().equals("O1") && !ticket.getOffenceCode().equals("O2") && !ticket.getOffenceCode().equals("O3") && !ticket.getOffenceCode().equals("O4"))
										{
											System.out.println("Invalid offence code. \nPlease reenter the offence code: ");
										    System.out.println("\nEnter Offence Code: ");
											ticket.setOffenceCode(input.next());
										}
										
										System.out.println("Accepted Suitable Offence code");
										
									} catch (Exception e) {
										System.err.println(e.getMessage());
									}
									
									try{
										if (ticket.getOffenceCode().equals("O1")) {
										    ticket.setFineAmount(3000.00); 
										} else if (ticket.getOffenceCode().equals("O2")) {
										    ticket.setFineAmount(10000.00);
										} else if (ticket.getOffenceCode().equals("O3")) {
										    ticket.setFineAmount(30000.00);  
										} else if (ticket.getOffenceCode().equals("O4")) {
										    ticket.setFineAmount(50000.00); 
										}
									} catch (NumberFormatException er) {
										System.err.println(er.getMessage());
									}catch (Exception e) {
										System.err.println(e.getMessage());
									}
									break;
									
								case 2:
									try{
										System.out.println("\nEnter Offence Description: NOTE: Use one word: ");
										ticket.setOffenceDescription(input.next());
									} catch (Exception e) {
										System.err.println(e.getMessage());
									}
									break;
									
								case 3:
									try{
										System.out.println("\nEnter Licence Plate Number: ");
										ticket.setLicensePlateNo(input.next());
										
										while(ticket.getLicensePlateNo().length() != 6){
											System.out.println("Invalid license plate number. It must be exactly 6 characters long.");
										    System.out.println("\nRe-enter Licence Plate Number: ");
										    ticket.setLicensePlateNo(input.next());
										}						
										
									} catch (Exception e) {
										System.err.println(e.getMessage());
									}
									break;
									
								default:
					                System.out.println("Invalid option. Please select a valid option.\n");
					                break;
							}
		
		                    System.out.println("\nTicket updated successfully.");
		                    break;  // Exit the loop after updating
		                }
		            }
					
					if (!found){
						System.out.println("\nTicket not found");
					}else{
						// Sort the list of tickets by officer station
            			Collections.sort(ticketList, (ticket1, ticket2) -> Integer.compare(ticket1.getTicketNo(), ticket2.getTicketNo()));
		                    
		                    try (FileWriter writer = new FileWriter(ticketFile)) {
							    for (Ticket tic1 : ticketList) {
							        // Build the ticket string manually
							        String record = tic1.getTicketNo() + "\t" +
							                        tic1.getDriverTrn() + "\t" +
							                        tic1.getIssueDueDate().getDay() + " " +
							                        tic1.getIssueDueDate().getMonth() + " " +
							                        tic1.getIssueDueDate().getYear() + "\t" +
							                        tic1.getOffenceCode() + "\t" +
							                        tic1.getOffenceDescription() + "\t" +
							                        tic1.getFineAmount() + "\t" +
							                        tic1.getLicensePlateNo() + "\t" +
							                        tic1.getDriverFullName().getFirstName() + " " +
							                        tic1.getDriverFullName().getMiddleName() + " " +
							                        tic1.getDriverFullName().getLastName() + "\t" +
							                        tic1.getDriverDob().getDay() + " " +
							                        tic1.getDriverDob().getMonth() + " " +
							                        tic1.getDriverDob().getYear() + "\t" +
							                        tic1.getDriverAddress() + "\t" +
							                        tic1.getDriverContact() + "\t" +
							                        tic1.getBadgeNum() + "\t" +
							                        tic1.getOfficerFullName().getFirstName() + " " +
							                        tic1.getOfficerFullName().getMiddleName() + " " +
							                        tic1.getOfficerFullName().getLastName() + "\t" +
							                        tic1.getOfficerStation();
							
							        // Write the updated record to the file
							        writer.write(record + "\n"); // Add a newline at the end of each record
							    }
							    System.out.println("\nUpdated tickets successfully written to file.\n");
		                    
		                } catch (IOException e) {
		                    System.err.println("\nError while writing updated tickets to file: " + e.getMessage());
		                }
            
						
					}
					
				}
			}
		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String toString() {//Displays the values held by the attributes when called in the format below
		return "\nOfficer's Information\nFull Name: " + fullName + "\nTRN: " + trn + "\nRole: " + role + "\nBadge Number: " + badgeNum + "\nPolice Station: " + policeStation + "\n";
	}
	
	


	
	
	
}
