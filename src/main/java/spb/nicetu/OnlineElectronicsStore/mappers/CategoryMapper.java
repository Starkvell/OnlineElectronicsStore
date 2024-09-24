package spb.nicetu.OnlineElectronicsStore.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import spb.nicetu.OnlineElectronicsStore.dto.CategoryDTO;
import spb.nicetu.OnlineElectronicsStore.models.Category;

@Mapper
public interface CategoryMapper {
    CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "products", target = "products"),
            @Mapping(source = "childCategories", target = "childCategories")
    })
    CategoryDTO toCategoryDTO(Category category);

    @InheritInverseConfiguration
    Category toCategory(CategoryDTO categoryDTO);
}
