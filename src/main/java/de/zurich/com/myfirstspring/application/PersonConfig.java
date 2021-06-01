package de.zurich.com.myfirstspring.application;


import de.zurich.com.myfirstspring.repositories.PersonRepository;
import de.zurich.com.myfirstspring.services.PersonService;
import de.zurich.com.myfirstspring.services.impl.PersonServiceImpl;
import de.zurich.com.myfirstspring.application.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public PersonService personService(PersonRepository repo, PersonMapper mapper) {
        return new PersonServiceImpl(repo,mapper,antipathen());
    }

}
