package vn.iotstar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import vn.iotstar.entity.Product;
import vn.iotstar.service.ProductService;

import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProductsSortedByPriceAsc();
        model.addAttribute("products", products);
        return "product"; // product.html
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form"; // form tạo product
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult result) {
        if(result.hasErrors()) return "product_form";
        productService.createProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product_form";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Integer id,
                              @Valid @ModelAttribute("product") Product product,
                              BindingResult result) {
        if(result.hasErrors()) return "product_form";
        product.setId(id);
        productService.createProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product";
    }
}