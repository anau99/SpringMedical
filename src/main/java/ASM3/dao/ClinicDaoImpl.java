package ASM3.dao;

import ASM3.entity.Clinic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class ClinicDaoImpl implements ClinicDao{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Clinic> getTopClinic(){
        Session session = sessionFactory.getCurrentSession();
        Query theQuery = session.createQuery("From Clinic",Clinic.class);
        return (List<Clinic>) theQuery.getResultList();
    }
    /*TÌm kiếm theo từ khóa
    *@params String keyword :  từ khóa nhận được từ client
    * Output : Một danh sách clinic tìm kiếm tên, địa chỉ, description
     */
    @Override
    public List<Clinic>getResultClinics(String keyWord){
        List<Clinic> clinicList = getTopClinic();
        List<Clinic>ans = new ArrayList<>();
        HashSet<Integer> hashSet = new HashSet<>();
        for(Clinic clinic : clinicList){
            if(clinic.getName().toLowerCase().contains(keyWord)
                || clinic.getAddress().contains(keyWord)
                || clinic.getDescription().contains(keyWord)
                && !hashSet.contains(clinic.getId())){
                ans.add(clinic);
                hashSet.add(clinic.getId());
            }
        }
        return ans;

    }

}
