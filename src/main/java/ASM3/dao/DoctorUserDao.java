package ASM3.dao;

import ASM3.entity.DoctorUser;
import ASM3.form.AddADoctorUserForm;
import ASM3.form.BlockForm;
import ASM3.form.CancelAcceptedScheduleForm;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface DoctorUserDao {
    public List<DoctorUser> getListDoctorUser();
    public List<DoctorUser> getResultDoctorBySpecialization(String keyWord);
    public DoctorUser isExisted(int doctorId);
    public boolean cancelASchedule(String idUser, CancelAcceptedScheduleForm cancelAcceptedScheduleForm);
    public boolean appceptedASchedule(String idUser, CancelAcceptedScheduleForm cancelAcceptedScheduleForm);
    public void deleteDoctorUser(BlockForm blockForm);
    public void creatADoctorUser(AddADoctorUserForm addADoctorUserForm);



}
