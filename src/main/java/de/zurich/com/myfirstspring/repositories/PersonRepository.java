package de.zurich.com.myfirstspring.repositories;

import de.zurich.com.myfirstspring.repositories.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, String> {

    public List<PersonEntity> findByVorname(String vorname);
    public List<PersonEntity> findByNachname(String nachname);
    public List<PersonEntity> findByVornameAndNachnameOrderByNachnameAsc(String vorname, String nachname);
    public List<PersonEntity> findByVornameOrNachnameOrderByNachnameAsc(String vorname, String nachname);

}
