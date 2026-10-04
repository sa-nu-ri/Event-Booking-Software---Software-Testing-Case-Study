package tests;

import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import au.edu.sccs.csp3105.NBookingPlanner.Planner;

// A small tutorial testing class
public class BVAtests {

	// save out the console output to a stream, rather than printing to the actual console window
	private final ByteArrayOutputStream out = new ByteArrayOutputStream(); // data can be written to this byte array
	private final PrintStream originalOut = System.out; // write output data in text instead of bytes
	
	// lets make the planner class, so we can use it to test
	private Planner planner;

	// helper method, takes in the console stream, cleans up the text and returns the last line
	private String GetLastConsoleOutput(String input) {
		String output = input;
		output = output.strip();
		String[] lines = output.split("\n"); 
		String lastLine = lines[lines.length - 1];
		return lastLine;
	}
	
	// set up our stream to capture the console output
	@BeforeEach
	public void setStreams() {
	    System.setOut(new PrintStream(out));  // reassigns the output stream, we can store in the out variable
	}

	// reset after the test is done
	@AfterEach
	public void restoreInitialStreams() {
	    System.setOut(originalOut); // reset
	}
	
	/*
	 * BVA Tests for scheduleVacation(), checkRoomAvailability(), checkEmployeeAvailability(), 
	 * checkAgendaRoom(), checkAgendaPerson()
	 * */
	
	// Lower bound checking
	
