package ASM3.service;

import ASM3.dao.RoleDao;
import ASM3.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleDao roleDao;
    @Override
    @Transactional
    public boolean checkRoleId(int roleId){
        return roleDao.checkRoleId(roleId);
    }

    @Override
    @Transactional
    public List<Role> getRoles(){
        return roleDao.getRoles();
    }

}
