package ASM3.service;


import ASM3.entity.Specialization;

import java.util.List;

public interface SpecializationService {
    public List<Specialization> getTopspecializations();
    public List<Specialization> getResultSpecializations(String keyWord);
}
