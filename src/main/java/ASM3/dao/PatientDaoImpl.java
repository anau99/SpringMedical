package ASM3.dao;

import ASM3.entity.DoctorUser;
import ASM3.entity.Patient;
import ASM3.entity.Status;
import ASM3.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PatientDaoImpl implements PatientDao{
    @Autowired
    private UserDao userDao;

    @Autowired
    private DoctorUserDao doctorUserDao;
    @Autowired
    private SessionFactory sessionFactory ;

    @Override
    public int isExistedPatientByUserId(int userId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("Select doctorId From Patient Where userId = :userId");
        query.setParameter("userId",userId);
        try {
            Integer result = (Integer) query.uniqueResult();
            if (result != null) {
                return result;
            }
        } catch (Exception e) {
            return -1;
        }

        return 0;
    }


    @Override
    public List<Patient> getListPatients(int userId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("From Patient Where doctorId = :userId");
        query.setParameter("userId",userId);
        return (List<Patient>) query.getResultList();
    }

    @Override
    public void createAPatient(User user, User doctorUser){
        Status status = new Status();
        status.setId(3);//Tình trạng bắt đầu khám
        Session session = sessionFactory.getCurrentSession();
        Patient patient = new Patient();
        patient.setUser(user);
        patient.setStatus(status);
        int idUser = doctorUser.getId();
        try {
            Query query = session.createQuery(" From DoctorUser Where doctorId = :idUser");
            query.setParameter("idUser",idUser);
            DoctorUser doctorUser1 = (DoctorUser) query.uniqueResult();
            patient.setDoctor(doctorUser1);
            session.save(patient);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Patient getApatientByUserId(int userID){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(" From Patient Where userId = :userID");
        query.setParameter("userID",userID);
        return (Patient) query.uniqueResult();
    }
}
