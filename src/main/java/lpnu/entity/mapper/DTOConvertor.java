package lpnu.entity.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

// Клас конвертор, який перетворює клас сутності в клас DTO
@Component
public class DTOConvertor {
    private final ModelMapper modelMapper;

    @Autowired
    public DTOConvertor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T, D extends Convertable> D convertToEntity(T dto, D entity) {
        return modelMapper.map(dto, (Type) entity.getClass());
    }

    public <T, D extends Convertable> T convertToDto(D entity, Type dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <T, D extends Convertable> D convertFromDtoToDto(T dtoConverted, D dtoConverting) {
        return modelMapper.map(dtoConverted, (Type) dtoConverting.getClass());
    }
}
//************************************************
