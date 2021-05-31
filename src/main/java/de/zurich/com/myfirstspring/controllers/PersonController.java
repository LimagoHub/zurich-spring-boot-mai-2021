package de.zurich.com.myfirstspring.controllers;


import de.zurich.com.myfirstspring.controllers.dtos.PersonDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/personen")
public class PersonController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> findeAllePersonenMitParameter(@RequestParam(required = false) String vorname, @RequestParam(required = false) String nachname) {

        System.out.printf("Vorname = %s, Nachname = %s\n", vorname, nachname);
        return ResponseEntity.ok(Arrays.asList(
                PersonDTO.builder().id("1").vorname("John").nachname("Doe").build(),
                PersonDTO.builder().id("2").vorname("John").nachname("Wick").build(),
                PersonDTO.builder().id("3").vorname("John").nachname("Wayne").build(),
                PersonDTO.builder().id("4").vorname("John").nachname("McClaine").build()
        ));

    }

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    public ResponseEntity<PersonDTO> findePersonMitId(@PathVariable String id) {
        if("100".equals(id))
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(PersonDTO.builder().id(id).vorname("John").nachname("Doe").build());
    }

    @PutMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Person wurde geändert")
    @ApiResponse(responseCode = "201", description = "Person wurde neu angelegt")
    public ResponseEntity<Void> saveOrUpdatePersonIdempotent(@Valid  @RequestBody  PersonDTO personDTO, UriComponentsBuilder uriComponentsBuilder) {

        System.out.println(personDTO + " wurde gespeichert");

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void>  deletePerson(@PathVariable  String id) {
        System.out.println(String.format("Person mit der Id '%s' wurde geloescht", id));
        return ResponseEntity.ok().build();
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