	// Lower bound - Valid and Invalid values
	@Test
	@DisplayName("BVA1 Lower Bound")
	void BVA1LowerBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int sMonth = 1;
		int sDay = 0;
		int eMonth = 1;
		int eDay = 2;
		String absentee = "Jaci Johnston";
				
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(sMonth),
							Integer.toString(sDay),
							Integer.toString(eMonth),
							Integer.toString(eDay),
							absentee, 
							"cancel").execute(() -> {
			//call the schedule vacation method	
			planner.scheduleVacation();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Day does not exist.", GetLastConsoleOutput(out.toString()));		
	}
	
	// Lower bound - Valid and Invalid values
	@Test
	@DisplayName("BVA2 Lower Bound")
	void BVA2LowerBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int sMonth = 0;
		int sDay = 1;
		int eMonth = 0;
		int eDay = 4;
		String absentee = "Jaci Johnston";
				
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(sMonth),
							Integer.toString(sDay),
							Integer.toString(eMonth),
							Integer.toString(eDay),
							absentee, 
							"cancel").execute(() -> {
			//call the schedule vacation method	
			planner.scheduleVacation();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Month does not exist.", GetLastConsoleOutput(out.toString()));		
	}
	
	// Lower bound - Valid values
	@Test
	@DisplayName("BVA3 Lower Bound")
	void BVA3LowerBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int sMonth = 2;
		int sDay = 1;
		int eMonth = 2;
		int eDay = 2;
		String absentee = "Jaci Johnston";
				
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(sMonth),
							Integer.toString(sDay),
							Integer.toString(eMonth),
							Integer.toString(eDay),
							absentee, 
							"cancel").execute(() -> {
			//call the schedule vacation method	
			planner.scheduleVacation();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Enter a person's name, or cancel to cancel the request:", GetLastConsoleOutput(out.toString()));		
	}
	
	
	// Lower and upper bound checking
	
	// Lower and upper bound - Valid and Invalid values
	@Test
	@DisplayName("BVA4 Lower Upper Bound")
	void BVA4LowerUpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 2;
		int day = 28;
		int start = -1;
		int end = 1;
				
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							Integer.toString(start),
							Integer.toString(end),
							"cancel").execute(() -> {
			//call the check Room Availability method	
			planner.checkRoomAvailability();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Illegal hour.", GetLastConsoleOutput(out.toString()));		
	}
	
	
	// Lower and upper bound - Valid values
	@Test
	@DisplayName("BVA5 Lower Upper Bound")
	void BVA5LowerUpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 2;
		int day = 29;
		int start = 0;
		int end = 2;
				
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							Integer.toString(start),
							Integer.toString(end),
							"cancel").execute(() -> {
			//call the check Room Availability method	
			planner.checkRoomAvailability();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("The rooms available at the specified time are:", GetLastConsoleOutput(out.toString()));		
	}
	
	
	// Lower and upper bound - Valid and Invalid values
	@Test
	@DisplayName("BVA6 Lower Upper Bound")
	void BVA6LowerUpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 2;
		int day = 28;
		int start = 0;
		int end = -1;
				
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							Integer.toString(start),
							Integer.toString(end),
							"cancel").execute(() -> {
			//call the check Room Availability method	
			planner.checkRoomAvailability();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Illegal hour.", GetLastConsoleOutput(out.toString()));		
	}
	
	
	// Lower and upper bound - Valid and Invalid values
	@Test
	@DisplayName("BVA7 Lower Upper Bound")
	void BVA7LowerUpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 2;
		int day = 30;
		int start = 1;
		int end = 2;
				
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							Integer.toString(start),
							Integer.toString(end),
							"cancel").execute(() -> {
			//call the check employee Availability method	
			planner.checkEmployeeAvailability();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Day does not exist.", GetLastConsoleOutput(out.toString()));		
	}
	
	// Lower and upper bound - Valid values
	@Test
	@DisplayName("BVA8 Lower Upper Bound")
	void BVA8LowerUpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 2;
		int day = 1;
		int start = 22;
		int end = 23;
				
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							Integer.toString(start),
							Integer.toString(end),
							"cancel").execute(() -> {
			//call the check employee Availability method	
			planner.checkEmployeeAvailability();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Ashley Martin", GetLastConsoleOutput(out.toString()));
	}
	
	
	// Lower and upper bound - Valid and Invalid values
	@Test
	@DisplayName("BVA9 Lower Upper Bound")
	void BVA9LowerUpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 2;
		int day = 2;
		int start = 22;
		int end = 24;
				
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							Integer.toString(start),
							Integer.toString(end),
							"cancel").execute(() -> {
			//call the check employee Availability method	
			planner.checkEmployeeAvailability();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Illegal hour.", GetLastConsoleOutput(out.toString()));
	}
	
	
	// Lower and upper bound - Valid values
	@Test
	@DisplayName("BVA10 Lower Upper Bound")
	void BVA10LowerUpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 2;
		int day = 2;
		int start = 23;
		int end = 0;
				
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							Integer.toString(start),
							Integer.toString(end),
							"cancel").execute(() -> {
			//call the check employee Availability method	
			planner.checkEmployeeAvailability();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Ashley Martin", GetLastConsoleOutput(out.toString()));
	}
	
	
	// Upper bound - Valid values
	@Test
	@DisplayName("BVA11 Upper Bound")
	void BVA11UpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 11;
		int day = 29;
		String room = "ML5.123";

		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							room,
							"cancel").execute(() -> {
			//call the check agenda room method	
			planner.checkAgendaRoom();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("No Meetings booked on this date.", GetLastConsoleOutput(out.toString()));
	}
	
	
	// Lower upper bound - Valid values
	@Test
	@DisplayName("BVA12 Upper Bound")
	void BVA12UpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 11;
		int day = 30;
		String room = "ML5.123";

		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							room,
							"cancel").execute(() -> {
			//call the check agenda room method	
			planner.checkAgendaRoom();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("No Meetings booked on this date.", GetLastConsoleOutput(out.toString()));
	}
	
	
	// Lower upper bound - Valid and Invalid values
	@Test
	@DisplayName("BVA13 Upper Bound")
	void BVA13UpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 13;
		String allDays = "all";
		String room = "ML5.123";

		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							allDays,
							room,
							"cancel").execute(() -> {
			//call the check agenda room method	
			planner.checkAgendaRoom();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Month does not exist.", GetLastConsoleOutput(out.toString()));
	}
	
	
	// Lower upper bound - Valid values
	@Test
	@DisplayName("BVA14 Upper Bound")
	void BVA14UpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 11;
		int day = 31;
		String person = "Mark Colin";

		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							person,
							"cancel").execute(() -> {
			//call the check agenda person method	
			planner.checkAgendaPerson();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Day does not exist.", GetLastConsoleOutput(out.toString()));
	}
	
	
	// Lower upper bound - Valid values
	@Test
	@DisplayName("BVA15 Upper Bound")
	void BVA15UpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 12;
		int day = 30;
		String person = "Mark Colin";

		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							person,
							"cancel").execute(() -> {
			//call the check agenda person method	
			planner.checkAgendaPerson();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("No Meetings booked on this date.", GetLastConsoleOutput(out.toString()));
	}
	
	
	// Lower upper bound - Valid values
	@Test
	@DisplayName("BVA16 Upper Bound")
	void BVA16UpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 12;
		int day = 31;
		String person = "Mark Colin";

		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							person,
							"cancel").execute(() -> {
			//call the check agenda person method	
			planner.checkAgendaPerson();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("No Meetings booked on this date.", GetLastConsoleOutput(out.toString()));
	}
	
	
	// Lower upper bound - Valid values
	@Test
	@DisplayName("BVA17 Upper Bound")
	void BVA17UpperBound() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 12;
		int day = 32;
		String person = "Mark Colin";

		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							person,
							"cancel").execute(() -> {
			//call the check agenda person method	
			planner.checkAgendaPerson();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Day does not exist.", GetLastConsoleOutput(out.toString()));
	}
}
