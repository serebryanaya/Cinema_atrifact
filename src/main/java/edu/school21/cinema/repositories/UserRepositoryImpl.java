package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findUserById(Long id) {
        User user = jdbcTemplate.query(
                "SELECT * FROM users WHERE id=?",
//                        new Object[]{id},
//                new int[]{Types.VARCHAR},
                    new BeanPropertyRowMapper<>(User.class),
                    new Object[]{id})
                .stream()
                .findAny()
                .orElse(null);
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = jdbcTemplate.query(
                        "SELECT * FROM users WHERE email=?",
//                        new Object[]{email},
//                        new int[]{Types.VARCHAR},
                        new BeanPropertyRowMapper<>(User.class),
                        new Object[]{email})
                    .stream()
                    .findAny()
                    .orElse(null);
        return user;
    }

    @Override
    public void saveUser(User user) {
        jdbcTemplate.update(
                "INSERT INTO users (password, firstname, lastname, phone) VALUES (?, ?, ?, ?)",
                user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber());
    }


    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(
                "UPDATE users SET password=?, firstname=?, lastname=?, phone=? WHERE id=?",
                user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getId());
    }

    @Override
    public void deleteUserById(Long id) {
        jdbcTemplate.update(
                "DELETE FROM users WHERE id=?",
                id);
    }
}
