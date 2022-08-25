package com.epam.library.project.dao;

import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.entity.Role;

import java.util.List;

public interface RoleDAO {
    public void addRole(Role role) throws DAOException;

    public void deleteRole(int id) throws DAOException;

    public Role getRoleById(int id) throws DAOException;

    public List<Role> getAllRoles() throws DAOException;
}
