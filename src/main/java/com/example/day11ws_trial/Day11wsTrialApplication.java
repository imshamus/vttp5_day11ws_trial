package com.example.day11ws_trial;

import java.util.Collections;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day11wsTrialApplication {

    public static void main(String[] args) {
        // SpringApplication.run(Day11wsTrialApplication.class, args);  // Simple startup approach

        // Create a SpringApplication object, which provides config settings and runs the app.
        SpringApplication app = new SpringApplication(Day11wsTrialApplication.class); 
    
        // Create DefaultApplicationArguments object named cliArgs, initialized with command-line args.
        // This utility class makes it easy to read and work with command-line arguments in Spring Boot.
        DefaultApplicationArguments cliArgs = new DefaultApplicationArguments(args); 


        String port = "3000"; // Set default port to 3000 if no arguments are provided.
                               // Spring Boot handles the conversion to integer when reading the port property internally.
    
        if (cliArgs.containsOption("port")) { // Check if --port was passed as an argument.
            // Returns a List<String> of values for the specified option (e.g., --optionName=value1,value2).
            // Useful when an option has multiple values or you need to retrieve a specific value.

			// getoptionvalues returns a List<String> since args can be multiple values. get(0) to get first value.
            port = cliArgs.getOptionValues("port").get(0);
        } 
        else if (System.getenv("PORT") != null) { // Check environment variable PORT (e.g., set PORT=8081)
            port = System.getenv("PORT");
        }
    
        System.out.printf("Starting application on port: %s%n", port);

		// create small map with a single entry, set "server.port" to the port value that was retrieved
        app.setDefaultProperties(Collections.singletonMap("server.port", port)); 
        app.run(args);
    }
}
