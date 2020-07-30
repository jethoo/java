package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class LoginController {

    @FXML
    private Label error;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;


    public LoginController(){
        System.out.println("Constructor");
    }

    @FXML
    private void loginClick(ActionEvent event){
        System.out.println("Login Clicked");

        /*

            evaluate the username and password values
                if username = admin
                AND password = pass
                    output to console "Success"
                else
                    output to error label
                        a failure message

        if(username.getText().equals("admin") &&
            password.getText().equals("pass")){

            System.out.println("Success!");
            error.setText("");
        }
        else{
            error.setText("Username or password incorrect");
        }
*/
        if(LoginModel
                .validate(username.getText(),
                        password.getText())){

            //do something
            /*
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Welcome to your portal");
            alert.show();
*/
            try{
                new WindowChanger()
                        .newWindow("welcome",
                                "Welcome, " + username.getText(),
                                false);
            }
            catch (Exception e){
                error.setText(e.getMessage());
            }


        }
        else{
            error.setText("Username and/or Password incorrect");
        }

    }
    @FXML
    private void registerClick(ActionEvent event){
        System.out.println("Register Clicked");

       try{
           new WindowChanger()
                   .newWindow("register",
                           "Register Page",
                           false);
       }
       catch (Exception e){
           error.setText(e.getMessage());
       }
    }

    @FXML
    private void initialize(){

        System.out.println("Init!");
        error.setText("");
        /*
        username.setText("Ben");
        password.setText("comp1011");
        */
    }

}
