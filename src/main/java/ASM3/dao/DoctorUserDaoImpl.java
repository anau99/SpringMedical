package ASM3.dao;

import ASM3.entity.DoctorUser;
import ASM3.entity.Schedule;
import ASM3.entity.Specialization;
import ASM3.entity.User;
import ASM3.form.AddADoctorUserForm;
import ASM3.form.BlockForm;
import ASM3.form.CancelAcceptedScheduleForm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class DoctorUserDaoImpl implements DoctorUserDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private SpecializationDao specializationDao;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<DoctorUser> getListDoctorUser(){
        Session session = sessionFactory.getCurrentSession();
        Query theQuery = session.createQuery("From DoctorUser", DoctorUser.class);
        return (List<DoctorUser>) theQuery.getResultList();
    }

    /*
    *@params : String keyWord: tỪ khóa user nhập vào để tìm kiếm các bác sỹ chuyên khoa tương ứng
    * Output : List Doctor theo chuyên khoa keyWord
     */

    @Override
    public List<DoctorUser> getResultDoctorBySpecialization(String keyWord){
        List<DoctorUser>ans = new ArrayList<>();
        //Lấy các ID của specialization liên quan đến keyword
        HashSet<Integer>hashSet = specializationDao.getIDsSpecialization(keyWord);
        List<DoctorUser>doctorUserList = getListDoctorUser();
        for(DoctorUser doctorUser : doctorUserList){
            if(hashSet.contains(doctorUser.getDoctor().getId()))
                ans.add(doctorUser);
        }
        return ans;
    }

    @Override
    public DoctorUser isExisted(int doctorId){
        Session session = sessionFactory.getCurrentSession();
        Query theQuery = session.createQuery("From DoctorUser Where id = :doctorId",DoctorUser.class);
        theQuery.setParameter("doctorId",doctorId);
        return (DoctorUser) theQuery.uniqueResult();

    }

    @Override
    public boolean cancelASchedule(String idUser, CancelAcceptedScheduleForm cancelAcceptedScheduleForm){
        Session session = sessionFactory.getCurrentSession();
        int userId = Integer.parseInt(idUser);
        String description = cancelAcceptedScheduleForm.getDescription();
        int idSchedule = cancelAcceptedScheduleForm.getIdSchedule();
        Query theQuery = session.createQuery("Update Schedule Set description = :description, cancel = 1 " +
                "Where id = :idSchedule AND  doctorId = :userId AND cancel = 0");
        theQuery.setParameter("description",description);
        theQuery.setParameter("idSchedule",idSchedule);
        theQuery.setParameter("userId",userId);
        int updatedRows =(int) theQuery.executeUpdate();

        return updatedRows > 0;
    }

    @Override
    public boolean appceptedASchedule(String idUser, CancelAcceptedScheduleForm cancelAcceptedScheduleForm){
        Session session = sessionFactory.getCurrentSession();
        int userId = Integer.parseInt(idUser);
        int idSchedule = cancelAcceptedScheduleForm.getIdSchedule();
        Query theQuery = session.createQuery("Update Schedule Set cancel = 2 " +
                "Where id = :idSchedule AND doctorId = :userId AND cancel = 0");
        theQuery.setParameter("idSchedule",idSchedule);
        theQuery.setParameter("userId",userId);
        int updatedRows =(int) theQuery.executeUpdate();

        return updatedRows > 0;
    }

    @Override
    public void deleteDoctorUser(BlockForm blockForm){
        Session session = sessionFactory.getCurrentSession();
        int userID = blockForm.getUserId();
        Query theQuery = session.createSQLQuery("Update doctor_users SET deletedAt = NOW() Where doctorId = :userID");
        theQuery.setParameter("userID",userID);
        theQuery.executeUpdate();
    }

    @Override
    public void creatADoctorUser(AddADoctorUserForm addADoctorUserForm){
       List<User>userList = userDao.getListUser();
       User user = userList.get(userList.size()-1);
        //Session session = sessionFactory.getCurrentSession();
        DoctorUser doctorUser = new DoctorUser();
        doctorUser.setDoctor(user);
        doctorUser.setSpecialization(specializationDao.getSpecializationById(addADoctorUserForm.getIdSpecialization()));
        doctorUser.setDescription(addADoctorUserForm.getDescription()+", "
                +addADoctorUserForm.getAchieve()+", "+addADoctorUserForm.getEducations());
        Session session = sessionFactory.getCurrentSession();
        session.save(doctorUser);
    }


}
