package com.example.basic_crud_01.service;

import com.example.basic_crud_01.model.User;
import com.example.basic_crud_01.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User createUser(User user) {

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(userDetails.getName());
            existingUser.setEmail(userDetails.getEmail());
            return userRepository.save(existingUser);
        }).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
    }

    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Usuario no encontrado con id" + id);
        }
        userRepository.deleteById(id);
    }


}
