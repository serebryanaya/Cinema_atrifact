package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User findUserById(Long id);
    User findUserByEmail(String email);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
}
