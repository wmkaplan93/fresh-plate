package com.techelevator.dao;

import com.techelevator.model.RegisterUserDTO;
import com.techelevator.model.SecurityAnswerDTO;
import com.techelevator.model.SecurityQuestion;
import com.techelevator.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role, long securityQuestionID, String answer);
    
    SecurityQuestion getSecurityQuestion(String username);
    
    List<SecurityQuestion> getAllSecurityQuestions();
    
    boolean compareAnswer(SecurityAnswerDTO answer);
    
    void updatePassword(RegisterUserDTO user);
}
