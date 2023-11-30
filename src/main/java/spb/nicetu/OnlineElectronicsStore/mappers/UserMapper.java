package spb.nicetu.OnlineElectronicsStore.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spb.nicetu.OnlineElectronicsStore.dto.UserDTO;
import spb.nicetu.OnlineElectronicsStore.models.User;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
