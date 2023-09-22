package ASM3.service;

import ASM3.controller.response.ResponsePatient;
import ASM3.dao.PatientDao;
import ASM3.entity.Patient;
import ASM3.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientDao patientDao;

    @Override
    @Transactional
    public List<ResponsePatient> getListPatients(int userId){
        List<ResponsePatient> ans = new ArrayList<>();
        List<Patient>patientList = patientDao.getListPatients(userId);
        for(Patient patient : patientList){
            ResponsePatient responsePatient = new ResponsePatient();
            responsePatient.setName(patient.getName());
            responsePatient.setAddress(patient.getUser().getAddress());
            responsePatient.setGender(patient.getUser().getGender());
            responsePatient.setStatus(patient.getStatus());
            ans.add(responsePatient);
        }
        return ans;
    }

    @Override
    @Transactional
    public void createAPatient(User user, User doctorUser){
        patientDao.createAPatient(user,doctorUser);
    }

    @Override
    @Transactional
    public Patient getApatientByUserId(int userID){
        return patientDao.getApatientByUserId(userID);
    }


}
