package gui;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseRunner {

    public static void main(String[] args) {

        try{
            UserDatbaseModel db =
                    new UserDatbaseModel();
            System.out.println("Success!");
            ArrayList<String> data = new ArrayList<String>();
            data.add("Insert Example 1");
            data.add("example1");
            data.add("password");
            db.insertRow(data);

        }
        catch (SQLException e){

            System.err.println(e);
        }

    }

}
