package ASM3.service;

import ASM3.entity.Role;

import java.util.List;

public interface RoleService {
    public boolean checkRoleId(int roleId);
    public List<Role> getRoles();
}
