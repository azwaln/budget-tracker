package com.csc3402.project.transaction.service;

import com.csc3402.project.transaction.model.Category;
import com.csc3402.project.transaction.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }
}


