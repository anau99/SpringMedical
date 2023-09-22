package ASM3.controller.response;

import ASM3.entity.DoctorUser;

import java.util.List;

public class SearchDoctorBySpecialization {
    private List<DoctorUser> doctorUserList;

    public SearchDoctorBySpecialization() {
    }

    public List<DoctorUser> getDoctorUserList() {
        return doctorUserList;
    }

    public void setDoctorUserList(List<DoctorUser> doctorUserList) {
        this.doctorUserList = doctorUserList;
    }
}
