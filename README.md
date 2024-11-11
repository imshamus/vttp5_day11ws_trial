"# vttp5_day11ws_trial" 

mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=3000" 
mvn spring-boot:run -Dserver.port=3000 // will use 3000 if nothing is set in application.properties, if application.properties is set at 5000, it will use 5000.

Precedence of Port Settings
Spring Boot decides which port to use based on the following priority order:

Command-line argument: --server.port=5000       // will override any setting in applications.properties, need  DefaultApplicationArguments cliArgs
Environment variable: PORT=8081                 // to set: set PORT=8081, to unset: set PORT=
Java system property: -Dserver.port=3600
Default property in app.setDefaultProperties: This is what app.setDefaultProperties(Collections.singletonMap("server.port", port)) does.
application.properties: If no other source specifies server.port, Spring Boot uses the value in application.properties.
Spring Boot default: 8080, if nothing else is specified.



http://localhost:8080                   Landing page
http://localhost:8080/nonexistentpage   Error page




SpringApplication app = new SpringApplication(Day11wsTrialApplication.class);
Purpose:

This line creates a new SpringApplication object, app, for your application.
SpringApplication is a Spring Boot class that provides configuration settings and runs the application. It’s responsible for bootstrapping the Spring context, which includes initializing beans, handling configurations, and starting the embedded server (like Tomcat).

Methods:

SpringApplication provides many methods to customize and control the startup behavior of your application. Here are some commonly used ones:

app.run(args):

Starts the application with the provided command-line arguments (args).
Initializes Spring, loads configuration files, and starts the embedded server (e.g., Tomcat).
setDefaultProperties(Map<String, Object> defaultProperties):

Allows you to set default properties for the application.
For example, if you set "server.port" to "8081", the server will start on port 8081 unless overridden by another configuration.
setBannerMode(Banner.Mode mode):

Controls whether to display the Spring Boot banner on startup.
You can set it to OFF, CONSOLE, or LOG.
setAdditionalProfiles(String... profiles):

Specifies active profiles, which control which configurations are loaded (e.g., dev, prod).
Useful for setting environment-specific configurations.
setLogStartupInfo(boolean logStartupInfo):

Enables or disables the logging of startup information (e.g., active profiles, port).
addListeners(ApplicationListener<?> listener):

Registers event listeners that respond to Spring application events (e.g., ApplicationReadyEvent or ContextRefreshedEvent).
addInitializers(ApplicationContextInitializer<?> initializer):

Registers initializers that can customize the Spring application context before beans are loaded.

e.g.
SpringApplication app = new SpringApplication(MyApplication.class);
app.setBannerMode(Banner.Mode.OFF);  // Disable the banner
app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
app.run(args);



DefaultApplicationArguments cliArgs = new DefaultApplicationArguments(args);
Purpose:

This line creates a DefaultApplicationArguments object named cliArgs, initialized with the command-line arguments from args.
DefaultApplicationArguments is a utility class that makes it easy to read and work with command-line arguments in Spring Boot.
It provides methods to check if certain options were passed and retrieve their values without parsing args directly.
Methods:

DefaultApplicationArguments provides several methods to process and retrieve values from command-line arguments:

containsOption(String name):

Checks if a specific option (e.g., --server.port) was passed as an argument.
Returns true if the option is present, false otherwise.
Example: cliArgs.containsOption("server.port")
getOptionValues(String name):

Returns a List<String> of values for the specified option (e.g., --optionName=value1,value2).
Useful when an option has multiple values or you need to retrieve a specific value.
Example: List<String> portValues = cliArgs.getOptionValues("server.port");
getNonOptionArgs():

Returns a list of arguments that do not start with --.
These are often positional arguments rather than options.
Example: List<String> nonOptions = cliArgs.getNonOptionArgs();
getSourceArgs():

Returns the original String[] args array passed to the application.
Example: String[] sourceArgs = cliArgs.getSourceArgs();

e.g.
DefaultApplicationArguments cliArgs = new DefaultApplicationArguments(args);

if (cliArgs.containsOption("server.port")) {
    String port = cliArgs.getOptionValues("server.port").get(0);
    System.out.println("Port specified by CLI: " + port);
}


