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

  public Category createCategory(Category category) {
    if (category.getParentCategory() != null) {
      Category parent =
          categoryRepository
              .findById(category.getParentCategory().getId())
              .orElseThrow(() -> new RuntimeException("Parent category not found"));
      category.setParentCategory(parent);
    }
    return categoryRepository.save(category);
  }

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Category getCategory(Long id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found"));
  }

  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }
}
