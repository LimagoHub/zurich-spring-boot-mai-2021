package de.zurich.com.myfirstspring.demo;

import de.zurich.com.myfirstspring.services.PersonService;
import de.zurich.com.myfirstspring.services.PersonenServiceException;
import de.zurich.com.myfirstspring.services.models.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component // @Controller @RestController @Service @Repository
public class Demo {

    private final PersonService personService;



    @Value("${Demo.Message}")
    private String message = "Ich bin eine Meldung";

    public Demo(PersonService personService) {
        this.personService = personService;
    }

    //private final Translator translator;

    /*
    @Autowired
    public Demo(PersonService personService, @Qualifier("upper") final Translator translator) {
        this.personService = personService;
        this.translator = translator;
        System.out.println(translator.translate("Ctor Demo"));
        System.out.println(message);
    }
    */
    @PostConstruct
    public void foo() {

        //System.out.println(translator.translate("Hier ist foo!"));
        //System.out.println(message);

        try {
            Person p = Person.builder().id("123").vorname("Peter").nachname("Mustermann").build();
            personService.speichern(p);
        } catch (PersonenServiceException e) {
            e.printStackTrace();
        }


    }
    @PreDestroy
    public void bar() {
        System.out.println("Hier ist bar!");
    }

}
