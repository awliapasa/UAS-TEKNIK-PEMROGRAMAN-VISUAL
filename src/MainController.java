/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainController  {
    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignup;
    
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
    private TextField tfEmail;
    
    @FXML 
    private TextField tfPass;
    
    
    @FXML
    private void login(ActionEvent event) throws IOException {
        String email = tfEmail.getText();
        String password = tfPass.getText();
        
        if(email.isEmpty() || password.isEmpty()) {
            showAlert("Error","Mohon isi email dan password");
            return;
        }
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klinik", "root", "M1celH1u!!");
            
            String query = "SELECT * FROM biodata_pasien WHERE email = ? and kata_sandi = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next()) {
                Pasien user = new Pasien(
                    rs.getString("id_pasien"),
                    rs.getString("nama_pasien"),
                    null,
                    null,
                    rs.getString("jenis_kelamin"),
                    rs.getDate("tanggal_lahir"),
                    rs.getString("alamat_pasien"),
                    rs.getString("nomor_telepon"),
                    rs.getString("riwayat_alergi"),
                    rs.getString("email"),
                    rs.getString("kata_sandi")
                );
                Pasien.setCurrUser(user);
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent menuRoot = loader.load();
        
                Stage stage = (Stage) btnLogin.getScene().getWindow();
        
                Scene scene = new Scene(menuRoot);
                stage.setScene(scene);
                stage.show();
            } else {
                showAlert("Login gagal", "Email atau password salah.");
            }
            
            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error","Terjadi kesalahan: " + e.getMessage());
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
 
}     

 

