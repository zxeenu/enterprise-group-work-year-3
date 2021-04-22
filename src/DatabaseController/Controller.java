package DatabaseController;


import DatabaseController.Entities.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Controller {

    public Controller(String c) throws SQLException {
        var Connection = new JdbcConnectionSource(c);
        this.UserAccountDao = DaoManager.createDao(Connection, User.class);

        if (!UserAccountDao.isTableExists()) TableUtils.createTable(Connection, User.class);
    }

    public Dao<User, String> UserAccountDao = null;
}
