package de.zurich.com.myfirstspring.controllers;


import de.zurich.com.myfirstspring.controllers.dtos.PersonDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@RestController
@RequestMapping("/v1/demo")
@RequestScope
public class DemoController {

    @GetMapping("/gruss")
    public String greeting() {
        return "Hallo Spring";
    }
    @GetMapping(path="/object", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public PersonDTO objectDemo() {
        return PersonDTO.builder().id("1234").vorname("John").nachname("Doe").build();
    }


}
