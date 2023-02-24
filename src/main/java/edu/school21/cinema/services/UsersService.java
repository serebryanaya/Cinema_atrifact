package edu.school21.cinema.services;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;

import java.util.List;

public interface UsersService {
    boolean signIn(String email, String password, String address);
    boolean signUp(String email, String password, String address);
    User getUserByEmail(String email);
    void update(User user);
    List<AuthHistory> getAuthHistoryByEmail(String email);

}
