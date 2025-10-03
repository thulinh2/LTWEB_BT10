package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.UserCategory;
import vn.iotstar.entity.UserCategoryKey;

import java.util.List;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, UserCategoryKey> {

    List<UserCategory> findByUserId(Integer userId);
    List<UserCategory> findByCategoryId(Integer categoryId);
}