package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AuthRepositoryImpl implements AuthRepository {
    private final JdbcTemplate jdbcTemplate;

    public AuthRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addSignInInfo(User user, String host) {
        jdbcTemplate.execute(
                "INSERT INTO auth_history (user_id, transaction_type, host, transaction_time) values (?, ?, ?, ?)",
                (PreparedStatementCallback<Object>) ps -> {
                    ps.setLong(1, user.getId());
                    ps.setString(2, "sign_in");
                    ps.setString(3, host);
                    ps.setString(4, new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));
                    return ps.execute();
                });
    }

    @Override
    public void addSignUpInfo(User user, String host) {
        jdbcTemplate.execute(
                "INSERT INTO auth_history (user_id, transaction_type, host, transaction_time) values (?, ?, ?, ?)",
                (PreparedStatementCallback<Object>) ps -> {
                    ps.setLong(1, user.getId());
                    ps.setString(2, "sign_up");
                    ps.setString(3, host);
                    ps.setString(4, new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));
                    return ps.execute();
                });
    }

    @Override
    public List<AuthHistory> findAuthInfo(String email) {
        List<AuthHistory> history = jdbcTemplate.query(
                        "SELECT * FROM auth_history WHERE user_id=?",
//                        new Object[]{email},
//                        new int[]{Types.VARCHAR},
                        new BeanPropertyRowMapper<>(AuthHistory.class));
        return history;
    }

}
