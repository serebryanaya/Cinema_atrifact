package edu.school21.cinema.models;

import lombok.Data;

@Data
public class AuthHistory {
    private Long user_id;
    private String type;
    private String time;
    private String host;

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getHost() {
        return host;
    }
}
