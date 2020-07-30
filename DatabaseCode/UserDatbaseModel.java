package gui;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDatbaseModel extends Database {

    private String table  = "users";
    private String[]
            columns
            = {"user_id", "name", "username", "password"};

    public UserDatbaseModel() throws SQLException{
        super();
    }

    @Override
    public int insertRow(ArrayList<String> data) throws SQLException {

        String sql = String.format("insert into `%s` " +
                "(`%s`,`%s`,`%s`) " +
                "values ('%s', '%s', '%s')",
                table, columns[1], columns[2], columns[3],
                data.get(0), data.get(1), data.get(2)
                );
        return statement.executeUpdate(sql);
    }

    @Override
    public int updateRow(int pkId, ArrayList<String> data) throws SQLException {
        return 0;
    }

    @Override
    public int deleteRow(int pkId) throws SQLException {
        return 0;
    }

    @Override
    public int getRow(int pkId) throws SQLException {
        return 0;
    }

    @Override
    public int getRows(int start_pkId, int end_pkId) throws SQLException {
        return 0;
    }
}
