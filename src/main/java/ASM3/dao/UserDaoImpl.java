package ASM3.dao;

import ASM3.form.BlockForm;
import ASM3.form.ForgetForm;
import ASM3.form.LoginForm;
import ASM3.form.UserRegisterForm;
import ASM3.entity.Role;
import ASM3.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public boolean checkEmail(String email){
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> theQuery = currentSession.createQuery("FROM User WHERE email = :emailParam", User.class);
        theQuery.setParameter("emailParam", email);
        List<User> usersWithSameEmail = theQuery.getResultList();
        return usersWithSameEmail.isEmpty();
    }

    @Override
    public void register(UserRegisterForm userRegisterForm){
        Session currentSession = sessionFactory.getCurrentSession();
        User user = new User();
        user.setName(userRegisterForm.getName());
        user.setGender(userRegisterForm.getGender());
        user.setEmail(userRegisterForm.getEmail());
        user.setPhone(userRegisterForm.getPhone());
        user.setAddress(userRegisterForm.getAddress());
        Role role = new Role();
        role.setId(userRegisterForm.getGetRoleId());
        user.setRole(role);
       // BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userRegisterForm.getPassword()));
        // Tạo ngày tạo và định dạng theo "dd/MM/yyyy"
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(currentDate);
        user.setCreatedAt(formattedDate);
        user.setIsActive(0);
        currentSession.save(user);
    }

    @Override
    public boolean isValidLogin(LoginForm loginForm,StringBuilder sb){
        Session currentSession = sessionFactory.getCurrentSession();
        String password = loginForm.getPassword().trim();
        String email = loginForm.getEmail().trim();
        Query theQuery = currentSession.createQuery("From User where email = :email");
        theQuery.setParameter("email",email);
        User user = (User) theQuery.uniqueResult();
        if(user!=null){
            if(passwordEncoder.matches(password,user.getPassword())){
                sb.append(user.getId());
                sb.append("_");
                sb.append(user.getRole().getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public void updatePassword(ForgetForm forgetForm){
        Session currentSession = sessionFactory.getCurrentSession();
        //Mã hóa mật khẩu
        String encoredPassword = passwordEncoder.encode(forgetForm.getPassword().trim());
        String email = forgetForm.getEmail();
        try {
            Query theQuery = currentSession.createQuery("UPDATE User SET password = :encodedPassword WHERE email = :email");
            theQuery.setParameter("encodedPassword", encoredPassword);
            theQuery.setParameter("email", email);
            theQuery.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(int userId){
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("From User where id = :userId");
        query.setParameter("userId",userId);
        return (User) query.uniqueResult();
    }

    @Override
    public void unBlockOrBlockAUser(BlockForm blockForm){
        Session currentSession = sessionFactory.getCurrentSession();
        //Nếu muốn block tài khoản
        try {
            int userID = blockForm.getUserId();
            if(blockForm.getDecide()==0){
                //Update isActive = 1 là block
                Query query = currentSession.createQuery("Update User Set isActive = 1 Where id = :userID ");
                query.setParameter("userID",userID);
                query.executeUpdate();
            }
            else {
                //Nếu muốn unblock tài khoản update isActive =2
                Query query = currentSession.createQuery("Update User Set isActive = 2 Where id = :userID ");
                query.setParameter("userID",userID);
                query.executeUpdate();
            }
            blockForm.setOK(true);

        }
        catch (Exception e) {
            blockForm.setOK(false);
        }


    }

    @Override
    public List<User> getListUser(){
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("From User",User.class);

        return (List<User>)query.getResultList();
    }

    @Override
    public List<User> getEmailDoctorAndPatient(int userDoctorId, int userIdPatient){
        List<User>users = getListUser();
        List<User>ans = new ArrayList<>();
        for (User user : users){
            if(user.getId()==userDoctorId){
                ans.add(user);
                break;
            }
        }
        for (User user : users){
            if(ans.size()==2)
                break;
            if(user.getId()==userIdPatient){
                ans.add(user);
                break;
            }
        }
        return ans;
    }


}
