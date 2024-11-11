package com.example.day11ws_trial;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day11wsTrialApplication {

	public static void main(String[] args) {
		// SpringApplication.run(Day11wsTrialApplication.class, args);
		SpringApplication app = new SpringApplication(Day11wsTrialApplication.class); // create SpringApp obj, which provides config settings and runs the app. 
	
		DefaultApplicationArguments cliArgs = new DefaultApplicationArguments(args); // create DefaultApplicationArguments object named cliArgs, intiliased with command line args. 
		// utility class that makes it easy to read and work with command-line arguments in Spring Boot.

		String port = "3000"; // set default port as 3000 if no args
		
		if(cliArgs.containsOption("port")) // Check if --port was passed as an argument.
		{
			port = cliArgs.getOptionValues("port").get(0); // Returns a List<String> of values for the specified option (e.g., --optionName=value1,value2).
			// Useful when an option has multiple values or you need to retrieve a specific value
		}
		
	}

}
