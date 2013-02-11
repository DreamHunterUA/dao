package net.golovach.dao;

import net.golovach.dao.bean.User;
import net.golovach.dao.dao.UserDao;
import net.golovach.dao.dao.impl.UserDaoJdbc;
import net.golovach.dao.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleTest {

    public static void main(String[] args) throws DBException, SQLException {
        TestMethodWithDriverManager();
    }

    private static void TestMethodWithDriverManager() throws DBException, SQLException {
        System.out.println("Test Method With DriverManager Start");
        UserDao dao = new UserDaoJdbc();
        System.out.println("ALL CURRENT USERS:");
        for (User user : dao.selectAll()) {
            System.out.println("    " + user.toString());
        }
        System.out.println("Test Method With DriverManager End");
        System.out.println("Test Method Without DriverManager Start");
        System.out.println("ALL CURRENT USERS:");
        for (User user : dao.selectAllWithoutDriverManager()) {
            System.out.println("    " + user.toString());
        }
        System.out.println("Test Method Without DriverManager Stop");
        System.out.println("DELETE:");
        for (User user : dao.selectAll()) {
            dao.deleteById(user.getId());
            System.out.println("    User with id = " + user.getId() + " deleted");
        }
        int index = -1;
        System.out.println("INSERT NEW:");
        index =  dao.InsertWithReturnGeneratedKeys(newUser(1, "Mike", "x@a.com"));
        System.out.println("    'Mike' inserted with ID="+index);
        index =  dao.InsertWithReturnGeneratedKeys(newUser(2, "Sara", "y@b.com"));
        System.out.println("    'Sara' inserted with ID="+index);
        index =  dao.InsertWithReturnGeneratedKeys(newUser(3, "Anna", "zcx.com"));
        System.out.println("    'Anna' inserted with ID="+index);
        index =  dao.InsertWithReturnGeneratedKeys(newUser(4,"Steve","steve@com.ua"));
        System.out.println("    'Steve' inserted with ID="+index);
        System.out.println("ALL CURRENT USERS:");
        for (User user : dao.selectAll()) {
            System.out.println("    " + user.toString());
        }
        System.out.println("DELETE:");
        for (User user : dao.selectAll()) {
            dao.deleteById(user.getId());
            System.out.println("    User with id = " + user.getId() + " deleted");
        }
        List<User> Users = new ArrayList<User>();
        Users.add(newUser(1, "Mike", "x@a.com"));
        Users.add(newUser(2, "Sara", "y@b.com"));
        Users.add(newUser(3, "Anna", "zcx.com"));
        Users.add(newUser(4,"Steve","steve@com.ua"));
        System.out.println("INSERT NEW LIST OF USERS");
        dao.BulkInsert(Users);
        for (User user:Users){
            System.out.println( user.getLogin()+" inserted ");
        }
        System.out.println("ALL CURRENT USERS:");
        for (User user : dao.selectAll()) {
            System.out.println("    " + user.toString());
        }

    }

    public static User newUser(int id, String login, String email) {
        User result = new User(id);
        result.setLogin(login);
        result.setEmail(email);
        return result;
    }
}
