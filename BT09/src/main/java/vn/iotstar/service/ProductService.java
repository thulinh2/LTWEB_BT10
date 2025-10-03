package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import vn.iotstar.entity.Product;

public interface ProductService {
	List<Product> getAllProductsSortedByPriceAsc();
    List<Product> getProductsByCategoryId(Integer categoryId);
    Optional<Product> getProductById(Integer id);
    Product createProduct(Product product);
    Product updateProduct(Integer id, Product product);
    void deleteProduct(Integer id);
}
