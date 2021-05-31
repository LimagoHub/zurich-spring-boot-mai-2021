package de.zurich.com.myfirstspring.demo;


import org.springframework.stereotype.Component;

@Component
public class TranslatorToUpperImpl implements Translator {

    public String translate(String message) {
        return message.toUpperCase();
    }
}
