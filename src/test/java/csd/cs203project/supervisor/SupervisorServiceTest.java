package csd.cs203project.supervisor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import csd.cs203project.model.User;
import csd.cs203project.repository.user.UserRepository;
import csd.cs203project.service.supervisor.SupervisorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SupervisorServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SupervisorServiceImpl supervisorService;

    @Test
    void addNewEmployee_ReturnSavedEmployee() {
        //arrange
        User user = new User("hi@gmail.com", "Mary", "Admin", "KFC");
        // mock the "findByEmail" operation
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(null));
        // mock the "save" operation 
        when(userRepository.save(any(User.class))).thenReturn(user);

        //act
        User savedUser = supervisorService.addEmployee(user);

        //assert
        assertNotNull(savedUser);

        verify(userRepository).findByEmail(user.getEmail());
        verify(userRepository).save(user);
    }

    @Test 
    void addEmployee_SameEmail_ReturnNull() {
        User user = new User("a@b", "Mary", "Admin", "KFC");
        // mock the "findByEmail" operation
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(user));
        

        //act
        User savedUser = supervisorService.addEmployee(user);

        //assert
        assertNull(savedUser);

        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test 
    void updateEmployee_NotFound_ReturnNull(){
        User user = new User("EFSGFDCDSFDSF", "Test", "Admin", "KFC");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        
        User updatedUser = supervisorService.updateEmployee(user.getEmail(), user);
        
        assertNull(updatedUser);
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test 
    void updateEmployee_Found_ReturnSavedUser(){
        User user = new User("yay@gmail.com", "NewNameForMe", "Admin", "KFC");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(new User("yay@gmail.com", "Marysss", "Admin", "CoffeeBean")));
        // mock the "save" operation 
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        User updatedUser = supervisorService.updateEmployee(user.getEmail(), user);
        
        assertNotNull(updatedUser);
        verify(userRepository).findByEmail(user.getEmail());
        verify(userRepository).save(user);
    }



    
}
