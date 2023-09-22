package ASM3.dao;

import ASM3.entity.Specialization;

import java.util.HashSet;
import java.util.List;

public interface SpecializationDao {
    public List<Specialization> getTopspecializations();
    public List<Specialization> getResultSpecializations(String keyWord);
    public HashSet<Integer> getIDsSpecialization(String keyWord);
    public Specialization getSpecializationById(int idSpecialization);
}
