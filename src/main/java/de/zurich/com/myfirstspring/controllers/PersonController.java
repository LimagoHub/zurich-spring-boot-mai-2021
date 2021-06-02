package de.zurich.com.myfirstspring.controllers;


import de.zurich.com.myfirstspring.controllers.dtos.PersonDTO;
import de.zurich.com.myfirstspring.controllers.mapper.PersonDTOMapper;
import de.zurich.com.myfirstspring.services.PersonService;
import de.zurich.com.myfirstspring.services.PersonenServiceException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/personen")
public class PersonController {

    private final PersonService personService;
    private  final PersonDTOMapper mapper;

    public PersonController(final PersonService personService,final PersonDTOMapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> findeAllePersonenMitParameter(@RequestParam(required = false) String vorname, @RequestParam(required = false) String nachname) throws PersonenServiceException {

        return ResponseEntity.ok(mapper.convert(personService.findeAlle()));

    }

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    public ResponseEntity<PersonDTO> findePersonMitId(@PathVariable String id) throws PersonenServiceException{
        return ResponseEntity.of(personService.findePersonMitId(id).map(mapper::convert));
    }

    @PutMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Person wurde geändert")
    @ApiResponse(responseCode = "201", description = "Person wurde neu angelegt")
    public ResponseEntity<Void> saveOrUpdatePersonIdempotent(@Valid  @RequestBody  PersonDTO personDTO, UriComponentsBuilder uriComponentsBuilder) throws PersonenServiceException{
        // Jetzt
        if(personService.speichern(mapper.convert(personDTO)))
            return ResponseEntity.ok().build();

       return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void>  deletePerson(@PathVariable  String id) throws PersonenServiceException{
        if(personService.loesche(id))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/to-upper",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> toUpper(@RequestBody PersonDTO personDTO){
        personDTO.setVorname(personDTO.getVorname().toUpperCase());
        personDTO.setNachname(personDTO.getNachname().toUpperCase());
        return  ResponseEntity.ok(personDTO);
    }


}

/*

VERB	Safe	idempotent

GET	    ja	    ja	    Daten holen
DELETE	nein	ja	    Daten löschen
PUT	    nein	ja	    Daten einfügen oder ändern
POST	nein	nein	Daten einfügen oder ändern

POST	Ja	Ja	Daten holen (Get-Ersatz wenn Parameter keine Strings oder zahlen sondern Objekte sind


 */
