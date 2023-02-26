package edu.school21.cinema.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthHistory {
    private Long user_id;
    private String type;
    private String time;
    private String host;
}
