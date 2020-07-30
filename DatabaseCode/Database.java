package gui;
import java.sql.*;
import java.util.ArrayList;

public abstract class Database {


    Connection connection;
    Statement statement;
    public Database() throws SQLException{

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/comp1011_thurs_w20",
                            "root",
                            ""
                    );
            statement = connection.createStatement();

    }
    public abstract int insertRow(ArrayList<String> data) throws SQLException;
    public abstract int updateRow(int pkId, ArrayList<String> data) throws SQLException;
    public abstract int deleteRow(int pkId) throws SQLException;
    public abstract int getRow(int pkId) throws SQLException;
    public abstract int getRows(int start_pkId, int end_pkId) throws SQLException;

}
