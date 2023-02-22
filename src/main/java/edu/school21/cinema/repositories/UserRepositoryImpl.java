package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    final private JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUserById(Long id) {
//        String SQL = "select * from users where id=?";
//        try {
//            return jdbcTemplate.queryForObject(SQL, new Object[]{id}, new UserMapper());
//        } catch (EmptyResultDataAccessException e) {
            return null;
//        }
    }

    @Override
    public User getUserByEmail(String email) {
//        String SQL = "select * from users where email=?";
//        try {
//            return jdbcTemplate.queryForObject(SQL, new Object[]{login}, new UserMapper());
//        } catch (EmptyResultDataAccessException e) {
            return null;
//        }
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void addSignInInfo(User user, String host) {

    }

    @Override
    public void addSignUpInfo(User user, String host) {

    }

    @Override
    public List<AuthHistory> getAuthInfo(String login) {
        return null;
    }
}
