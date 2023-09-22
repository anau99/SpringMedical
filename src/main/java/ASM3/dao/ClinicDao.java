package ASM3.dao;

import ASM3.entity.Clinic;

import java.util.List;

public interface ClinicDao {
    public List<Clinic> getTopClinic();
    public List<Clinic>getResultClinics(String keyWord);
}
