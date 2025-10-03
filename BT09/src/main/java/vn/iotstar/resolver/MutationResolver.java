package vn.iotstar.resolver;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;
import vn.iotstar.repository.CategoryRepository;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.repository.UserRepository;

@Controller
public class MutationResolver {

    private final ProductRepository productRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;

    public MutationResolver(ProductRepository productRepo, UserRepository userRepo,
                            CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    // User CRUD
    @MutationMapping
    public User createUser(String fullname, String email, String password, String phone) {
        User u = new User();
        u.setFullname(fullname);
        u.setEmail(email);
        u.setUserPassword(password);
        u.setPhone(phone);
        return userRepo.save(u);
    }

    @MutationMapping
    public Boolean deleteUser(Integer id) {
        userRepo.deleteById(id);
        return true;
    }

    // Product CRUD
    @MutationMapping
    public Product createProduct(
            @Argument String title,
            @Argument Integer quantity,
            @Argument String description,
            @Argument Double price,
            @Argument Integer userId) {

        Product p = new Product();
        p.setTitle(title);
        p.setQuantity(quantity);
        p.setDescription(description);
        p.setPrice(price);
        p.setUser(userRepo.findById(userId).orElseThrow());

        return productRepo.save(p);
    }



    @MutationMapping
    public Boolean deleteProduct(Integer id) {
        productRepo.deleteById(id);
        return true;
    }

    // Category CRUD
    @MutationMapping
    public Category createCategory(String name, String images) {
        Category c = new Category();
        c.setName(name);
        c.setImages(images);
        return categoryRepo.save(c);
    }

    @MutationMapping
    public Boolean deleteCategory(Integer id) {
        categoryRepo.deleteById(id);
        return true;
    }
 // User Update
    @MutationMapping
    public User updateUser(@Argument Integer id,
                           @Argument String fullname,
                           @Argument String email,
                           @Argument String password,
                           @Argument String phone) {
        User u = userRepo.findById(id).orElseThrow();
        if (fullname != null) u.setFullname(fullname);
        if (email != null) u.setEmail(email);
        if (password != null) u.setUserPassword(password);
        if (phone != null) u.setPhone(phone);
        return userRepo.save(u);
    }

    // Product Update
    @MutationMapping
    public Product updateProduct(@Argument Integer id,
                                 @Argument String title,
                                 @Argument Integer quantity,
                                 @Argument String description,
                                 @Argument Double price) {
        Product p = productRepo.findById(id).orElseThrow();
        if (title != null) p.setTitle(title);
        if (quantity != null) p.setQuantity(quantity);
        if (description != null) p.setDescription(description);
        if (price != null) p.setPrice(price);
        return productRepo.save(p);
    }

    // Category Update
    @MutationMapping
    public Category updateCategory(@Argument Integer id,
                                   @Argument String name,
                                   @Argument String images) {
        Category c = categoryRepo.findById(id).orElseThrow();
        if (name != null) c.setName(name);
        if (images != null) c.setImages(images);
        return categoryRepo.save(c);
    }

}