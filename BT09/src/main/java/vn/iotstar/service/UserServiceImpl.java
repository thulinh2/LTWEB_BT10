package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User login(String email, String password) {
        return userRepository.findByEmailAndUserPassword(email, password);
    }
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer id, User user) {
        return userRepository.findById(id).map(u -> {
            u.setFullname(user.getFullname());
            u.setEmail(user.getEmail());
            u.setUserPassword(user.getUserPassword());
            u.setPhone(user.getPhone());
            return userRepository.save(u);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}