package vn.iotstar.resolver;

import java.util.List;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;
import vn.iotstar.repository.CategoryRepository;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.repository.UserRepository;

@Controller
public class QueryResolver {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public QueryResolver(ProductRepository productRepository, UserRepository userRepository,
                         CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @QueryMapping
    public List<Product> getAllProductsSorted() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @QueryMapping
    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @QueryMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @QueryMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @QueryMapping
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

}