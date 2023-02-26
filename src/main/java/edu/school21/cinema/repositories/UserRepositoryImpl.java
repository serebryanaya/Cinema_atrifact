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
    private final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private final String SQL_DELETE_USER = "DELETE FROM users WHERE id = ?";
    private final String SQL_UPDATE_USER = "UPDATE users SET password=?, firstname=?, lastname=?, phone=? WHERE id=?";
    private final String SQL_FIND_ALL_USERS = "SELECT * FROM users";

    private final String SQL_INSERT_USER = "INSERT INTO users (password, firstname, lastname, phone) VALUES (?, ?, ?, ?) returning id";

    private RowMapper<User> mapper = new UserRowMapper();

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findUserById(Long id) {
//        User user = jdbcTemplate.query(
//                "SELECT * FROM users WHERE id=?",
////                        new Object[]{id},
////                new int[]{Types.VARCHAR},
//                    new BeanPropertyRowMapper<>(User.class),
//                    new Object[]{id})
//                .stream()
//                .findAny()
//                .orElse(null);
//        return user;
        return jdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID, new UserRowMapper(), id);

    }

    @Override
    public User findUserByEmail(String email) {
//        User user = jdbcTemplate.query(
//                        "SELECT * FROM users WHERE email=?",
////                        new Object[]{email},
////                        new int[]{Types.VARCHAR},
//                        new BeanPropertyRowMapper<>(User.class),
//                        new Object[]{email})
//                    .stream()
//                    .findAny()
//                    .orElse(null);
//        return user;
        return jdbcTemplate.queryForObject(SQL_FIND_USER_BY_EMAIL, new UserRowMapper(), new Object[] { email });
    }

    @Override
    public User saveUser(User user) {
//        jdbcTemplate.update(
//                "INSERT INTO users (password, firstname, lastname, phone) VALUES (?, ?, ?, ?)",
//                user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber());
        Long id = jdbcTemplate.queryForObject(SQL_INSERT_USER, Long.class, user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getPassword());
        user.setId(id);
        return user;
    }


    @Override
    public boolean updateUser(User user) {
        return (jdbcTemplate.update(SQL_UPDATE_USER, user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getId()) > 0);
    }

    @Override
    public boolean deleteUserById(Long id) {
       return (jdbcTemplate.update(SQL_DELETE_USER, id) > 0);
    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query(SQL_FIND_ALL_USERS, new UserRowMapper());
    }
}
