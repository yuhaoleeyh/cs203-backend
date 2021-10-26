package csd.cs203project.exception.supervisor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmployeeExistsException extends RuntimeException {
    public EmployeeExistsException(String email) {
        super("This email already exists: " + email);
    }
}
