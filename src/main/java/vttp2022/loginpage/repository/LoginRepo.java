package vttp2022.loginpage.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import static vttp2022.loginpage.repository.Queries.*;

import java.util.Optional;

@Repository
public class LoginRepo {
    @Autowired
    JdbcTemplate template;

    public boolean setUser(String username, String password){
        int added = template.update(SQL_INSERT_USER, username, password);
        return (added>0);
    }

    public Optional<String> getUser(String username){
        final SqlRowSet results = template.queryForRowSet(SQL_SELECT_USER, username);
        if (!results.next())
            return Optional.empty();

        return Optional.of(results.getString("password"));
    }

}
