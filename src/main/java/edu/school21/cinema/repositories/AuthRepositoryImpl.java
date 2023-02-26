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
    private final String SQL_ADD_TRANSACTION = "INSERT INTO auth_history (user_id, transaction_type, host, transaction_time) values (?, ?, ?, ?)";
    private final String SQL_FIND_USER_BY_ID = "SELECT * FROM auth_history WHERE user_id=?";


    public AuthRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addSignInfo(User user, String host, String type) {
        jdbcTemplate.execute(
                SQL_ADD_TRANSACTION,
                (PreparedStatementCallback<Object>) ps -> {
                    ps.setLong(1, user.getId());
                    ps.setString(2, type);
                    ps.setString(3, host);
                    ps.setString(4, new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));
                    return ps.execute();
                });
    }

    @Override
    public List<AuthHistory> findAuthInfo(String email) {
        List<AuthHistory> history = jdbcTemplate.query(
                SQL_FIND_USER_BY_ID,
//                        new Object[]{email},
//                        new int[]{Types.VARCHAR},
                new AuthRowMapper());
        return history;
    }

}
