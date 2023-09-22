package ASM3.service;

import ASM3.dao.ClinicDao;
import ASM3.entity.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClinicServiceImpl implements  ClinicService{
    @Autowired
    private ClinicDao clinicDao;

    @Override
    @Transactional
    public List<Clinic> getTopClinic(){
        return clinicDao.getTopClinic();
    }

    @Override
    @Transactional
    public List<Clinic>getResultClinics(String keyWord){
        return clinicDao.getResultClinics(keyWord);
    }
}
