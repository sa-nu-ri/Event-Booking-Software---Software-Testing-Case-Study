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
public class EQtests {

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
	 * EQ Tests for scheduleMeeting()
	 * */
	
	// Valid month, day, room, description, start time, end time, attendee
	@Test
	@DisplayName("EQ1 Valid Values")
	void EQ1ValidValues() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = 5;
		int day = 10;
		int start = 13;
		int end = 14;
		String roomIn = "ML5.123";
		String personIn1 = "Edith Cowan";
		String personIn2 = "Mark Colin";
		String complete = "done";
		String description = "PhD";
				
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							Integer.toString(start),
							Integer.toString(end),
							roomIn, 
							personIn1, 
							personIn2, 
							complete, 
							description,
							"cancel").execute(() -> {
			//call the schedule meeting method	
			planner.scheduleMeeting();
        });
		
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Enter a description for the meeting:", GetLastConsoleOutput(out.toString()));		
	}
	
	// Invalid month, day, end time
	@Test
	@DisplayName("EQ2 Invalid Values")
	void EQ2InvalidValues() throws Exception {   
		// make spy, we can use some of the real methods and mock some of the other methods
		planner = Mockito.spy(Planner.class);
		
		//override main menu with do nothing..we are mocking this ha-ah!
	    Mockito.doNothing().when(planner).mainMenu();
	    	    
		//set inputs as per table
		int month = -2;
		int day = -3;
		int start = 22;
		int end = 27;
		String roomIn = "ML5.123";
		String personIn = "Mark Colin";
		String complete = "done";
		String description = "PhD";
				
		
		//buffer input values, console will call these one by one! Thanks system lambda library
		withTextFromSystemIn(Integer.toString(month),
							Integer.toString(day),
							Integer.toString(start),
							Integer.toString(end),
							roomIn, 
							personIn, 
							complete, 
							description,
							"cancel").execute(() -> {
			//call the schedule meeting method
			planner.scheduleMeeting();
        });
		System.out.println(GetLastConsoleOutput(out.toString()));
		//assert what we expect to be printed to console, is what is actually observed
		Assertions.assertEquals("Day does not exist.", GetLastConsoleOutput(out.toString()));	
	}
	
	
	// Invalid month, day, start time, end time
		@Test
		@DisplayName("EQ3 Invalid Values")
		void EQ3InvalidValues() throws Exception {   
			// make spy, we can use some of the real methods and mock some of the other methods
			planner = Mockito.spy(Planner.class);
			
			//override main menu with do nothing..we are mocking this ha-ah!
		    Mockito.doNothing().when(planner).mainMenu();
		    	    
			//set inputs as per table
			int month = 15;
			int day = 33;
			int start = -5;
			int end = -6;
			String roomIn = "ML5.123";
			String personIn = "Mark Colin";
			String complete = "done";
			String description = "PhD";
					
			
			//buffer input values, console will call these one by one! Thanks system lambda library
			withTextFromSystemIn(Integer.toString(month),
								Integer.toString(day),
								Integer.toString(start),
								Integer.toString(end),
								roomIn, 
								personIn, 
								complete, 
								description,
								"cancel").execute(() -> {
				//call the schedule meeting method
				planner.scheduleMeeting();
	        });
			System.out.println(GetLastConsoleOutput(out.toString()));
			//assert what we expect to be printed to console, is what is actually observed
			Assertions.assertEquals("Day does not exist.", GetLastConsoleOutput(out.toString()));	
		}
	
}
