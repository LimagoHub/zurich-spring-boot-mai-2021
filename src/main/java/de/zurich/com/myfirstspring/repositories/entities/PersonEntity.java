package de.zurich.com.myfirstspring.repositories.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// JPA
@Entity
@Table(name="tblpersonen")
public class PersonEntity {

    @Id
    @Column(nullable = false, length = 36)
    private String id;

    @Column(nullable = true, length = 30)
    private String vorname;

    @Column(nullable = false, length = 30)
    private String nachname;


}
