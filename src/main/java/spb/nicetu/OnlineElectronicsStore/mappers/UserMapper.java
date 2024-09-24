package spb.nicetu.OnlineElectronicsStore.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import spb.nicetu.OnlineElectronicsStore.dto.UserDTO;
import spb.nicetu.OnlineElectronicsStore.models.User;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")
    })
    UserDTO toUserDTO(User user);

    @InheritInverseConfiguration
    User toUser(UserDTO userDTO);
}
