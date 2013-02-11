package net.golovach.dao.dao;

import net.golovach.dao.bean.User;
import net.golovach.dao.exception.DBException;
import net.golovach.dao.exception.NotUniqueUserEmailException;
import net.golovach.dao.exception.NotUniqueUserLoginException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public List<User> selectAll() throws DBException;
    public List<User> selectAllWithoutDriverManager() throws DBException, SQLException;
    public int deleteById(int id) throws DBException;

    public void insert(User user) throws DBException, NotUniqueUserLoginException, NotUniqueUserEmailException;
    public int InsertWithReturnGeneratedKeys(User user) throws DBException, NotUniqueUserLoginException, NotUniqueUserEmailException;
}
