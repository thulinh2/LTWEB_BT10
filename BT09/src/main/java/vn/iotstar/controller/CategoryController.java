package vn.iotstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import vn.iotstar.entity.Category;
import vn.iotstar.service.CategoryService;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category"; // category.html
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        return "category_form";
    }

    @PostMapping("/create")
    public String createCategory(@Valid @ModelAttribute("category") Category category,
                                 BindingResult result) {
        if(result.hasErrors()) return "category_form";
        categoryService.createCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category_form";
    }

    @PostMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Integer id,
                               @Valid @ModelAttribute("category") Category category,
                               BindingResult result) {
        if(result.hasErrors()) return "category_form";
        category.setId(id);
        categoryService.createCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/category";
    }
}