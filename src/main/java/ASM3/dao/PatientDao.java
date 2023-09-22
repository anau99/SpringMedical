package ASM3.dao;

import ASM3.entity.Patient;
import ASM3.entity.User;

import java.util.List;

public interface PatientDao {
    public int isExistedPatientByUserId(int userId);

    public List<Patient> getListPatients(int userId);
    public void createAPatient(User user, User doctorUser);
    public Patient getApatientByUserId(int userID);

}
