package com.epam.library.project.service.impl;

import com.epam.library.project.dao.RoleDAO;
import com.epam.library.project.dao.exception.DAOException;
import com.epam.library.project.dao.factory.DAOFactory;
import com.epam.library.project.entity.Role;
import com.epam.library.project.service.RoleService;
import com.epam.library.project.service.exception.ServiceException;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO = DAOFactory.getInstance().getRoleDAO();

    @Override
    public void addRole(Role role) throws ServiceException {
        try {
            roleDAO.addRole(role);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteRole(int id) throws ServiceException {
        try {
            roleDAO.deleteRole(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Role getRoleById(int id) throws ServiceException {
        try {
            return roleDAO.getRoleById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Role> getAllRoles() throws ServiceException {
        try {
            return roleDAO.getAllRoles();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
