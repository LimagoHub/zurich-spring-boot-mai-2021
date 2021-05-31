package de.zurich.com.myfirstspring.controllers.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@XmlRootElement
public class PersonDTO {

    @NotNull
    @Size(min = 36, max = 36)
    private String id;

    @Size(min = 2, max = 30)
    private String vorname;

    @NotNull
    @Size(min = 2, max = 30)
    private String nachname;
}
