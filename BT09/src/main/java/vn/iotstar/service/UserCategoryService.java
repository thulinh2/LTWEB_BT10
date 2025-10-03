package vn.iotstar.service;

import java.util.List;

import vn.iotstar.entity.UserCategory;
import vn.iotstar.entity.UserCategoryKey;

public interface UserCategoryService {
	List<UserCategory> getAllUserCategories();
    List<UserCategory> getCategoriesByUserId(Integer userId);
    List<UserCategory> getUsersByCategoryId(Integer categoryId);
    UserCategory addUserCategory(UserCategory userCategory);
    void deleteUserCategory(UserCategoryKey id);
}
