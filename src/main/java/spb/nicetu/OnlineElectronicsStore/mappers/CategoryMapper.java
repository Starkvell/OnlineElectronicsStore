package spb.nicetu.OnlineElectronicsStore.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import spb.nicetu.OnlineElectronicsStore.dto.CategoryDTO;
import spb.nicetu.OnlineElectronicsStore.models.Category;

@Component
public class CategoryMapper {
    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryDTO convertToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }
}
