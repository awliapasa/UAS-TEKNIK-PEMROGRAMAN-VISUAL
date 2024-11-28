/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainController implements Initializable {
    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignup;
    
    @FXML
    private Button btnLogin2;

    @FXML
    private Button btnSignup2;
    
    @FXML
    private Button btnDaftar;
    
    @FXML
    private Button btnListAntrian;
    
    @FXML
    private Button btnRiwayatKunjungan;
    
    @FXML
    private Button btnBeranda;
    
    @FXML
    private MenuItem btnLogOut;

    @FXML
    private MenuItem btnProfile;

    @FXML
    private void login(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent menuRoot = loader.load();
        
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        
        Scene scene = new Scene(menuRoot);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void signup (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent signupRoot = loader.load();
        
        Stage stage = (Stage) btnSignup.getScene().getWindow();
        
        Scene scene = new Scene(signupRoot);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void login2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent menuRoot = loader.load();
        
        Stage stage = (Stage) btnLogin2.getScene().getWindow();
        
        Scene scene = new Scene(menuRoot);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void signup2 (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent signupRoot = loader.load();
        
        Stage stage = (Stage) btnSignup2.getScene().getWindow();
        
        Scene scene = new Scene(signupRoot);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void daftarAntrian(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("daftarAntrian.fxml"));
        Parent daftarRoot = loader.load();
        
        Stage stage = (Stage) btnDaftar.getScene().getWindow();
        
        Scene scene = new Scene(daftarRoot);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void listAntrian(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAntrian.fxml"));
        Parent listRoot = loader.load();
        
        Stage stage = (Stage) btnListAntrian.getScene().getWindow();
        
        Scene scene = new Scene(listRoot);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void riwayatKunjungan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("riwayatKunjungan.fxml"));
        Parent riwayatRoot = loader.load();
        
        Stage stage = (Stage) btnRiwayatKunjungan.getScene().getWindow();
        
        Scene scene = new Scene(riwayatRoot);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void beranda(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent riwayatRoot = loader.load();
        
        Stage stage = (Stage) btnBeranda.getScene().getWindow();
        
        Scene scene = new Scene(riwayatRoot);
        stage.setScene(scene);
        stage.show();
    }
    
    //Menu Item "Profile" dan "Log Out"
    @FXML
    void profile(ActionEvent event) throws IOException {
         // Menyiapkan stage (tidak dengan getScene())
        Stage stage = (Stage) btnProfile.getParentPopup().getOwnerWindow();

        // Untuk memuat file FXML baru
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings_Profile.fxml"));
        Parent root = loader.load();

        // Untuk mengganti scene pada stage
        stage.setScene(new Scene(root));
        stage.show();

    }
    
    @FXML
    void logout(ActionEvent event) throws IOException {
 
        Stage stage = (Stage) btnLogOut.getParentPopup().getOwnerWindow();

        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();

        
        stage.setScene(new Scene(root));
        stage.show();

    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
