package gui;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowChanger {

    public void newWindow(
            String filename, String title,
            boolean resizable
    ) throws Exception{
        Stage stage = new Stage();
        Parent root = FXMLLoader
                .load(getClass()
                        .getResource(filename + ".fxml"));
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.setResizable(resizable);
        stage.show();


    }



    public void close(Event event){

        Node node =(Node)event.getSource();
        node.getScene().getWindow().hide();

    }
}
