package ASM3.service;

import ASM3.controller.response.ResponsePatient;
import ASM3.entity.DoctorUser;
import ASM3.form.AddADoctorUserForm;
import ASM3.form.BlockForm;
import ASM3.form.CancelAcceptedScheduleForm;

import java.util.List;

public interface DoctorService {
    public List<DoctorUser>getResultDoctorBySpecialization(String keyWord);
    public DoctorUser isExisted(int doctorId);
    public boolean cancelASchedule(String idUser, CancelAcceptedScheduleForm cancelAcceptedScheduleForm);

    public boolean appceptedASchedule(String idUser, CancelAcceptedScheduleForm cancelAcceptedScheduleForm);

    public void deleteDoctorUser(BlockForm blockForm);

    public void creatADoctorUser(AddADoctorUserForm addADoctorUserForm);

}
