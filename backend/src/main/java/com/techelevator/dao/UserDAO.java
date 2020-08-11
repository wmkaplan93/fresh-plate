package com.techelevator.dao;

import com.techelevator.model.SecurityQuestionDTO;
import com.techelevator.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role, long securityQuestionID);
    
    String getSecurityQuestion(String username);
    
    List<SecurityQuestionDTO> getAllSecurityQuestions();
    
    String getAnswer(String username);
    
    void updatePassword(String username, String newPassword);
}
