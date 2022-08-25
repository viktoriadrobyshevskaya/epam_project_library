package com.epam.library.project.service;

import com.epam.library.project.entity.Role;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public interface RoleService {

    public void addRole(Role role) throws ServiceException;

    public void deleteRole(int id) throws ServiceException;

    public Role getRoleById(int id) throws ServiceException;

    public List<Role> getAllRoles() throws ServiceException;
}
