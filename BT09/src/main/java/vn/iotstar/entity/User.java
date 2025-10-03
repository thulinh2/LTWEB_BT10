package vn.iotstar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import vn.iotstar.validator.ValidEmail;
import vn.iotstar.validator.ValidPhone;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Họ tên không được để trống")
    private String fullname;

    @Column(nullable = false, unique = true, length = 100)
    @NotBlank(message = "Email không được để trống")
    @ValidEmail
    private String email;

    @Column(name = "user_password", nullable = false, length = 255)
    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, message = "Password phải ít nhất 6 ký tự")
    private String userPassword; // camelCase trong Java


    @Column(length = 15)
    @ValidPhone
    private String phone;

    @ManyToMany
    @JoinTable(
        name = "UserCategory",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Product> products = new HashSet<>();

    @Column(nullable = false, length = 20)
    @NotBlank(message = "Role không được để trống")
    private String role; // Giá trị "ADMIN" hoặc "USER"

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // Getter & Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUserPassword() { return userPassword; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }


    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Set<Category> getCategories() { return categories; }
    public void setCategories(Set<Category> categories) { this.categories = categories; }

    public Set<Product> getProducts() { return products; }
    public void setProducts(Set<Product> products) { this.products = products; }
}