package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.iotstar.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByOrderByPriceAsc();
    // Lấy product theo category qua bảng UserCategory
    @Query("SELECT p FROM Product p JOIN UserCategory uc ON p.user.id = uc.user.id " +
           "WHERE uc.category.id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Integer categoryId);
}