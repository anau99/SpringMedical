package ASM3.controller.response;

import ASM3.entity.Clinic;
import ASM3.entity.Specialization;

import java.util.List;

public class GeneralSearch {
    private String labelClinic;
    private List<Clinic> clinicList;
    private String labelSpecialization;
    private List<Specialization> specializationList;

    public GeneralSearch() {
        this.labelClinic = "Danh Sách Cơ Sở Y Tế";
        this.labelSpecialization ="Danh Sách Chuyên Khoa";
    }

    public String getLabelClinic() {
        return labelClinic;
    }

    public void setLabelClinic(String labelClinic) {
        this.labelClinic = labelClinic;
    }

    public List<Clinic> getClinicList() {
        return clinicList;
    }

    public void setClinicList(List<Clinic> clinicList) {
        this.clinicList = clinicList;
    }

    public String getLabelSpecialization() {
        return labelSpecialization;
    }

    public void setLabelSpecialization(String labelSpecialization) {
        this.labelSpecialization = labelSpecialization;
    }

    public List<Specialization> getSpecializationList() {
        return specializationList;
    }

    public void setSpecializationList(List<Specialization> specializationList) {
        this.specializationList = specializationList;
    }
}
