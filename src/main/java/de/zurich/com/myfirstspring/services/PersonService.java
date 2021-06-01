package de.zurich.com.myfirstspring.services;

import de.zurich.com.myfirstspring.services.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    boolean speichern(Person person) throws PersonenServiceException;
    Optional<Person> findePersonMitId(String id) throws PersonenServiceException;
    List<Person> findeAlle() throws PersonenServiceException;
    boolean loesche(Person person) throws PersonenServiceException;
    boolean loesche(String id) throws PersonenServiceException;
}
