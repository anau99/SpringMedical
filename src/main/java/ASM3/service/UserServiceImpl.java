package ASM3.service;

import ASM3.form.BlockForm;
import ASM3.entity.User;
import ASM3.form.ForgetForm;
import ASM3.form.LoginForm;
import ASM3.form.UserRegisterForm;
import ASM3.dao.UserDao;
import ASM3.validator.EmailValidator;
import ASM3.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    @Override
    @Transactional
    public boolean checkEmail(String email){
        return userDao.checkEmail(email);
    }

    @Override
    public boolean checkFormatEmail(String email){
        EmailValidator emailValidator = new EmailValidator();
        return emailValidator.isValid(email.trim());
    }

    @Override
    public boolean checkPassword(String password, String rePassword){
        if(password==null || rePassword==null)return false;
        PasswordValidator passwordValidator = new PasswordValidator();
        return passwordValidator.checkRePassword(password,rePassword);
    }

    @Override
    @Transactional
    public void register(UserRegisterForm userRegisterForm){
        userDao.register(userRegisterForm);
    }

    @Override
    @Transactional
    public boolean isValidLogin(LoginForm loginForm,StringBuilder sb){
        return userDao.isValidLogin(loginForm,sb);
    }

    @Override
    @Transactional
    public void updatePassword(ForgetForm forgetForm){
        userDao.updatePassword(forgetForm);
    }

    @Override
    @Transactional
    public User getUserById(int userId){
        return userDao.getUserById(userId);
    }

    @Override
    @Transactional
    public void unBlockOrBlockAUser(BlockForm blockForm){
        userDao.unBlockOrBlockAUser(blockForm);
    }

    @Override
    @Transactional
    public List<User> getEmailDoctorAndPatient(int userDoctorId, int userIdPatient){
        return userDao.getEmailDoctorAndPatient(userDoctorId, userIdPatient);
    }
}
