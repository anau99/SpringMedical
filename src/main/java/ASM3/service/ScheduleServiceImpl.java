package ASM3.service;

import ASM3.dao.ScheduleDao;
import ASM3.entity.DoctorUser;
import ASM3.entity.Schedule;
import ASM3.entity.User;
import ASM3.form.ScheduleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleDao scheduleDao;

    @Override
    @Transactional
    public List<Schedule> getHistoryOfUserId(int userId){
        return scheduleDao.getHistoryOfUserId(userId);
    }

    @Override
    @Transactional
    public void creatASchedule(ScheduleForm scheduleForm, DoctorUser doctorUser, User user){
         scheduleDao.creatASchedule(scheduleForm,doctorUser,user);
    }

    @Override
    @Transactional
    public void getInfor(int idSchedule,User user,User doctorUser){
        scheduleDao.getInfor(idSchedule,user,doctorUser);
    }

    @Override
    @Transactional
    public List<Schedule>getListScheduleDoctor(int doctorId){
       return scheduleDao.getListScheduleDoctor(doctorId);
    }


}
