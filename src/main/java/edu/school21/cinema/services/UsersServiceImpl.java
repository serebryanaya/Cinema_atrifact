package edu.school21.cinema.services;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.AuthRepository;
import edu.school21.cinema.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UsersServiceImpl implements UsersService{

    private UserRepository userRepository;
    private AuthRepository authRepository;
//    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean signIn(String email, String password, String address) {
        if (!email.isEmpty() && !password.toString().isEmpty()) {
            User user = userRepository.findUserByEmail(email);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                authRepository.addSignInInfo(userRepository.findUserByEmail(email), address);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean signUp(String email, String password, String address) {
        if (!email.isEmpty() && !password.isEmpty()) {
            if (userRepository.findUserByEmail(email) == null) {
                User user = new User(email, passwordEncoder.encode(password), "", "", "");
                userRepository.saveUser(user);
                authRepository.addSignUpInfo(userRepository.findUserByEmail(email), address);
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void update(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public List<AuthHistory> getAuthHistoryByEmail(String email) {
        return authRepository.findAuthInfo(email);
    }
}
