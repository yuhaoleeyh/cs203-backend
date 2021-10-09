package csd.cs203project.service.supervisor;

import csd.cs203project.model.*;
import java.util.List;

public interface SupervisorService {
    List<User> findByCompany(String company);

    User addValidUser(User user);

    User updateValidUser(User user);
}
