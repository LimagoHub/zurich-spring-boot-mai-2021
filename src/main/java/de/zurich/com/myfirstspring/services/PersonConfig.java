package de.zurich.com.myfirstspring.services;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.List;

@Configuration
public class PersonConfig {


    @Bean
    //@Primary
    @Qualifier("antipathen")
    public List<String> antipathen() {
        return Arrays.asList("Attila","Peter","Paul","Mary");
    }

    @Bean
    @Qualifier("fruechte")
    public List<String> fruits() {
        return Arrays.asList("Banana","Apple","Cherry","Raspberry");
    }

}
