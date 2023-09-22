package ASM3.dao;

import ASM3.entity.Role;

import java.util.List;

public interface RoleDao{
    public List<Role> getRoles();
    public boolean checkRoleId(int roleId);

}
