package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User findUserById(Long id);
    User findUserByEmail(String email);
    List<User> findAllUsers();
    User saveUser(User user);
    boolean updateUser(User user);
    boolean deleteUserById(Long id);
}
