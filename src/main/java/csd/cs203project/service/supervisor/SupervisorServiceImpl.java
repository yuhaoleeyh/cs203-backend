package csd.cs203project.service.supervisor;

import csd.cs203project.repository.user.UserRepository;
import java.util.List;
import java.util.Optional;

import csd.cs203project.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.dao.EmptyResultDataAccessException;


 
@Service
public class SupervisorServiceImpl implements SupervisorService {
    private UserRepository userRepository;
    
    @Autowired
    public SupervisorServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findEmployeesByCompany(String company) {
        return userRepository.findEmployeesByCompany(company, "Employee");
    }

    @Override
    public List<User> findEmployeesAndAdminsUnderCompany(String company) {
        return userRepository.findEmployeesByCompany(company, "Supervisor");
    }

    @Override
    public User addEmployee(User user) {
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if(!u.isPresent()) {
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public User updateEmployee(String email, User newEmployeeInfo) {
        Optional<User> u = userRepository.findByEmail(email);
        if (u.isPresent()){
            User user = u.get();
            user.setName(newEmployeeInfo.getName());
            user.setVaccinationStatus(newEmployeeInfo.getVaccinationStatus());
            user.setSwabTestResult(newEmployeeInfo.getSwabTestResult());
            user.setFetStatus(newEmployeeInfo.getFetStatus());
            user.setCompany(newEmployeeInfo.getCompany());

            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEmployee(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            userRepository.deleteByEmail(email);
        } else {
            throw new EmptyResultDataAccessException(1); 
        }
    }

}
