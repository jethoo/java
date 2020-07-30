package gui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


        String sql = String.format("update `%s` set `%s` = ?, " +
                "`%s` = ?, `%s` = ? " +
                "where `%s` = ?", table, columns[1], columns[2], columns[3],
                columns[0]);
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(3, data.get(2));
        statement.setString(2, data.get(1));
        statement.setString(1, data.get(0));
        statement.setInt(4, pkId);

        return statement.executeUpdate();

    }
    public String getNameFromUsername(String username) throws SQLException{

        String sql = String.format("select `%s` from `%s` " +
                "where `%s` = ?", columns[1], table, columns[2]);
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        else{
            return "";
        }

    }
    public boolean authenticate(String username, String password)
    throws SQLException{

        String sql = String.format(
                "select count(*) as `num` from `%s` where " +
                        "`%s` = ? and `%s` = ?", table, columns[2], columns[3]);

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) > 0;

    }

    @Override
    public int deleteRow(int pkId) throws SQLException {
        return 0;
    }

    @Override
    public String getRow(int pkId) throws SQLException {
        String sql = String.format(
                "select * from `%s` where `%s` = %d",
                table, columns[0], pkId
        );

        ResultSet resultSet = statement.executeQuery(sql);
        String data = "";
        while(resultSet.next()){

            for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++){
                /*
                System.out.println("Column = "
                        + resultSet.getMetaData().getColumnName(i) + ", "
                        + "Value 1= " + resultSet.getObject(i) + ", "
                        + "Value 2= " + resultSet.getObject(resultSet.getMetaData().getColumnName(i))
                );*/
                data += resultSet.getObject(i);

                if(i < resultSet.getMetaData().getColumnCount()){
                    data += ",";
                }
            }

        }
        return data;
    }

    @Override
    public String getRows(int start_pkId, int end_pkId) throws SQLException {
        return null;
    }
}
