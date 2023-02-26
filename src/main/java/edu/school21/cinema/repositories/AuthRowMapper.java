package edu.school21.cinema.repositories;

import edu.school21.cinema.models.AuthHistory;
import edu.school21.cinema.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthRowMapper implements RowMapper<AuthHistory> {
    @Override
    public AuthHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuthHistory history = new AuthHistory();
        history.setUser_id(rs.getLong("user_id"));
        history.setType(rs.getString("type"));
        history.setTime(rs.getString("time"));
        history.setHost(rs.getString("host"));
        return history;
    }
}
