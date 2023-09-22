package ASM3.dao;

import ASM3.entity.DoctorUser;
import ASM3.entity.Schedule;
import ASM3.entity.Specialization;
import ASM3.entity.User;
import ASM3.form.ScheduleForm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Repository
public class ScheduleDaoImpl implements ScheduleDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PatientDao patientDao;

    @Override
    public List<Schedule>  getListSchedule(){
        Session session = sessionFactory.getCurrentSession();
        Query theQuery = session.createQuery("From Schedule",Schedule.class);
        return (List<Schedule>) theQuery.getResultList();
    }

    @Override
    public List<Schedule> getHistoryOfUserId(int userId){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("From Schedule Where userID = :userId");
        query.setParameter("userId",userId);
        return (List<Schedule>) query.getResultList();

    }

    @Override
    public void creatASchedule(ScheduleForm scheduleForm, DoctorUser doctorUser, User user){
        Session session = this.sessionFactory.getCurrentSession();
        Schedule schedule = new Schedule();
        schedule.setDoctor(doctorUser.getDoctor());
        schedule.setTime(scheduleForm.getTimeLine());
        schedule.setUser(user);
        schedule.setSumBooking(scheduleForm.getSumBooking());
        Date currentDate = new Date();
        schedule.setCreateAt(currentDate);
        session.save(schedule);

    }

    @Override
    public void getInfor(int idSchedule,User user,User doctorUser){
       List<Schedule>scheduleList = getListSchedule();
       for(Schedule schedule : scheduleList){
           if(schedule.getId()==idSchedule){
               User tempUser = schedule.getUser();
               User tempDoctorUser= schedule.getDoctor();
               user.setId(tempUser.getId());
               user.setEmail(tempUser.getEmail());
               user.setRole(tempUser.getRole());

               doctorUser.setId(tempDoctorUser.getId());
               doctorUser.setEmail(tempDoctorUser.getEmail());
               doctorUser.setRole(tempDoctorUser.getRole());

               break;
           }
       }
    }

    @Override
    public List<Schedule>getListScheduleDoctor(int doctorId){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("From Schedule Where doctorId = :doctorId");
        query.setParameter("doctorId",doctorId);
        return (List<Schedule>) query.getResultList();
    }


}
