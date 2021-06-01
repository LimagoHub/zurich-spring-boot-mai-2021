package de.zurich.com.myfirstspring.services.models;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

// Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Setter(AccessLevel.NONE)
    private String id;


    private String vorname;


    private String nachname;
}
