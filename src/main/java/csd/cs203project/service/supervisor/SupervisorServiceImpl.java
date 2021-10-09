package csd.cs203project.service.supervisor;

import csd.cs203project.repository.user.UserRepository;
import java.util.List;
import csd.cs203project.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
@Service
public class SupervisorServiceImpl implements SupervisorService {
    private UserRepository userRepository;
    
    @Autowired
    public SupervisorServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findByCompany(String company) {
        return userRepository.findByCompany(company);
    }

    @Override
    public User addValidUser(User user) {
        if (user.getUserType().equals("Employee")) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User updateValidUser(User user) {
        if (user.getUserType().equals("Employee")) {
            userRepository.save(user);
        }
    }
}
