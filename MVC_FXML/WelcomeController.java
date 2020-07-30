package gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class WelcomeController {

    @FXML
    Label name;

    @FXML
    private void gotoBP(MouseEvent event){

        WindowChanger wc = new WindowChanger();

        try{
            wc.newWindow("borderpane", "Border Pane Example", false);
            wc.close(event);

        }
        catch (Exception e){
            System.err.println(e);
        }

    }


    @FXML
    private void onEnter(MouseEvent event){

        Node node = (Node)event.getSource();

        Stage stage = (Stage)node.getScene().getWindow();

        String title = stage.getTitle();

        String username = title.split(",")[1].trim();
        System.out.println(username);

        try{
            name.setText(new UserDatbaseModel().getNameFromUsername(username));
        }
        catch (SQLException e){
            System.err.println(e);
        }

    }

}
