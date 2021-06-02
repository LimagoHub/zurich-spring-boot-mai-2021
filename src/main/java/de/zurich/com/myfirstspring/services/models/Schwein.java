package de.zurich.com.myfirstspring.services.models;

import lombok.*;

// Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schwein {

    @Setter(AccessLevel.PRIVATE)  private String id;
    private String name;
    @Setter(AccessLevel.PRIVATE)  private int gewicht;

    public void setName(String name) {
        if(name == null || name.equalsIgnoreCase("Elsa"))
            throw new IllegalArgumentException("Name nicht erlaubt");
        this.name = name;
    }

    public void fuettern() {
        setGewicht(getGewicht() + 1);
    }
}