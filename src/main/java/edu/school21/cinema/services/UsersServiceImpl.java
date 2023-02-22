package edu.school21.cinema.services;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;

import java.util.List;

public class UsersServiceImpl implements UsersService{

    @Override
    public boolean signIn(String email, char[] password, String address) {
        return false;
    }

    @Override
    public boolean signUp(String email, char[] password, String address) {
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<AuthHistory> getAuthHistoryByEmail(String email) {
        return null;
    }
}
