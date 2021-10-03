package csd.cs203project.service.supervisor;

import csd.cs203project.repository.user.UserRepository;
import java.util.List;
import csd.cs203project.model.*;
import org.springframework.stereotype.Service;

 
@Service
public class SupervisorServiceImpl implements SupervisorService {
    private UserRepository users;
    

    public SupervisorServiceImpl(UserRepository users){
        this.users = users;
    }

    @Override
    public List<User> findByCompany(String company) {
        return users.findByCompany(company);
    }
}
