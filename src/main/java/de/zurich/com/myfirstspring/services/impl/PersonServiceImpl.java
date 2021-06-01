package de.zurich.com.myfirstspring.services.impl;

import de.zurich.com.myfirstspring.repositories.PersonRepository;
import de.zurich.com.myfirstspring.repositories.entities.PersonEntity;
import de.zurich.com.myfirstspring.services.PersonService;
import de.zurich.com.myfirstspring.services.PersonenServiceException;
import de.zurich.com.myfirstspring.services.mapper.PersonMapper;
import de.zurich.com.myfirstspring.services.models.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;
    private final List<String> antipathen;

    public PersonServiceImpl(

            final PersonRepository repository, // Unter der Verwaltung von Spring
            final PersonMapper mapper, // Unter der Verwaltung von Spring
            @Qualifier("antipathen") final List<String> antipathen // Nicht unter der Verwaltung
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.antipathen = antipathen;
    }



    @Override
    public boolean speichern(Person person) throws PersonenServiceException {
        try {
            return speichernImpl(person);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Service nicht erreichbar.", e);
        }
    }

    private boolean speichernImpl(Person person) throws PersonenServiceException {
        checkPerson(person);

        boolean exists = repository.existsById(person.getId());
        repository.save(mapper.convert(person));

        return exists;
    }

    private void checkPerson(Person person) throws PersonenServiceException {
        validatePerson(person);

        businessCheck(person);
    }

    private void businessCheck(Person person) throws PersonenServiceException {
        if (antipathen.contains(person.getVorname()))
            throw new PersonenServiceException("Antipath");
    }

    private void validatePerson(Person person) throws PersonenServiceException {
        if(person == null)
            throw new PersonenServiceException("Person darf nicht null sein.");

        if(person.getVorname() == null || person.getVorname().length() < 2)
            throw new PersonenServiceException("Vorname muss min. 2 Zeichen haben.");
    }

    @Override
    public Optional<Person> findePersonMitId(String id) throws PersonenServiceException {
        try {
            return repository.findById(id).map(mapper::convert);
        } catch (RuntimeException e) {
           throw new PersonenServiceException(e);
        }
    }

    @Override
    public List<Person> findeAlle() throws PersonenServiceException {
        try {
            List<PersonEntity> retval = new ArrayList<>();
            repository.findAll().forEach(retval::add);
            return mapper.convert(retval);
        } catch (RuntimeException e) {
            throw new PersonenServiceException(e);
        }
    }

    @Override
    public boolean loesche(Person person) throws PersonenServiceException {
        return loesche(person.getId());
    }

    @Override
    public boolean loesche(String id) throws PersonenServiceException {
        try {
            boolean exist = repository.existsById(id);
            if(exist)
                repository.deleteById(id);
            return exist;
        } catch (RuntimeException e) {
            throw new PersonenServiceException(e);
        }
    }
}
