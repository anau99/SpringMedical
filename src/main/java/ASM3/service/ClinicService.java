package ASM3.service;

import ASM3.entity.Clinic;

import java.util.List;

public interface ClinicService {
    public List<Clinic>getTopClinic();
    public List<Clinic>getResultClinics(String keyWord);
}
