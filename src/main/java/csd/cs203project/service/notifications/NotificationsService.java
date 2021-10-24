package csd.cs203project.service.notifications;

import csd.cs203project.model.Measures;
import csd.cs203project.model.User;

import java.util.List;

public interface NotificationsService {

    void sendChangedMeasures(List<String> changes, List<User> affectedUsers);
}
