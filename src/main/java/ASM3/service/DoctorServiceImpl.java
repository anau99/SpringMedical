package ASM3.service;
import ASM3.dao.DoctorUserDao;
import ASM3.entity.DoctorUser;
import ASM3.form.AddADoctorUserForm;
import ASM3.form.BlockForm;
import ASM3.form.CancelAcceptedScheduleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorUserDao doctorUserDao;
    @Override
    @Transactional
    public List<DoctorUser> getResultDoctorBySpecialization(String keyWord){
        return doctorUserDao.getResultDoctorBySpecialization(keyWord);
    }

    @Override
    @Transactional
    public DoctorUser isExisted(int doctorId){
        return doctorUserDao.isExisted(doctorId);
    }

    @Override
    @Transactional
    public boolean cancelASchedule(String idUser, CancelAcceptedScheduleForm cancelAcceptedScheduleForm){
        return doctorUserDao.cancelASchedule(idUser,cancelAcceptedScheduleForm);
    }

    @Override
    @Transactional
    public boolean appceptedASchedule(String idUser, CancelAcceptedScheduleForm cancelAcceptedScheduleForm){
        return doctorUserDao.appceptedASchedule(idUser,cancelAcceptedScheduleForm);
    }

    @Override
    @Transactional
    public void deleteDoctorUser(BlockForm blockForm){
        doctorUserDao.deleteDoctorUser(blockForm);
    }

    @Override
    @Transactional
    public void creatADoctorUser(AddADoctorUserForm addADoctorUserForm){
        doctorUserDao.creatADoctorUser(addADoctorUserForm);
    }

}
