package com.taxonomy.service;

import com.taxonomy.model.Category;
import com.taxonomy.repository.CategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category createCategory(String name, Long parentId) {
    Category category = new Category();
    category.setName(name);
    if (parentId != null) {
      Category parent =
          categoryRepository
              .findById(parentId)
              .orElseThrow(() -> new RuntimeException("Parent category not found"));
      category.setParentCategory(parent);
    }
    return categoryRepository.save(category);
  }

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }
}
