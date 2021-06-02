package de.zurich.com.myfirstspring.services.mapper;

import de.zurich.com.myfirstspring.repositories.entities.SchweinEntity;
import de.zurich.com.myfirstspring.services.models.Schwein;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SchweinMapper {
    SchweinEntity convert(Schwein schwein);
    Schwein convert(SchweinEntity entity);
    List<Schwein> convert(List<SchweinEntity> entities);
}
