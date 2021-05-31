package de.zurich.com.myfirstspring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Demo {
    private final Translator translator;


    public Demo(final Translator translator) {
        this.translator = translator;
        System.out.println(translator.translate("Ctor Demo"));
    }

    @PostConstruct
    public void foo() {
        System.out.println(translator.translate("Hier ist foo!"));
    }
    @PreDestroy
    public void bar() {
        System.out.println("Hier ist bar!");
    }

}
