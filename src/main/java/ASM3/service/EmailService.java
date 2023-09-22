package ASM3.service;

import ASM3.entity.Patient;
import ASM3.entity.User;

import java.util.List;

public interface EmailService {
    public boolean sendConfirmationEmail(List<User> userList, Patient patient);
}
