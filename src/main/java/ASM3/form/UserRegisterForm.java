package ASM3.form;

public class UserRegisterForm {
    private String name;
    private String email;
    private String password;
    private String rePassword;
    private String address;
    private String phone;
    private String gender;
    private int getRoleId;

    public UserRegisterForm() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return this.rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRoleId(int id) {
        this.getRoleId = id;
    }

    public int getGetRoleId() {
        return this.getRoleId;
    }
}
