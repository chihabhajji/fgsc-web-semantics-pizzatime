package tn.esprit.fgsc.ws_pizza_ontology;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.PostConstruct;

@CrossOrigin("*")
@SpringBootApplication
public class PizzasApplication{
	public static void main(String[] args) {SpringApplication.run(PizzasApplication.class, args);}

	@PostConstruct
	public void setCors(){

	}
}
