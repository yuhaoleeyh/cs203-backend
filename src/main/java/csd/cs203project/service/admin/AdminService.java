package csd.cs203project.service.admin;

import csd.cs203project.model.*;


public interface AdminService {
    User addValidSupervisor(User user);

    User updateValidSupervisor(Long id, User user);
}
