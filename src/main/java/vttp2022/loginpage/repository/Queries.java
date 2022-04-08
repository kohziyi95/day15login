package vttp2022.loginpage.repository;

public class Queries {

    public static final String SQL_INSERT_USER
        = "insert into user(username, password) values (? , sha1(?))";
    
    public static final String SQL_SELECT_USER
        =  "select * from user where username = ?";
}
