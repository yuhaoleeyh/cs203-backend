package csd.cs203project.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import csd.cs203project.model.User;
import csd.cs203project.repository.user.UserRepository;
import csd.cs203project.service.user.UserServiceImpl;
import csd.cs203project.telegrambot.TelegramBot;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private UserRepository users;

    @Mock
    private TelegramBot telegramBot;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void addUser_NewEmail_ReturnSavedUser() {
        // Arrange
        User user = new User("john.doe@smu.edu.sg", "John Doe", "ROLE_EMPLOYEE", "SMU");
        when(users.findByEmail(any(String.class))).thenReturn(Optional.empty());
        when(users.save(any(User.class))).thenReturn(user);

        // Act
        User savedUser = userService.addUser(user);
        
        // Assert
        assertNotNull(savedUser);
        verify(users).findByEmail(user.getEmail());
        verify(users).save(user);
    }

    @Test
    void addUser_SameEmail_ReturnNull() {
        // Arrange
        User user = new User("john.doe@smu.edu.sg", "John Doe", "ROLE_EMPLOYEE", "SMU");
        when(users.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // Act
        User savedUser = userService.addUser(user);

        // Assert
        assertNull(savedUser);
        verify(users).findByEmail(user.getEmail());
    }

    @Test
    void updateUser_InvalidEmail_ReturnNull() {
        User user = new User("john.doe@smu.edu.sg", "John Updated Name", "ROLE_EMPLOYEE", "SMU");
        when(users.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        User savedUser = userService.updateUser(user.getEmail(), user);

        assertNull(savedUser);
        verify(users).findByEmail(user.getEmail());
    }
}
