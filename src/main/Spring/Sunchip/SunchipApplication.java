package main.Spring.Sunchip;

import main.Backend.BackendContext;
import main.Common.Shared;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@SpringBootApplication
public class SunchipApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

	/** Please enter the following information into your MMSQL DB connection string:
	 *	Location of your MMSQL main.Database goes after sqlserver:// (replace localhost:1433 if required)
	 *	Replace enterDB with your database name
	 *	Replace enterUser with your server admin username
	 *	Replace enterPass with the server admin password
	 **/
		String hostname = "";
		try {
			hostname = new BufferedReader(
					new InputStreamReader(Runtime.getRuntime().exec("hostname").getInputStream()))
					.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}



		switch (hostname) {
			case "Ark":
				Shared.BeContext = new BackendContext("jdbc:sqlserver://localhost:1433;databaseName=BLANKDB;user=dbacc;password=OraS1m$1"); // Sams database
			case "silver-note":
				Shared.BeContext = new BackendContext("jdbc:sqlserver://SILVER-NOTE:1433;databaseName=devilsdatabase;user=zeenu;password=2OTF5FkZudUGv"); // Ziaans database
		}

//		Shared.BeContext = new BackendContext("jdbc:sqlserver://SILVER-NOTE:1433;databaseName=enterprisejuly1;user=zeenu;password=2OTF5FkZudUGv"); // Ziaans database

//	 	Shared.BeContext = new BackendContext("jdbc:sqlserver://localhost:1433;databaseName=enterDB;user=enterUser;password=enterPass"); // Template
//
//		Shared.BeContext = new BackendContext("jdbc:sqlserver://SILVER-NOTE:1433;databaseName=enterprisejuly1;user=zeenu;password=2OTF5FkZudUGv"); // Ziaans database

	/** If you were unable to create and use your own database please uncomment line 24 and comment out line 21 to use the application
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
