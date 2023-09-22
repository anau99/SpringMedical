package ASM3.service;

import ASM3.dao.SpecializationDao;
import ASM3.entity.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SpecializationServiceImpl implements SpecializationService{
    @Autowired
   private SpecializationDao specializationDao;

    @Override
    @Transactional
    public List<Specialization> getTopspecializations(){
        List<Specialization> specializationList = specializationDao.getTopspecializations();

        return specializationList;

    }
    @Override
    @Transactional
    public List<Specialization> getResultSpecializations(String keyWord){
        return specializationDao.getResultSpecializations(keyWord);
    }
}
