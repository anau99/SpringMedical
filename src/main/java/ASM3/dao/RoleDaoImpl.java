package ASM3.dao;

import ASM3.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Role> getRoles(){
        Session currentSession = sessionFactory.getCurrentSession();
        return (List<Role>) currentSession.createQuery("From Role").getResultList();
    }

    @Override
    public boolean checkRoleId(int roleId){
        Session currentSession = sessionFactory.getCurrentSession();
        try {
            Query theQuery = currentSession.createQuery("From Role where id = :roleId",Role.class);
            theQuery.setParameter("roleId",roleId);
            return !theQuery.getResultList().isEmpty();
        } catch (Exception e) {
            // In ra thông tin ngoại lệ để xác định nguyên nhân cụ thể
            e.printStackTrace();
            return false; // Hoặc làm xử lý khác tùy vào trường hợp
        }
    }


}
