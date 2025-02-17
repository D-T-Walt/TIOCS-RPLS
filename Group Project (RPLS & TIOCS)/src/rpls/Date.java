/*Authors:
 		Olivia McFarlane- 2301555
 		Kemone Laws- 2109446
 		Javone-Anthony Gordon
 		Diwani Walters*/
package rpls;

public class Date {
	private int day;
	private int month;
	private int year;
	
	//default constructor
	public Date() { 
		this.day = 00;
		this.month = 00;
		this.year = 0000;
	}
	
	//primary constructor
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	//copy constructor
	public Date(Date date) {
		this.day = date.day;
		this.month = date.month;
		this.year = date.year;
	}

	//getters
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	//setters
	public void setDay(int day) {
		this.day = day;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public String toString() {// //Displays the values held by the attributes when called in the format below
		return day + "/" + month + "/" + year;
	}
}
