package main.Spring.Sunchip;

import main.Backend.BackendContext;
import main.Common.Shared;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class SunchipApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

	/** Please enter the following information into your MMSQL DB connection string:
	 *	Location of your MMSQL main.Database goes after sqlserver:// (replace localhost:1433 if required)
	 *	Replace enterDB with your database name
	 *	Replace enterUser with your server admin username
	 *	Replace enterPass with the server admin password
	 **/
		Shared.BeContext = new BackendContext("jdbc:sqlserver://localhost:1433;databaseName=enterDB;user=enterUser;password=enterPass");

	/** If you were unable to create and use your own database please uncomment line 25 and comment out line 22 to use the application
	 Shared.BeContext = new BackendContext("jdbc:sqlserver://27.114.157.158:1433;databaseName=JUNITDB;user=dbacc;password=OraS1m$1");**/

		SpringApplication.run(SunchipApplication.class, args
		);
	}

	/**
	 * Note from Bob
	 *
	 * For some reason we need this below Ovveride to properly package the app into a .war
	 * This applicatoin cannot use the maven embeded server to run from jar file due to limitatations of
	 * jsp not being compatible with it
	 *
	 * .war file works though...
	 *
	 * @param builder
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(SunchipApplication.class);
	}

}
