package de.zurich.com.myfirstspring.demo;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("upper")
public class TranslatorToUpperImpl implements Translator {

    public String translate(String message) {
        return message.toUpperCase();
    }
}
