package spb.nicetu.OnlineElectronicsStore.services;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spb.nicetu.OnlineElectronicsStore.models.Category;
import spb.nicetu.OnlineElectronicsStore.repositories.CategoriesRepository;
import spb.nicetu.OnlineElectronicsStore.util.exceptions.CategoryNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoriesRepository categoriesRepository;

    @InjectMocks
    private CategoryService categoryService;


    @Test
    void testFindAll() {
        // Arrange
        List<Category> categories = Arrays.asList(
                new Category("Category 1"),
                new Category("Category 2")
        );
        when(categoriesRepository.findAll()).thenReturn(categories);

        // Act
        List<Category> result = categoryService.findAll();

        // Assert
        assertThat(result).isEqualTo(categories);
        verify(categoriesRepository, times(1)).findAll();
    }

    @Test
    void testFindOne_ExistingCategory() {
        // Arrange
        Category category = new Category(1,"Category 1", null, null, null);
        when(categoriesRepository.findById(1)).thenReturn(Optional.of(category));

        // Act
        Category result = categoryService.findOne(1);

        // Assert
        assertThat(result).isEqualTo(category);
        verify(categoriesRepository, times(1)).findById(1);
    }

    @Test
    void testFindOne_NonExistingCategory(){
        // Act and Assert
        assertThatThrownBy(() -> categoryService.findOne(1))
                .isInstanceOf(CategoryNotFoundException.class)
                .hasMessage("Could not find object category with ID:1");
    }
}