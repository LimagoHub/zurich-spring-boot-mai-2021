package de.zurich.com.myfirstspring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component // @Controller @RestController @Service @Repository
public class Demo {

    @Value("${Demo.Message}")
    private String message = "Ich bin eine Meldung";

    private final Translator translator;

    @Autowired
    public Demo(@Qualifier("upper") final Translator translator) {
        this.translator = translator;
        System.out.println(translator.translate("Ctor Demo"));
        System.out.println(message);
    }

    @PostConstruct
    public void foo() {

        System.out.println(translator.translate("Hier ist foo!"));
        System.out.println(message);
    }
    @PreDestroy
    public void bar() {
        System.out.println("Hier ist bar!");
    }

}
