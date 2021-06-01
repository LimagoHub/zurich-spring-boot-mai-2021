package de.zurich.com.myfirstspring.application.mapper;

import de.zurich.com.myfirstspring.repositories.entities.PersonEntity;

import de.zurich.com.myfirstspring.services.models.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonEntity convert(Person person);
    Person convert(PersonEntity personEntity);
    List<Person> convert(List<PersonEntity> entities);
}
