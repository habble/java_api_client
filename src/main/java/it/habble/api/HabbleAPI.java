package it.habble.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
@SpringBootApplication()
public class HabbleAPI  {
	
	  public static void main(String[] args) throws Exception {  
		  new SpringApplication(HabbleAPI.class).run(args);	  
	  }
}
