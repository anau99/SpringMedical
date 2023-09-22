package ASM3.dao;

import ASM3.entity.DoctorUser;
import ASM3.entity.Schedule;
import ASM3.entity.Specialization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Repository
public class SpecializationDaoImpl implements SpecializationDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    DoctorUserDao doctorUserDao;

    @Autowired
    ScheduleDao scheduleDao;

    /*
    *  Tìm các chuyên khoa được đặt lịch nhiều nhất
    *  Mỗi 1 dòng schedules trong DB tương ứng với 1 doctor. Khi đó có những dòng schedule có cùng bác sỹ giống nhau
       Mỗi 1 bác sỹ như vậy trong bảng doctor_user của DB sẽ tương ứng với 1 chuyên khoa (specialization)
     */
    @Override
    public List<Specialization> getTopspecializations(){
        List<Specialization>ans = new ArrayList<>();
        int cnt = 0; //Dùng để lấy 5 chuyên khoa có số lượng đặt lịch khám cao nhất
        HashSet<Integer>hashSet = new HashSet<>();
        HashSet<Integer> hashSetIdSpecialization = new HashSet<>();
        //Bước 1 : dếm số lượng bác sỹ trong bảng schedule và sắp sắp  theo thứ tự giảm dần theo tần suất xuất hiện của doctorId
        HashMap<Integer, Integer> mapCntIdDoctor = new HashMap<>();
        List<Schedule> scheduleList = scheduleDao.getListSchedule();
        for(Schedule schedule : scheduleList){
            if(mapCntIdDoctor.containsKey(schedule.getDoctor().getId())){
                mapCntIdDoctor.put(schedule.getDoctor().getId(),mapCntIdDoctor.get(schedule.getDoctor().getId())+1);
            }
            else
                mapCntIdDoctor.put(schedule.getDoctor().getId(),1);
        }
        //Bước 2 sorting
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(mapCntIdDoctor.entrySet());
        Collections.sort(entryList, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        //Bước 3 Lấy 5 doctorId được đặt lịch nhiều nhất
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : entryList) {
            if(index>=5) break;//top Five
            hashSet.add(entry.getKey());
            index++;
        }
        //Bước 5: Lấy  SpecializationID mà có idDoctor của doctor nằm trong mapCntIdDoctor
        List<DoctorUser> doctorUserList = doctorUserDao.getListDoctorUser();
        for (DoctorUser doctorUser : doctorUserList){
            if(hashSet.contains(doctorUser.getId()))
                hashSetIdSpecialization.add(doctorUser.getSpecialization().getId());
        }
        //Bước 6 : Lấy 5 specialization được đặt lịch khám cao nhất

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("From Specialization",Specialization.class);
        List<Specialization>specializationList =(List<Specialization>) query.getResultList();
        for (Specialization specialization : specializationList){
            if(cnt>=5) break;
            if(hashSetIdSpecialization.contains(specialization.getId())){
                ans.add(specialization);
            }
        }
        return ans;
    }

    @Override
    public List<Specialization> getResultSpecializations(String keyWord){
        Session session = sessionFactory.getCurrentSession();
        List<Specialization> result = new ArrayList<>();
        Query query = session.createQuery("From Specialization",Specialization.class);
        List<Specialization>specializationList =(List<Specialization>) query.getResultList();
        HashSet<Integer>hashSet = new HashSet<>();
        for(Specialization specialization : specializationList){
            if(specialization.getName().toLowerCase().contains(keyWord)
                    ||specialization.getDescription().contains(keyWord)
                    && !hashSet.contains(specialization.getId())){
                result.add(specialization);
                hashSet.add(specialization.getId());
            }
        }
        return result;
    }
    @Override
    public HashSet<Integer> getIDsSpecialization(String keyWord){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("From Specialization",Specialization.class);
        HashSet<Integer>hashSet = new HashSet<>();
        List<Specialization>temp =(List<Specialization>) query.getResultList();
        for (Specialization specialization : temp){
            if(specialization.getName().toLowerCase().startsWith(keyWord)
                    || specialization.getDescription().toLowerCase().contains(keyWord))
                hashSet.add(specialization.getId());
        }
        return hashSet;
    }

    @Override
    public Specialization getSpecializationById(int idSpecialization){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("From Specialization Where id = :idSpecialization");
        query.setParameter("idSpecialization",idSpecialization);
        List<Specialization>specializationList =(List<Specialization>) query.getResultList();
        return specializationList.get(0);
    }

}
