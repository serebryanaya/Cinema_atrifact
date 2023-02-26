package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;

import java.util.List;
import java.util.Optional;

public interface AuthRepository {
    void addSignInfo(User user, String host, String type);
    List<AuthHistory> findAuthInfo(String login);
}
