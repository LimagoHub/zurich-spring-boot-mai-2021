package de.zurich.com.myfirstspring.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchweinEntity {
    private String id;
    private String name;
    private int gewicht;
}