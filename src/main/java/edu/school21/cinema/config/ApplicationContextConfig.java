package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.cinema.repositories.AuthRepository;
import edu.school21.cinema.repositories.AuthRepositoryImpl;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.repositories.UserRepositoryImpl;
import edu.school21.cinema.services.UsersService;
import edu.school21.cinema.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@ComponentScan("edu.school21.cinema")
@PropertySource("classpath:../application.properties")

public class ApplicationContextConfig {
    @Value("${db.url}")
    private String DB_URL;
    @Value("${db.username}")
    private String DB_USERNAME;
    @Value("${db.password}")
    private String DB_PASSWORD;
    @Value("${db.driver}")
    private String DB_DRIVER;
    @Value("${upload-path}")
    private String UPLOAD_PATH;

    @Bean
        public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setDriverClassName(DB_DRIVER);
        return dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserRepository userRepository(DataSource dataSource) {
        return new UserRepositoryImpl(dataSource());
    }

    @Bean
    AuthRepository authRepository(DataSource dataSource) {
        return new AuthRepositoryImpl(dataSource());
    }

    @Bean
    UsersService usersService(UserRepository userRepository) {
        return new UsersServiceImpl(userRepository(dataSource()), authRepository(dataSource()));
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
    return jdbcTemplate;
    }

    @Bean
    String uploadPath() {
        return UPLOAD_PATH;
    }

}
