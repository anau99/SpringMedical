package ASM3.service;

import ASM3.entity.DoctorUser;
import ASM3.entity.Schedule;
import ASM3.entity.User;
import ASM3.form.ScheduleForm;

import java.util.List;

public interface ScheduleService {
    public List<Schedule> getHistoryOfUserId(int userId);
    public void creatASchedule(ScheduleForm scheduleForm, DoctorUser doctorUser, User user);
    public void getInfor(int idSchedule,User user,User doctorUser);
    public List<Schedule>getListScheduleDoctor(int doctorId);
}
