package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import vn.iotstar.entity.User;

public interface UserService {
	List<User> getAllUsers();
    Optional<User> getUserById(Integer id);
    User login(String email, String password);
    User createUser(User user);
    User updateUser(Integer id, User user);
    void deleteUser(Integer id);
}
