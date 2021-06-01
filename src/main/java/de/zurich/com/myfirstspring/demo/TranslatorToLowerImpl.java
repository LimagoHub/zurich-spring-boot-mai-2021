package de.zurich.com.myfirstspring.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Qualifier("lower")
public class TranslatorToLowerImpl implements  Translator{
    public String translate(String message) {
        return message.toLowerCase();
    }
}
