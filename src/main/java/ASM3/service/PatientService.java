package ASM3.service;

import ASM3.controller.response.ResponsePatient;
import ASM3.entity.DoctorUser;
import ASM3.entity.Patient;
import ASM3.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PatientService {

    public List<ResponsePatient> getListPatients(int userId);
    public void createAPatient(User user, User doctorUser);
    public Patient getApatientByUserId(int userID);


}
