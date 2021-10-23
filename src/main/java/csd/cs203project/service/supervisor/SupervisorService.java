package csd.cs203project.service.supervisor;

import csd.cs203project.model.*;
import java.util.List;

public interface SupervisorService {
    List<User> findEmployeesByCompany(String company);

    List<User> findEmployeesAndAdminsUnderCompany(String company);

    User addEmployee(User user);

    User updateEmployee(String email, User newEmployeeInfo);

    void deleteEmployee(String email);
}
