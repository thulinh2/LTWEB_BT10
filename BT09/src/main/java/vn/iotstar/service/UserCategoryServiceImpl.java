package vn.iotstar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.iotstar.entity.UserCategory;
import vn.iotstar.entity.UserCategoryKey;
import vn.iotstar.repository.UserCategoryRepository;

@Service
@RequiredArgsConstructor
public class UserCategoryServiceImpl implements UserCategoryService {

    private final UserCategoryRepository userCategoryRepository;

    @Override
    public List<UserCategory> getAllUserCategories() {
        return userCategoryRepository.findAll();
    }

    @Override
    public List<UserCategory> getCategoriesByUserId(Integer userId) {
        return userCategoryRepository.findByUserId(userId);
    }

    @Override
    public List<UserCategory> getUsersByCategoryId(Integer categoryId) {
        return userCategoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public UserCategory addUserCategory(UserCategory userCategory) {
        return userCategoryRepository.save(userCategory);
    }

    @Override
    public void deleteUserCategory(UserCategoryKey id) {
        userCategoryRepository.deleteById(id);
    }
}