package ASM3.dao;

import ASM3.form.BlockForm;
import ASM3.entity.User;
import ASM3.form.ForgetForm;
import ASM3.form.LoginForm;
import ASM3.form.UserRegisterForm;

import java.util.List;

public interface UserDao {
    public boolean checkEmail(String email);
    public void register(UserRegisterForm userRegisterForm);

    public boolean isValidLogin(LoginForm loginForm,StringBuilder sb);
    public void updatePassword(ForgetForm forgetForm);
    public User getUserById(int userId);
    public void unBlockOrBlockAUser(BlockForm blockForm);

    public List<User>getListUser();
    public List<User> getEmailDoctorAndPatient(int userDoctorId, int userIdPatient);

}
