package de.zurich.com.myfirstspring.application;

import de.zurich.com.myfirstspring.controllers.PersonDTOMapper;
import de.zurich.com.myfirstspring.controllers.dtos.PersonDTO;
import de.zurich.com.myfirstspring.services.PersonService;
import de.zurich.com.myfirstspring.services.PersonenServiceException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PersonHandlerImpl {

    private final PersonService service;
    private final PersonDTOMapper mapper;


    public PersonHandlerImpl(PersonService service, PersonDTOMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    public void handleSave(PersonDTO personDTO) {
        try {
            service.speichern(mapper.convert(personDTO));
            // Erfolgsevent
        } catch (PersonenServiceException e) {
            // Misserfolg feuern
            e.printStackTrace();
        }
    }
}
