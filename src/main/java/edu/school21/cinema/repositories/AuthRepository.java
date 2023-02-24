package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;

import java.util.List;
import java.util.Optional;

public interface AuthRepository {
    void addSignInInfo(User user, String host);
    void addSignUpInfo(User user, String host);
    List<AuthHistory> findAuthInfo(String login);
}
