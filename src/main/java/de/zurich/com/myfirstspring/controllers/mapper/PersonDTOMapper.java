package de.zurich.com.myfirstspring.controllers.mapper;


import de.zurich.com.myfirstspring.controllers.dtos.PersonDTO;
import de.zurich.com.myfirstspring.services.models.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonDTOMapper {

    PersonDTO convert(Person person);
    Person convert(PersonDTO personDTO);
    List<PersonDTO> convert(List<Person> personen);
}
