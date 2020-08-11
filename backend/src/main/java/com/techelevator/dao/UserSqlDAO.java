package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.SecurityQuestion;
import com.techelevator.model.User;

@Service
public class UserSqlDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    public UserSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
    }

	@Override
	public User getUserById(Long userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if(results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("userId "+userId+" was not found.");
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if( user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password, String role, long securityQuestionID, String answer) {
        boolean userCreated = false;

        // create user
        String insertUser = "insert into users (username,password_hash,role,security_question_id, answer) values(?,?,?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
                    ps.setString(1, username);
                    ps.setString(2, password_hash);
                    ps.setString(3, ssRole);
                    ps.setLong(4, securityQuestionID);
                    ps.setString(5, answer);
                    return ps;
                }
                , keyHolder) == 1;
        int newUserId = (int) keyHolder.getKeys().get(id_column);

        return userCreated;
    }
    
    @Override
	public SecurityQuestion getSecurityQuestion(String username) {
    	SecurityQuestion securityQuestion = null;
		String sql = "SELECT security_questions.security_questions FROM security_questions " + 
						"JOIN users ON security_questions.security_question_id = users.security_question_id " + 
						"WHERE users.username = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
		while(results.next()) {
			securityQuestion = mapRowToSecurityQuestion(results);
		}
		return securityQuestion;
	}
    
    @Override
	public List<SecurityQuestion> getAllSecurityQuestions() {
		List<SecurityQuestion> securityQuestions = new ArrayList<>();
		String sql = "SELECT security_question_id, security_questions FROM security_questions";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			SecurityQuestion securityQuestion = mapRowToSecurityQuestion(results);
			securityQuestions.add(securityQuestion);
		}
		return securityQuestions;
	}

	@Override
	public boolean compareAnswer(String username, String answer) {
		boolean response = false;
		
		String sql = "SELECT (answer = ?) FROM users WHERE username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql,answer, username);
		while(results.next()) {
			response = results.getBoolean(1);
		}
		return response;
	}

	@Override
	public void updatePassword(String username, String newPassword) {
		String password_hash = new BCryptPasswordEncoder().encode(newPassword);
		String sql = "UPDATE users SET password_hash = ? WHERE username = ?";
		jdbcTemplate.update(sql, password_hash, username);
		
	}

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setActivated(true);
        return user;
    }
    
    private SecurityQuestion mapRowToSecurityQuestion(SqlRowSet rs) {
    	SecurityQuestion securityQuestion = new SecurityQuestion();
    	securityQuestion.setSecurityQuestionID(rs.getLong("security_question_id"));
    	securityQuestion.setSecurityQuestion(rs.getString("security_questions"));
    	return securityQuestion;
    }
	
}
