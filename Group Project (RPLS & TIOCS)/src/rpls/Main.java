/*Authors:
 		Olivia McFarlane- 2301555
 		Kemone Laws- 2109446
 		Javone-Anthony Gordon
 		Diwani Walters*/
package rpls;

import java.util.InputMismatchException;
import java.util.Scanner;
import tiocs.Officer;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
        int choice1 = 0;
        int choice2 = 0;
        int choice3 = 0;
        
        Name poName= new Name("Stanley", "Paul","Boris");
        ProcessingOfficer poObj= new ProcessingOfficer(poName, "999-999-999");
        String pass= "P@$$word";
        String ent= "";
        
        Application app= new Application();
        User user1;
        Driver driverUser= new Driver();
        Officer objOfficer= new Officer();
        
       // Main menu loop
       while (true) {
            System.out.println("\n=== MAIN MENU ===\nChoose The System You Want To Use");
            System.out.println("1. Red Plate Licensing System");
            System.out.println("2. Ticket Issuing and Offender Checking System");
            System.out.println("3. Processing Officer Maintenance");
            System.out.println("0. Exit");
            System.out.print("Please choose an option: ");
            
            try {
            	choice1 = scanner.nextInt();
            }catch(InputMismatchException e){
            	System.err.println("Invalid Entry\nTerminating System....");
                return;               	
            }
            // Exit condition for Main Menu
            if (choice1 == 0) {
                System.out.println("\nExiting the application.....");
                break;
            }

            switch (choice1) {
                case 1:
                	              	
                    // Sub-menu for Option 1
                    while (true) {
                        System.out.println("\n--- WELCOME TO THE RED PLATE LICENSING SYSTEM ---\nChoose the Type of User You Are");
                        System.out.println("1. Processing Officer");
                        System.out.println("2. Driver");
                        System.out.println("0. Go back to Main Menu");
                        System.out.println("-1. Exit");
                        System.out.print("Please choose an option: ");
                        
                        try {
                        	choice2 = scanner.nextInt();
                        } catch(InputMismatchException e){
                        	System.err.println("Invalid Entry\nTerminating System....");
                            return;               	
                        }
                        // Exit condition from Option 1
                        if (choice2 == -1) {
                            System.out.println("\nExiting the application.....");
                            return; // Immediately exit the program
                        }

                        if (choice2 == 0) {
                            break; // Go back to the main menu
                        }

                        switch (choice2) {
                        	
                            case 1:
                            	System.out.print("\n"+poObj.getFullName().getFirstName()+ " "+ poObj.getFullName().getLastName()+", Enter the Password: ");
                                ent= scanner.next();
                                
                                if(ent.equals(pass)) {
                                	System.out.println("\n"+poObj.getFullName().getFirstName()+ " "+ poObj.getFullName().getLastName()+" Correct Password :)");

                                	// Nested options for Sub-option 1
                                    while (true) {
                                        System.out.println("\n--- PROCESSING OFFICER MENU ---");
                                        System.out.println("1. Create an Application");
                                        System.out.println("2. Update an Application");
                                        System.out.println("3. Delete and Application");
                                        System.out.println("4. Reject an Application");
                                        System.out.println("5. Generate Report for an Application");
                                        System.out.println("0. Go back to previous menu");
                                        System.out.println("-1. Exit");
                                        System.out.print("Please choose an option: ");
                                        
                                        try {
                                        	choice3 = scanner.nextInt();
                                        } catch(InputMismatchException e){
                                        	System.err.println("Invalid Entry\nTerminating System....");
                                            return;               	
                                        }
                                        
                                        // Exit condition for Sub-option 1
                                        if (choice3 == -1) {
                                            System.out.println("\nExiting the application.....");
                                            return; // Immediately exit the program
                                        }

                                        if (choice3 == 0) {
                                            break; // Go back to Sub-option 1 menu
                                        }

                                        // Handle nested choices for Sub-option 1
                                        if (choice3== 1) {
                                        	app.createApp();
                                        } else if(choice3== 2) {
                                        	app.updateApp();
                                        } else if(choice3== 3) {
                                        	app.deleteApp();
                                        } else if(choice3== 4) {
                                        	app.rejectApp();
                                        } else if(choice3== 5) {
                                        	app.searchApp();
                                        } else {
                                            System.err.println("Invalid choice. Please select between 1 and 5.");
                                        }
                                        
                                    }
                                }
                                else {
                                	System.err.print("Wrong Password\nRedirecting to Previous Menu...");
                                	break;
                                }
                                break;
                            case 2:
                            	user1= new Driver();
                            	if(driverUser.log()) {
                            		                             		
                                    // Nested options for Sub-option 2
                                    while (true) {
                                        System.out.println("\n--- DRIVER MENU ---");
                                        System.out.println("1. Check All Past Tickets");
                                        System.out.println("2. Make Online Payments");
                                        System.out.println("3. Check Past-Due Tickets");
                                        System.out.println("4. View Ticket Payments (not passed due)");
                                        System.out.println("5. Check For Warrants");
                                        System.out.println("6. View Paid Tickets");
                                        System.out.println("7. View Outstanding Tickets and Due Date");
                                        System.out.println("0. Go back to previous menu");
                                        System.out.println("-1. Exit");
                                        System.out.print("Please choose an option: ");
                                        
                                        try {
                                        	choice3 = scanner.nextInt();
                                        } catch(InputMismatchException e){
                                        	System.err.println("Invalid Entry\n Terminating System....");
                                            return;               	
                                        }
                                        
                                        // Exit condition for Sub-option 2
                                        if (choice3 == -1) {
                                            System.out.println("\nExiting the application.....");
                                            return; // Immediately exit the program
                                        }

                                        if (choice3 == 0) {
                                            break; // Go back to Sub-option 2 menu
                                        }

                                        // Handle nested choices for Sub-option 1
                                        if (choice3== 1) {
                                        	driverUser.ticketCheck();
                                        } else if(choice3== 2) {
                                        	driverUser.viewPayable();
                                        	driverUser.onlinePayment();
                                        } else if(choice3== 3) {
                                        	driverUser.checkPastDueDate();
                                        } else if(choice3== 4) {
                                        	driverUser.viewTicket();
                                        } else if(choice3== 5) {
                                        	driverUser.checkWarrant();
                                        } else if(choice3== 6) {
                                        	driverUser.viewPaidTicket();
                                        } else if(choice3== 7) {
                                        	driverUser.viewOutstandingTicket();
                                        } else {
                                            System.err.println("Invalid choice. Please select between 1 and 7.");
                                        }
                                    }                            		
                            	} else {
                                	System.out.print("Incorrect TRN\nRedirecting to Previous Menu...");   
                                	break;
                            	}

                                break;
                            default:
                                System.err.println("Invalid choice. Please select between 1 and 2.");
                        }
                    }
                    break;

                case 2:
                	objOfficer= new Officer();
                	
                	if(objOfficer.log()){
                		while (true) {
                            System.out.println("\n--- WELCOME TO THE TICKET ISSUING AND OFFENDER SYSTEM ---");
                            System.out.println("\n--- JCF OFFICER MENU ---");
                            System.out.println("1. Add New Ticket");
                            System.out.println("2. Check Status of a Driver");
                            System.out.println("3. View All");
                            System.out.println("0. Go back to previous menu");
                            System.out.println("-1. Exit");
                            System.out.print("Please choose an option: ");
                            
                            try {
                            	choice3 = scanner.nextInt();
                            } catch(InputMismatchException e){
                            	System.err.println("Invalid Entry\nTerminating System....");
                                return;               	
                            }
                            // Exit condition for Sub-option 2
                            if (choice3 == -1) {
                                System.out.println("\nExiting the application.....");
                                return; // Immediately exit the program
                            }

                            if (choice3 == 0) {
                                break; // Go back to Sub-option 2 menu
                            }

                            // Handle nested choices for Sub-option 2
                            if (choice3== 1) {
                            	objOfficer.add();
                            } else if(choice3== 2) {
                            	objOfficer.check();
                            } else if(choice3== 3) {
                            	objOfficer.viewAll();
                            } else {
                                System.err.println("Invalid choice. Please select between 1 and 3.");
                            }
                        }
                	} else {
                    	System.out.print("Incorrect TRN\nRedirecting to Previous Menu...");   
                    	break;
                	}
                    
                    break;
                case 3:
                	System.out.print("\n"+poObj.getFullName().getFirstName()+ " "+ poObj.getFullName().getLastName()+", Enter the Password: ");
                    ent= scanner.next();
                    
                    if(ent.equals(pass)) {
                    	System.out.print("\n"+poObj.getFullName().getFirstName()+ " "+ poObj.getFullName().getLastName()+" Correct Password :)");
                    	
                    	while (true) {
                            System.out.println("\n--- PROCESSING OFFICER MAINTENANCE MENU ---");
                            System.out.println("1. Add General Driver to System");
                            System.out.println("2. Add PPV to System from Application");
                            System.out.println("3. Update PPV/ Driver File");
                            System.out.println("4. View a Driver's Record");
                            System.out.println("5. View All Drivers with PPV License Badge");
                            System.out.println("6. Delete PPV Record");
                            System.out.println("7. Delete Driver Record");
                            System.out.println("8. Vehicle Current and Past Report");
                            System.out.println("9. Add JCF Officer to the System");
                            System.out.println("0. Go back to previous menu");
                            System.out.println("-1. Exit");
                            System.out.print("Please choose an option: ");
                            
                            try {
                            	choice2 = scanner.nextInt();
                            } catch(InputMismatchException e){
                            	System.err.println("Invalid Entry\nTerminating System....");
                                return;               	
                            }
                            // Exit condition for Sub-option 1
                            if (choice2 == -1) {
                                System.out.println("\nExiting the application.....");
                                return; // Immediately exit the program
                            }

                            if (choice2 == 0) {
                                break; // Go back to Sub-option 1 menu
                            }

                            // Handle nested choices for Sub-option 1
                            if (choice2== 1) {
                            	Driver add= new Driver();
                            	add.addDriver();
                            } else if(choice2== 2) {
                            	poObj.addPPV();
                            } else if(choice2== 3) {
                            	poObj.updatePPV();
                            } else if(choice2== 4) {
                            	poObj.viewPPV();
                            } else if(choice2== 5) {
                            	poObj.viewAllPPV();
                            } else if(choice2== 6) {
                            	poObj.deletePPV();
                            } else if(choice2== 7) {
                            	poObj.deleteDriver();
                            } else if(choice2== 8) {
                            	poObj.report();
                            } else if(choice2== 9) {
                            	objOfficer.addOfficer();
                            } else {
                                System.err.println("Invalid choice. Please select between 1 and 9.");
                            }
                        }
                    }  	
                    else {
                    	System.err.print("Wrong Password\nRedirecting to Previous Menu...");
                    	break;
                    }
                	break;
                default:
                    System.err.println("Invalid choice. Please select between 1 and 2.");
            }
        }

        scanner.close();        
	}
}
