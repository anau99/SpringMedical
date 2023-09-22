package ASM3.validator;

public class PasswordValidator {
    public boolean checkRePassword(String password, String rePassword){
        if(password.equals(rePassword))
            return true;
        return false;
    }
}
