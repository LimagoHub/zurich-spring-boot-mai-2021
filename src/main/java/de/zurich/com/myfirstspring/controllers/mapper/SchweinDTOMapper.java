package de.zurich.com.myfirstspring.controllers.mapper;

import de.zurich.com.myfirstspring.controllers.dtos.SchweinDTO;
import de.zurich.com.myfirstspring.repositories.entities.SchweinEntity;
import de.zurich.com.myfirstspring.services.models.Schwein;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SchweinDTOMapper {
    SchweinDTO convert(Schwein schwein);
    Schwein convert(SchweinDTO entity);
    List<SchweinDTO> convert(List<Schwein> schweine);
}
