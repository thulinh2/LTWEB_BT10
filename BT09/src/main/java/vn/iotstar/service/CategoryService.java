package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import vn.iotstar.entity.Category;

public interface CategoryService {
	List<Category> getAllCategories();
    Optional<Category> getCategoryById(Integer id);
    Category createCategory(Category category);
    Category updateCategory(Integer id, Category category);
    void deleteCategory(Integer id);
}
