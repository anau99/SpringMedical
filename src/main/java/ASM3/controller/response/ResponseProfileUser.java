package ASM3.controller.response;

import ASM3.entity.Schedule;
import ASM3.entity.User;

import java.util.List;

public class ResponseProfileUser {
    private String labelProfile;
    private User user;
    private String labelHistory;
    private List<Schedule> listHistoryOfPatient;

    public ResponseProfileUser() {
        this.labelProfile = "Thông tin cá nhân";
        this.labelHistory = "Lich sử khám bệnh";
    }

    public String getLabelProfile() {
        return labelProfile;
    }

    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public void setLabelProfile() {
        this.labelProfile = "Thông tin cá nhân";
    }

    public String getLabelHistory() {
        return labelHistory;
    }

    public void setLabelHistory() {
        this.labelHistory = "Lich sử khám bệnh";
    }

    public List<Schedule> getListHistoryOfPatient() {
        return listHistoryOfPatient;
    }

    public void setListHistoryOfPatient(List<Schedule> listHistoryOfPatient) {
        this.listHistoryOfPatient = listHistoryOfPatient;
    }
}
