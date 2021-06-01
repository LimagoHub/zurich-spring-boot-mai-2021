package de.zurich.com.myfirstspring.services;

import de.zurich.com.myfirstspring.repositories.PersonRepository;
import de.zurich.com.myfirstspring.repositories.entities.PersonEntity;
import de.zurich.com.myfirstspring.services.mapper.PersonMapper;
import de.zurich.com.myfirstspring.services.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl objectUnderTest;

    @Mock
    private PersonRepository repoMock;

    @Mock
    private PersonMapper mapperMock;

    @Mock
    private List<String> antipathenMock;

    @Test
    void speichern_parameterIsNull_throwsPersonenerviceException (){
        // Arrange

        // act // Assert
        //
        PersonenServiceException ex = assertThrows(PersonenServiceException.class , ()->objectUnderTest.speichern(null));
        assertEquals("Person darf nicht null sein.", ex.getMessage());

    }
    @Test
    void speichern_VornameIsNull_throwsPersonenerviceException (){
        // Arrange
        Person person = Person.builder().id("1223").vorname(null).nachname("Doe").build();
        // act // Assert
        //


        PersonenServiceException ex = assertThrows(PersonenServiceException.class , ()->objectUnderTest.speichern(person));
        assertEquals("Vorname muss min. 2 Zeichen haben.", ex.getMessage());

    }
    @Test
    void speichern_VornameZuKurz_throwsPersonenerviceException (){
        // Arrange
        Person person = Person.builder().id("1223").vorname("J").nachname("Doe").build();
        // act // Assert
        //


        PersonenServiceException ex = assertThrows(PersonenServiceException.class , ()->objectUnderTest.speichern(person));
        assertEquals("Vorname muss min. 2 Zeichen haben.", ex.getMessage());

    }
    @Test
    void speichern_Antipath_throwsPersonenerviceException (){
        // Arrange
        Person person = Person.builder().id("1223").vorname("Attila").nachname("Doe").build();
        // act // Assert
        //
        Mockito.when(antipathenMock.contains(Mockito.anyString())).thenReturn(true);

        PersonenServiceException ex = assertThrows(PersonenServiceException.class , ()->objectUnderTest.speichern(person));
        assertEquals("Antipath", ex.getMessage());

    }
    @Test
    void speichern_ExceptionInUnderlyingService_throwsPersonenerviceException (){
        // Arrange
        Person person = Person.builder().id("1223").vorname("John").nachname("Doe").build();

        Mockito.when(repoMock.save(Mockito.any(PersonEntity.class))).thenThrow(new ArrayIndexOutOfBoundsException());
        Mockito.when(antipathenMock.contains(Mockito.anyString())).thenReturn(false);
        // act // Assert
        //


        PersonenServiceException ex = assertThrows(PersonenServiceException.class , ()->objectUnderTest.speichern(person));
        assertEquals("Service nicht erreichbar.", ex.getMessage());

    }
    @Test
    void speichern_HappyDay_PersonPassedToRepo () throws Exception{
        // Arrange
        Person person = Person.builder().id("1223").vorname("John").nachname("Doe").build();
        PersonEntity entity = PersonEntity.builder().id("1").vorname("xxx").nachname("yyy").build();
        Mockito.when(repoMock.save(Mockito.any(PersonEntity.class))).thenReturn(entity);
        Mockito.when(mapperMock.convert(Mockito.any(Person.class))).thenReturn(entity);
        Mockito.when(antipathenMock.contains(Mockito.anyString())).thenReturn(false);
        // act
        //


        objectUnderTest.speichern(person);
        // Assert
        Mockito.verify(repoMock, Mockito.times(1)).save(entity);

    }

}