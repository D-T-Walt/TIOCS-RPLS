/*Authors:
 		Olivia McFarlane- 2301555
 		Kemone Laws- 2109446
 		Javone-Anthony Gordon
 		Diwani Walters*/
package rpls;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;

import tiocs.Ticket;

public class ProcessingOfficer {
	private Name fullName;
	private String trn;
	
	
	
	public ProcessingOfficer(Name fullName, String trn) {
		this.fullName = fullName;
		this.trn = trn;
	}
	
	public ProcessingOfficer() {
		this.fullName = new Name();
		this.trn = "000-000-000";
	}
	
	public ProcessingOfficer(ProcessingOfficer obj) {
		this.fullName = obj.fullName;
		this.trn = obj.trn;
	}

	public Name getFullName() {
		return fullName;
	}

	public String getTrn() {
		return trn;
	}

	public void setFullName(Name fullName) {
		this.fullName = new Name(fullName);
	}

	public void setTrn(String trn) {
		this.trn = trn;
	}

	@Override
	public String toString() {
		return "Processing Officer's Name: " + fullName + "\nProcessingOfficer's TRN: " + trn;
	}
	
	public void addPPV(){
		PPV veh= new PPV();
		String search=null;
		Scanner input= new Scanner (System.in);
		boolean validTrn= false;
		boolean foundTrn= false;
		
		Application app= new Application();
				
		while(!validTrn){
			System.out.println("\nEnter the Applicant's TRN of the driver you want to add: ");        //Applicant's/Owner TRN
			search= input.next();

			
			// Check if trn length is 11 characters
			if (search.length()==11)  {
	               validTrn = true;  // Exit loop if email is valid
	           } else {
	               System.out.println("Invalid TRN. Please ensure the length of the trn is exactly 11 characters");
	          }
		}
		
		File origin=new File("Application.txt");
		
		try {
			Scanner reader= new Scanner(origin);
			
			while(reader.hasNext()) {
				app.setTrn(reader.next());
				
				String f= reader.next();
				String mi= reader.next();
				String l= reader.next();
				app.setOwnerName(new Name(f,mi,l));
				
				f= reader.next();
				mi= reader.next();
				l= reader.next();
				app.setDriverName(new Name(f,mi,l));
				
				int day= reader.nextInt();
				int month= reader.nextInt();
				int year= reader.nextInt();
				app.setDob(new Date(day, month, year));
				
				app.setAddress(reader.next());
				app.setAddress(app.getAddress().replace('_', ' '));
				app.setEmail(reader.next());
				app.setContact(reader.next());
				app.setNumOfTicketOutstanding(reader.nextInt());
				app.setTicketOwing(reader.nextDouble());
				
				app.setPoliceRecordReason(reader.next());
				app.setPoliceDeniedReason(reader.next());
				
				if(app.getTrn().equals(search)) {
					foundTrn= true;
					
					System.out.print("\nEnter the License Number of the PPV the driver drives: ");
					veh.setLicenceNo(input.next());
					
					veh.setOwnerName(app.getOwnerName());
					
					veh.setDriverName(app.getDriverName());
					
					Name pastO= new Name("N/A","N/A","N/A" );
					veh.setPastOwner(pastO);
					
					veh.setPastDriver(pastO);
					
					LocalDate issue= LocalDate.now();
					LocalDate expiry= issue.plusYears(5);
					
					veh.setIssueDate(new Date (issue.getDayOfMonth(), issue.getMonthValue(), issue.getYear()));
					veh.setExpDate(new Date (expiry.getDayOfMonth(), expiry.getMonthValue(), expiry.getYear()));	
					
					File writing =new File("ppvDatabase.txt");
					
					try {
						FileWriter rite= new FileWriter(writing, true);
						
						String record= veh.getLicenceNo()+"\t"+ veh.getOwnerName()+"\t"+ veh.getDriverName()+"\t"+ veh.getPastOwner()+"\t"+ veh.getPastDriver()
						+"\t"+ veh.getIssueDate().getDay()+" "+ veh.getIssueDate().getMonth()+" "+ veh.getIssueDate().getYear()+"\t"+ veh.getExpDate().getDay()
						+" "+ veh.getExpDate().getMonth()+" "+ veh.getExpDate().getYear()+ "\n";
						
						rite.write(record);
								
						rite.close();
						
						System.out.println("\nRecord Added!! :)");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
			reader.close();					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(!foundTrn) {
			System.out.println("No Such Application");
		}
	}
	
	public void updatePPV(){
		PPV ppv= new PPV();
		Driver dri = new Driver();
		
		String search=null;
		Scanner input= new Scanner (System.in);
		int opt=0;
		int opt1=0;
		
		String search1= null;
		
		File oldPPVF = new File("ppvDatabase.txt");
		File newPPVF = new File("tempPPV.txt");
		
		File oldDriver = new File("driverDatabase.txt");
		File newDriver = new File("tempDriver.txt");
		
		boolean found = false;
		boolean validTrn= false;
		boolean foundTrn= false;
		
		int day= 0;    //DOB
		int month= 0;
		int year= 0;
		
		while (opt != 1 || opt != 2){
			System.out.println("\nUpdate Menu\n1. Update PPV \n2. Update Driver \n3. Return to Previous Menu \nOption: ");
			
			try {
				opt = (int) input.nextInt();
			} catch(InputMismatchException e){
            	System.err.println("Invalid Entry\n Terminating System....");
                return;               	
            }
			
			switch(opt){
				case 1:
					try (Scanner old = new Scanner(oldPPVF);
						FileWriter newFile = new FileWriter(newPPVF, false)){
							
							System.out.println("\nEnter the License Number of the PPV you want to update: ");
							search1 = input.next();
							
							while(old.hasNext()){
								ppv.setLicenceNo(old.next());
								
								String f = old.next();
								String m = old.next();
								String l = old.next();
								ppv.setOwnerName(new Name(f, m, l));
								
								f = old.next();
								m = old.next();
								l = old.next();
								ppv.setDriverName(new Name(f, m, l));
								
								f = old.next();
								m = old.next();
								l = old.next();
								ppv.setPastOwner(new Name(f, m, l));
								
								f = old.next();
								m = old.next();
								l = old.next();
								ppv.setPastDriver(new Name(f, m, l));
								
								day = old.nextInt();
								month = old.nextInt();
								year = old.nextInt();
								ppv.setIssueDate(new Date(day, month, year));
								
								day = old.nextInt();
								month = old.nextInt();
								year = old.nextInt();
								ppv.setExpDate(new Date(day, month, year));
								
								if(search1.equals(ppv.getLicenceNo()))
								{
									found = true;
										System.out.println("\nSelect the option you would like to update.\n1. Current Owner \n2. Current Driver \n3. Badge Issue Date (For Renewal) \nOption: ");
										opt1 = (int) input.nextInt();
										
										switch(opt1){
											case 1:
												ppv.setPastOwner(ppv.getOwnerName());
												System.out.println("\nEnter updated current owner first name: ");
												f = input.next();
													
												System.out.println("\nEnter updated current owner middle name: ");
												m = input.next(); 
													
												System.out.println("\nEnter updated current owner last name: ");
												l = input.next();
													
												ppv.setOwnerName(new Name(f, m, l));
													
												break;
											
											case 2:
												ppv.setPastDriver(ppv.getDriverName());
												System.out.println("\nEnter updated current driver first name: ");
												f = input.next();
												
												System.out.println("\nEnter updated current driver middle name: ");
												m = input.next(); 
												
												System.out.println("\nEnter updated current driver last name: ");
												l = input.next();
												
												ppv.setDriverName(new Name(f, m, l));
												
												LocalDate issue= LocalDate.now();
												LocalDate expiry= issue.plusYears(5);
												
												ppv.setIssueDate(new Date (issue.getDayOfMonth(), issue.getMonthValue(), issue.getYear()));
												ppv.setExpDate(new Date (expiry.getDayOfMonth(), expiry.getMonthValue(), expiry.getYear()));
												break;
											
											case 3:
												issue= LocalDate.now();
												expiry= issue.plusYears(5);
												
												ppv.setIssueDate(new Date (issue.getDayOfMonth(), issue.getMonthValue(), issue.getYear()));
												ppv.setExpDate(new Date (expiry.getDayOfMonth(), expiry.getMonthValue(), expiry.getYear()));
											
												break;
												
											default:
												System.out.println("Invalid option. Please select a valid option.\n");
												break;
										}
								}
								
								newFile.write(ppv.getLicenceNo() + "\t" + ppv.getOwnerName().getFirstName() + " " + ppv.getOwnerName().getMiddleName() + " " + ppv.getOwnerName().getLastName() 
								+ "\t" + ppv.getDriverName().getFirstName() + " " + ppv.getDriverName().getMiddleName() + " " + ppv.getDriverName().getLastName() 
								+ "\t" + ppv.getPastOwner().getFirstName() + " " + ppv.getPastOwner().getMiddleName() + " " + ppv.getPastOwner().getLastName() 
								+ "\t" + ppv.getPastDriver().getFirstName() + " " + ppv.getPastDriver().getMiddleName() + " " + ppv.getPastDriver().getLastName() 
								+ "\t" + ppv.getIssueDate().getDay() + " " + ppv.getIssueDate().getMonth() + " " + ppv.getIssueDate().getYear()
								+ "\t" + ppv.getExpDate().getDay() + " " + ppv.getExpDate().getMonth() + " " + ppv.getExpDate().getYear() + "\n");
								
							}
							if (!found) {
						            System.out.println("Record doesn't exist");
						    } else {
						    	System.out.println("\nUpdate made to PPV");
						    }
								
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					oldPPVF.delete();
					newPPVF.renameTo(oldPPVF);
					break;
					
				case 2:
					
					try (Scanner old = new Scanner(oldDriver);
				             FileWriter newFile = new FileWriter(newDriver, false)) { // Do not append, overwrite the file

				            // Input loop for TRN
				            while (!validTrn) {
				                System.out.println("\nEnter the TRN of the driver you want to update: ");
				                search1 = input.next();

				                // Validate TRN length
				                if (search1.length() == 11) {
				                    validTrn = true;
				                } else {
				                    System.out.println("Invalid TRN. Please ensure the length of the TRN is exactly 11 characters.");
				                }
				            }

				            // Process each record from the old file
				            while (old.hasNext()) {
				                try {
				                    // Reading and setting data from old file
				                    dri.setTrn(old.next());
				                    String f = old.next();     // Fullname
				                    String m = old.next();
				                    String l = old.next();
				                    dri.setFullName(new Name(f, m, l));

				                    // Reading Date of Birth
				                    day = old.nextInt();
				                    month = old.nextInt();
				                    year = old.nextInt();
				                    dri.setDob(new Date(day, month, year));

				                    // Reading other fields
				                    dri.setAddress(old.next());
				                    dri.setContactNo(old.next());

				                    // Payment due date
				                    day = old.nextInt();
				                    month = old.nextInt();
				                    year = old.nextInt();
				                    dri.setPaymentDueDate(new Date(day, month, year));

				                    // Court date
				                    day = old.nextInt();
				                    month = old.nextInt();
				                    year = old.nextInt();
				                    dri.setCourtDate(new Date(day, month, year));

				                    // Additional fields
				                    dri.setCourtLocation(old.next());
				                    dri.setTotalUnpaidTickets(old.nextInt());
				                    dri.setTotalFineAmount(old.nextDouble());

				                } catch (InputMismatchException e) {
				                    System.err.println("Error reading file: " + e.getMessage());
				                    old.next(); // Skip invalid token
				                }

				                // Check if this is the driver to update
				                if (search1.equals(dri.getTrn())) {
				                    found = true;
				                        System.out.println("\nSelect the option you would like to update.\n1. Driver DOB\n2. Driver Address \n3. Contact \nOption: ");
				                        
				                        try {
				                        	opt1 = input.nextInt();
				                        } catch(InputMismatchException e){
                                        	System.err.println("Invalid Entry\n Terminating System....");
                                            return;               	
                                        }

				                        switch (opt1) {
				                            case 1:
				                                System.out.println("\nEnter updated driver's Date of Birth");
				                                day = -1;
				                                while (day < 1 || day > 31) {
				                                    try {
				                                        System.out.println("Day (1-31): ");
				                                        day = input.nextInt();
				                                    } catch (InputMismatchException e) {
				                                        System.err.println("Invalid! Please enter a valid day (1-31).");
				                                    }
				                                }
				                                month = -1;
				                                while (month < 1 || month > 12) {
				                                    try {
				                                        System.out.println("Month (1-12): ");
				                                        month = input.nextInt();
				                                    } catch (InputMismatchException e) {
				                                        System.err.println("Invalid! Please enter a valid month (1-12).");
				                                    }
				                                }
				                                year = -1;
				                                while (year < 1900 || year > 2006) {
				                                    try {
				                                        System.out.println("Year (e.g. 1990): ");
				                                        year = input.nextInt();
				                                    } catch (InputMismatchException e) {
				                                        System.err.println("Invalid! Please enter a valid year (1900-2006).");
				                                    }
				                                }
				                                dri.setDob(new Date(day, month, year));
				                                break;

				                            case 2:
				                                input.nextLine(); // Consume leftover newline
				                                System.out.println("\nEnter updated driver's address: ");
				                                dri.setAddress(input.nextLine().replace(' ', '_'));
				                                break;

				                            case 3:
				                                System.out.println("\nEnter updated driver's Contact: ");
				                                dri.setContactNo(input.next());
				                                while (dri.getContactNo().length() != 8 && dri.getContactNo().length() != 12) {
				                                    System.out.println("Invalid contact number. Please ensure the length of the number is exactly 8 or 12 characters.");
				                                    dri.setContactNo(input.next());
				                                }
				                                break;
				                            default:
				                                System.out.println("Invalid option. Please select a valid option.");
				                                break;
				                        }
				          
				                }

				                // Write the (updated) or unchanged record to the new file
				                String rec = dri.getTrn() + "\t" + dri.getFullName().getFirstName() + " " + dri.getFullName().getMiddleName() + " " + dri.getFullName().getLastName() + "\t"
				                        + dri.getDob().getDay() + " " + dri.getDob().getMonth() + " " + dri.getDob().getYear() + "\t" + dri.getAddress() + "\t" + dri.getContactNo() + "\t"
				                        + dri.getPaymentDueDate().getDay() + " " + dri.getPaymentDueDate().getMonth() + " " + dri.getPaymentDueDate().getYear() + "\t"
				                        + dri.getCourtDate().getDay() + " " + dri.getCourtDate().getMonth() + " " + dri.getCourtDate().getYear() + "\t"
				                        + dri.getCourtLocation() + "\t" + dri.getTotalUnpaidTickets() + "\t" + dri.getTotalFineAmount() + "\n";
				                newFile.write(rec);
				            }

				            if (!found) {
				                System.out.println("Record doesn't exist.");
				            }

				        } catch (FileNotFoundException e) {
				            e.printStackTrace();
				        } catch (IOException e) {
				            e.printStackTrace();
				        } finally {
				            // Safely delete old file and rename new file
				            oldDriver.delete();
				            if (newDriver.renameTo(oldDriver)) {
				                System.out.println("\nUpdate made to driver :)");
				            } else {
				                System.err.println("Error: Failed to rename the temporary file.");
				            }
				        }
					break;
					
				case 3:
					return;
					
				default:
					System.out.println("Invalid option. Please select a valid option.\n");

					break;
			}
		}	
	}
	
	
	
	public void viewPPV() {
		Scanner input = new Scanner(System.in);
		String searchTRN = null;
		
		boolean found = false;
		boolean validTRN = false;
		boolean hasTickets = false;
		
		
		
		while(!validTRN)
		{
			System.out.println("Enter the TRN for the driver to see their driving record: ");
			searchTRN = input.next();
			
			if(searchTRN.length() == 11)
			{
				validTRN = true;
			}
			else 
			{
				System.out.println("Invalid TRN entered!");
			}
		}		
		
		
		File driverFile = new File ("driverDatabase.txt");
		
		try (Scanner reader = new Scanner(driverFile))
		{
			Driver dri = new Driver();
			
			while(reader.hasNext())
			{				
				dri.setTrn(reader.next());
	            String fi = reader.next(); // Fullname
	            String mi = reader.next();
	            String la = reader.next();
	            dri.setFullName(new Name(fi, mi, la));
	
	           	int d = reader.nextInt(); // DOB day
	           	int m = reader.nextInt(); // DOB month
	            int y = reader.nextInt(); // DOB year
	            dri.setDob(new Date(d, m, y));
	
	            dri.setAddress(reader.next()); // Address
	            dri.setContactNo(reader.next()); // Contact
	
	            d = (int)reader.nextInt(); // PaymentDueDate day
	            m = (int)reader.nextInt();
	            y = (int)reader.nextInt();
	            dri.setPaymentDueDate(new Date(d, m, y));
	
	            d = (int)reader.nextInt(); // CourtDate day
	            m = (int)reader.nextInt();
	            y = (int)reader.nextInt();
	            dri.setCourtDate(new Date(d, m, y));
	
	            dri.setCourtLocation(reader.next());
	            dri.setTotalUnpaidTickets(reader.nextInt());
	            dri.setTotalFineAmount(reader.nextDouble());
	            
	            
	            if (dri.getTrn().equals(searchTRN))
	            {
					found = true;
					System.out.println("Driver's Total Fine Amount:  "+dri.getTotalFineAmount()+"\nDriver's Total Unpaid Ticket: "+dri.getTotalUnpaidTickets());
					File ticketFile = new File ("ticketDatabase.txt");
					
					try(Scanner reader2 = new Scanner(ticketFile))
					{
						Ticket ticket = new Ticket();
						
						while(reader2.hasNext())
						{
							ticket.setTicketNo(reader2.nextInt());
							ticket.setDriverTrn(reader2.next());
				
							// Read Issue Due Date
							int da = (int) reader2.nextInt();
							int mo = (int)reader2.nextInt();
							int ye = (int) reader2.nextInt();
							ticket.setIssueDueDate(new Date(da, mo, ye));
				
							ticket.setOffenceCode(reader2.next());
							ticket.setOffenceDescription(reader2.next());
							ticket.setFineAmount(reader2.nextDouble());
							ticket.setLicensePlateNo(reader2.next());
				
							String f = reader2.next();
							String mn = reader2.next();
							String l = reader2.next();
							ticket.setDriverFullName(new Name(f, mn, l));
				
							da = (int) reader2.nextInt();
							mo = (int) reader2.nextInt();
							ye = (int) reader2.nextInt();
							ticket.setDriverDob(new Date(da, mo, ye));
				
							ticket.setDriverAddress(reader2.next().replace('_', ' '));
							ticket.setDriverContact(reader2.next());
							ticket.setBadgeNum(reader2.next());
				
							f = reader2.next();
							mi = reader2.next();
							l = reader2.next();
							ticket.setOfficerFullName(new Name(f, mi, l));
							ticket.setOfficerStation(reader2.next());
							ticket.setOfficerStation(ticket.getOfficerStation().replace('_', ' '));
							
							if(dri.getTrn().equals(ticket.getDriverTrn()))
							{		
								hasTickets = true;
								System.out.println("Issue Date: "+ ticket.getIssueDueDate().toString()
								+ "\nTicket Number: "+ ticket.getTicketNo()+ "\nOffence Code: "
								+ ticket.getOffenceCode()+ "\nOffence Description: "+ ticket.getOffenceDescription()
								+ "\nFine Amount: $"+ ticket.getFineAmount()+"\n"
								);
								
								System.out.println("----------------------------------------");
							}																		
						}
						
						if (!hasTickets)
						{
							System.out.println("This driver has no tickets.");
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}				
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		if (found) {
	       System.out.println("\nDriver found!");
	    } else {
	        System.out.println("Driver with the provided TRN not found.");
	    }
	}				
		
	
	
	public void viewAllPPV() {
    PPV ppv = new PPV();

    int d = 0;
    int m = 0;
    int y = 0;
    String fi = ""; 
    String mi = ""; 
    String la = "";

    List<PPV> ppvList = new ArrayList<>(); // List to store PPV objects
    List<Driver> driverList = new ArrayList<>(); // List to store all drivers from the database

    System.out.println("\nView All PPV Drivers");

    // Step 1: Load all drivers from driverDatabase.txt into a List<Driver>
    File driverFile = new File("driverDatabase.txt");
    try (Scanner fileScanner = new Scanner(driverFile)) {
        while (fileScanner.hasNext()) {
            Driver driver = new Driver();

            driver.setTrn(fileScanner.next());
            fi = fileScanner.next(); // Fullname
            mi = fileScanner.next();
            la = fileScanner.next();
            driver.setFullName(new Name(fi, mi, la));

            d = fileScanner.nextInt(); // DOB day
            m = fileScanner.nextInt(); // DOB month
            y = fileScanner.nextInt(); // DOB year
            driver.setDob(new Date(d, m, y));

            driver.setAddress(fileScanner.next()); // Address
            driver.setContactNo(fileScanner.next()); // Contact

            d = fileScanner.nextInt(); // PaymentDueDate day
            m = fileScanner.nextInt();
            y = fileScanner.nextInt();
            driver.setPaymentDueDate(new Date(d, m, y));

            d = fileScanner.nextInt(); // CourtDate day
            m = fileScanner.nextInt();
            y = fileScanner.nextInt();
            driver.setCourtDate(new Date(d, m, y));

            driver.setCourtLocation(fileScanner.next());
            driver.setTotalUnpaidTickets(fileScanner.nextInt());
            driver.setTotalFineAmount(fileScanner.nextDouble());

            driverList.add(driver); // Add the driver to the list
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    // Step 2: Read PPV data from the ppvDatabase.txt file
    File ppvFile = new File("ppvDatabase.txt");
    try (Scanner viewAllPPV = new Scanner(ppvFile)) {
        while (viewAllPPV.hasNext()) {
            ppv.setLicenceNo(viewAllPPV.next());

            fi = viewAllPPV.next(); // ownerName
            mi = viewAllPPV.next();
            la = viewAllPPV.next();
            ppv.setOwnerName(new Name(fi, mi, la));

            fi = viewAllPPV.next(); // driverName
            mi = viewAllPPV.next();
            la = viewAllPPV.next();
            ppv.setDriverName(new Name(fi, mi, la));

            fi = viewAllPPV.next(); // pastOwnerName
            mi = viewAllPPV.next();
            la = viewAllPPV.next();
            ppv.setPastOwner(new Name(fi, mi, la));

            fi = viewAllPPV.next(); // pastDriverName
            mi = viewAllPPV.next();
            la = viewAllPPV.next();
            ppv.setPastDriver(new Name(fi, mi, la));

            d = viewAllPPV.nextInt(); // issueDate
            m = viewAllPPV.nextInt();
            y = viewAllPPV.nextInt();
            ppv.setIssueDate(new Date(d, m, y));

            d = viewAllPPV.nextInt(); // issueDueDate
            m = viewAllPPV.nextInt();
            y = viewAllPPV.nextInt();
            ppv.setExpDate(new Date(d, m, y));

            ppvList.add(ppv); // Add the PPV object to the list
                        
            ppv = new PPV(); // Reset the PPV object for the next iteration
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    // Step 3: Sort the PPV list by parish
    ppvList.sort((ppv1, ppv2) -> {
        String parish1 = getParishForDriver(ppv1.getDriverName(), driverList);
        String parish2 = getParishForDriver(ppv2.getDriverName(), driverList);
        return parish1.compareTo(parish2);
    });

    // Step 4: Display Sorted PPV Data
    System.out.println("\nSorted PPV Drivers by Parish:");
    for (PPV sortedPPV : ppvList) {
		
		// Check if the driver's name in the PPV matches a driver in the driverList
	    for (Driver driver : driverList) {
	        // Compare the driver name from PPV with the driver list
	        if (sortedPPV.getDriverName().getFirstName().equals(driver.getFullName().getFirstName()) 
	        && sortedPPV.getDriverName().getMiddleName().equals(driver.getFullName().getMiddleName()) 
	        && sortedPPV.getDriverName().getLastName().equals(driver.getFullName().getLastName())) {
	            String parish = getParishForDriver(sortedPPV.getDriverName(), driverList);
	            
	            // Print PPV details and the matching driver parish
	            System.out.println(sortedPPV.toString());
	            System.out.println("Driver Parish: " + parish);
	            System.out.println("----------------------------------");
	            break; // Once a match is found, break out of the inner loop
	        }
	    }
    }
}

	// Method to Extract Parish for a Given Driver
	private String getParishForDriver(Name driverName, List<Driver> driverList) {
	    for (Driver driver : driverList) {
	        if (driver.getFullName().getFirstName().equals(driverName.getFirstName()) 
	        && driver.getFullName().getMiddleName().equals(driverName.getMiddleName()) 
	        && driver.getFullName().getLastName().equals(driverName.getLastName())) {
	            String address = driver.getAddress();
	            if (address != null && !address.isEmpty()) {
	                String[] parts = address.split("_");
	                if (parts.length >= 2) {
	                    if (parts[parts.length - 2].contains("St.")) {
	                        return parts[parts.length - 2] + " " + parts[parts.length - 1];
	                    } else {
	                        return parts[parts.length - 1];
	                    }
	                }
	            }
	        }
	    }
	    return "Unknown"; // Default if no match found
	}

	
	
	//Kemone
	public void deletePPV() {
		Scanner input = new Scanner(System.in);
		String searchLic= "";
		
		boolean validLic= false;
		boolean found= false;
		
		PPV ppv = new PPV();
		
		//Old file and new file without the deleted record
		File newFi = new File("tempPPV.txt");
		File oldFi = new File("ppvDatabase.txt");
		
		int d= 0;
		int m= 0;
		int y= 0;
		
		String fi= "";
		String mi= "";
		String la= "";
		
		FileWriter newPPVFile = null;
		
		//Validating TRN
		while(!validLic){
			System.out.println("\nEnter the License Number for the PPV you would like to delete : ");        //Applicant's/Owner TRN
			searchLic= input.next();

			// Check if trn length is 6 characters
			if (searchLic.length()==6)  {
	               validLic = true;  // Exit loop if email is valid
	           } else {
	               System.out.println("Invalid Licence Plate Number. Please ensure the length of the licence is exactly 6 characters");
	          }
		}
		
		try{
			//Read record from application file
			Scanner fileScanner1 = new Scanner(oldFi);
			
			while(fileScanner1.hasNext()){
				ppv.setLicenceNo(fileScanner1.next());                //licence number
					fi= fileScanner1.next();                          //ownerName
					mi= fileScanner1.next();
					la= fileScanner1.next();
					ppv.setOwnerName(new Name(fi, mi, la));
					fi= fileScanner1.next();                          //driverName
					mi= fileScanner1.next();
					la= fileScanner1.next();
					ppv.setDriverName(new Name(fi, mi, la));
					fi= fileScanner1.next();                          //pastOwnerName
					mi= fileScanner1.next();
					la= fileScanner1.next();
					ppv.setPastOwner(new Name(fi, mi, la));
					fi= fileScanner1.next();                          //pastDriverName
					mi= fileScanner1.next();
					la= fileScanner1.next();
					ppv.setPastDriver(new Name(fi, mi, la));
					d= fileScanner1.nextInt();                          //issueDate
					m= fileScanner1.nextInt();
					y= fileScanner1.nextInt();
					ppv.setIssueDate(new Date(d, m, y));
					d= fileScanner1.nextInt();                          //Expiry Date
					m= fileScanner1.nextInt();
					y= fileScanner1.nextInt();
					ppv.setExpDate(new Date(d, m, y));
					
					//Check if the current record matches the Licence Number to delete
					if(ppv.getLicenceNo().equals(searchLic)){
						found = true;
						System.out.println("Public Passenger Vehicle with Licence number " + searchLic + " has been deleted successfully!");
					} else {
						try{
							//If Licence does not match(i.e- it's not the record to be deleted - Write the record to the temporary file)
							newPPVFile = new FileWriter(newFi,true);
							
							String record= ppv.getLicenceNo()+"\t"+ ppv.getOwnerName()+"\t"+ ppv.getDriverName()+"\t"+ ppv.getPastOwner()+"\t"+ ppv.getPastDriver()
								+"\t"+ ppv.getIssueDate().getDay()+" "+ ppv.getIssueDate().getMonth()+" "+ ppv.getIssueDate().getYear()+"\t"+ ppv.getExpDate().getDay()
								+" "+ ppv.getExpDate().getMonth()+" "+ ppv.getExpDate().getYear()+ "\n";
								
							newPPVFile.write(record);
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
					}
				}
				
				fileScanner1.close();
				try{
					newPPVFile.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				
				//Delete original file and rename the temporary file that contains every record except the deleted record(record who's licence matched the licence searched for) which was not wriiten - i.e. the updated file
				oldFi.delete();
				newFi.renameTo(oldFi);
				
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		if(!found) {
			System.out.println("This vehicle doesn't exist in the PPV Database\n");
		}
	} 
	
	//Javone
	public void report(){
		
		PPV ppv = new PPV();		
		Scanner input = new Scanner(System.in);		
		File ppvFile = new File ("ppvDatabase.txt");
		String searchLN = null;
		boolean found = false;
		
		
		System.out.print("Enter the licence number for the PPV that you request information about: ");
		searchLN = input.next();
		
		try(Scanner reader = new Scanner(ppvFile)){
			while(reader.hasNext())
			{
				ppv.setLicenceNo(reader.next());
				String f = reader.next();
				String m = reader.next();
				String l = reader.next();
				ppv.setOwnerName(new Name(f,m,l));
				
				 f = reader.next();
				 m = reader.next();
				 l = reader.next();
				 ppv.setDriverName(new Name(f,m,l));
				 
				 f = reader.next();
				 m = reader.next();
				 l = reader.next();
				 ppv.setPastOwner(new Name(f,m,l));
				 
				 f = reader.next();
				 m = reader.next();
				 l = reader.next();
				 ppv.setPastDriver(new Name(f,m,l));
				 
				 int d = (int)reader.nextInt();
				 int mo = (int)reader.nextInt();
				 int y = (int)reader.nextInt();
				 ppv.setIssueDate(new Date(d,mo,y));
				 
				 d = (int)reader.nextInt();
				 mo = (int)reader.nextInt();
				 y = (int)reader.nextInt();
				 ppv.setExpDate(new Date(d,mo,y));
				 
				 if(searchLN.equals(ppv.getLicenceNo()))
				 {
					 found=true;
					 System.out.println("\nCurrent Driver : "+ppv.getDriverName().getFirstName()+" "+ppv.getDriverName().getMiddleName()+" "+ppv.getDriverName().getLastName()
					 +"\nPast Driver: "+ppv.getPastDriver().getFirstName()+" "+ppv.getPastDriver().getMiddleName()+" "+ppv.getPastDriver().getLastName()
					 +"\nCurrent Owner : "+ppv.getOwnerName().getFirstName()+" "+ppv.getOwnerName().getMiddleName()+" "+ppv.getOwnerName().getLastName()
					 +"\nPast Owner : "+ppv.getPastOwner().getFirstName()+" "+ppv.getPastOwner().getMiddleName()+" "+ppv.getPastOwner().getLastName());
					 
					 System.out.println("----------------------------------");
					 
					 File driFile = new File("driverDatabase.txt");
					 
					 try(Scanner reader2 = new Scanner(driFile))
					 {
						while(reader2.hasNext())
						{
							Driver dri = new Driver();			 
			
							
							dri.setTrn(reader2.next());
				            String fi = reader2.next(); // Fullname
				            String mi = reader2.next();
				            String la = reader2.next();
				            dri.setFullName(new Name(fi, mi, la));
				
				           	int da = reader2.nextInt(); // DOB day
				           	int mon = reader2.nextInt(); // DOB month
				            int ye = reader2.nextInt(); // DOB year
				            dri.setDob(new Date(da, mon, ye));
				
				            dri.setAddress(reader2.next()); // Address
				            dri.setContactNo(reader2.next()); // Contact
				
				            da = (int)reader2.nextInt(); // PaymentDueDate day
				            mon = (int)reader2.nextInt();
				            ye = (int)reader2.nextInt();
				            dri.setPaymentDueDate(new Date(da, mon, ye));				
				
				            da = (int)reader2.nextInt(); // CourtDate day
				            mo = (int)reader2.nextInt();
				            ye = (int)reader2.nextInt();
				            dri.setCourtDate(new Date(da, mon, ye));
				
				
				            dri.setCourtLocation(reader2.next());
				            dri.setTotalUnpaidTickets(reader2.nextInt());
				            dri.setTotalFineAmount(reader2.nextDouble());
							
							if (dri.getFullName().getFirstName().equals(ppv.getDriverName().getFirstName()) 
		        			&& dri.getFullName().getMiddleName().equals(ppv.getDriverName().getMiddleName()) 
		        			&& dri.getFullName().getLastName().equals(ppv.getDriverName().getLastName()))
		        			{
								System.out.println("\nCurrent Driver's Total Outstanding Ticket: "+dri.getTotalUnpaidTickets());
							}
						}						
						
					 }catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				 }				 		
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();			
		}
		if (found) {
	       System.out.println("\nLicence Number found!");
	    } else {
	        System.out.println("\nLicence Number not found!");
	    }		
	}
	
	public void deleteDriver() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the TRN of the driver you want to delete: ");
        String trnToDelete = input.next();

        File inputFile = new File("driverDatabase.txt");
        File tempFile = new File("driverDatabase_temp.txt");

        // Check if the input file exists
        if (!inputFile.exists()) {
            System.err.println("Error: The file does not exist.");
            return;
        }

        try (Scanner fileScanner = new Scanner(inputFile);
             FileWriter fileWriter = new FileWriter(tempFile, true)) {

            boolean found = false;

            // Read the original file and write to temp file, skipping the driver with the matching TRN
            while (fileScanner.hasNext()) {
                String currentTrn = fileScanner.next();  // Read TRN
                String firstName = fileScanner.next(); 
                String middleName = fileScanner.next();
                String lastName = fileScanner.next();
                Name currentName = new Name(firstName, middleName, lastName);
                
                int day = fileScanner.nextInt();
                int month = fileScanner.nextInt();
                int year = fileScanner.nextInt();
                Date currentDob = new Date(day, month, year);
                
                String currentAddress = fileScanner.next();
                String currentContactNo = fileScanner.next();
                
                day = fileScanner.nextInt();
                month = fileScanner.nextInt();
                year = fileScanner.nextInt();
                Date currentPaymentDueDate = new Date(day, month, year);
                
                day = fileScanner.nextInt();
                month = fileScanner.nextInt();
                year = fileScanner.nextInt();
                Date currentCourtDate = new Date(day, month, year);
                
                String currentCourtLocation = fileScanner.next();
                int currentTotalUnpaidTickets = fileScanner.nextInt();
                double currentTotalFineAmount = fileScanner.nextDouble();
                
                // Skip the current record if it matches the TRN to delete
                if (currentTrn.equals(trnToDelete)) {
                    found = true;
                    continue;  // Skip writing this line to the temp file
                }

                // If the record is not the one to delete, write it to the temp file
                String record = currentTrn + "\t" + currentName.toString() + "\t" + currentDob.getDay() + " " + currentDob.getMonth() + " " + currentDob.getYear() + "\t" + currentAddress + "\t" + currentContactNo + "\t" + currentPaymentDueDate.getDay() + " " + currentPaymentDueDate.getMonth() + " " + currentPaymentDueDate.getYear() + "\t" + currentCourtDate.getDay() + " " + currentCourtDate.getMonth() + " " + currentCourtDate.getYear() + "\t" + currentCourtLocation + "\t" + currentTotalUnpaidTickets + "\t" + currentTotalFineAmount + "\n";
                
                // Write the record to the temp file
                fileWriter.write(record);
            }

            // Check if the driver with the given TRN was found and deleted
            if (found) {
                System.out.println("Driver with TRN " + trnToDelete + " has been deleted.");
            } else {
                System.out.println("Error: Driver with TRN " + trnToDelete + " not found.");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
        } catch (IOException e) {
            System.err.println("Error: An I/O error occurred.");
        }

        // Replace the original file with the temporary file
        if (inputFile.delete()) {
            if (tempFile.renameTo(inputFile)) {
                System.out.println("Database has been updated.");
            } else {
                System.err.println("Error: Could not rename the temporary file to original.");
            }
        } else {
            System.err.println("Error: Could not delete the original file.");
        }
    }
}
