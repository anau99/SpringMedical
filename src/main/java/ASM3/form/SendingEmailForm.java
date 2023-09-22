package ASM3.form;

public class SendingEmailForm {
    //Doctor nhập vào 1 id là userPatient để xác định email của bệnh nhân
    //Xác định hồ sơ bệnh án để g
    private int userPatientId;

    public SendingEmailForm() {
    }

    public int getUserPatientId() {
        return userPatientId;
    }

    public void setUserPatientId(int userPatientId) {
        this.userPatientId = userPatientId;
    }
}
