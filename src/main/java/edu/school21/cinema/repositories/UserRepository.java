package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;

import java.util.List;

public interface UserRepository {
    User getUserById(Long id);
    User getUserByEmail(String email);
    void saveUser(User user);
    void updateUser(User user);
    void addSignInInfo(User user, String host);
    void addSignUpInfo(User user, String host);
    List<AuthHistory> getAuthInfo(String login);
}
