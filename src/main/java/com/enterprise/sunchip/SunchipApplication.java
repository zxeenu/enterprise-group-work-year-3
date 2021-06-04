package main.java.com.enterprise.sunchip;

import Backend.BackendContext;
import Common.Shared;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SunchipApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		Shared.BeContext = new BackendContext("jdbc:sqlserver://localhost:1433;databaseName=BLANKDB;user=sa;password=QuidEst");
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
