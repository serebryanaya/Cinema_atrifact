package edu.school21.cinema.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
        private Long id;
        private String email;
//        private String password;
        private String password;
        private String firstName;
        private String lastName;
        private String phoneNumber;

        public User(String email, String password, String firstName, String lastName, String phoneNumber) {
            this.email = email;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
        }
}
