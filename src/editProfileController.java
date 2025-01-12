import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editProfileController implements Initializable{

    @FXML
    private Button btnBeranda;

    @FXML
    private MenuItem btnLogOut;

    @FXML
    private MenuItem btnProfile;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblId;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfJenis;

    @FXML
    private TextField tfLahir;

    @FXML
    private TextField tfNama;

    @FXML
    private TextField tfTelp;
    
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klinik", "root", "M1celH1u!!");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
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

    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();
      
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnProfile.getParentPopup().getOwnerWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings_Profile.fxml"));
        Parent root = loader.load();

        
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void save(ActionEvent event) throws IOException {
        Pasien currUser = Pasien.getCurrUser();
        
        if(currUser != null) {
            String nama = tfNama.getText();
            String jenisKelamin = tfJenis.getText();
            String tglLahir = tfLahir.getText();
            String telp = tfTelp.getText();
            String email = tfEmail.getText();
            
            String updateQuery = "UPDATE biodata_pasien SET nama_pasien = ?, jenis_kelamin = ?, tanggal_lahir = ?, nomor_telepon = ?, email = ? WHERE id_pasien = ?";
            
            try(Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(updateQuery)) {
                    ps.setString(1, nama);
                    ps.setString(2, jenisKelamin);
                    ps.setString(3, tglLahir);
                    ps.setString(4, telp);
                    ps.setString(5, email);
                    ps.setString(6, String.valueOf(currUser.getId()));
                    
                    int rowsUpdated = ps.executeUpdate();
                    
                    if(rowsUpdated > 0) {
                        System.out.println("Data berhasil diperbaharui");
                        Pasien updatedUser = Pasien.getCurrUser();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("INFO");
                        alert.setHeaderText("Profile Berhasil Diperbaharui!");
                        alert.setContentText("Silahkan melakukan log in kembali");
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
                        Parent riwayatRoot = loader.load();
        
                        Stage stage = (Stage) btnSave.getScene().getWindow();
        
                        Scene scene = new Scene(riwayatRoot);
                        stage.setScene(scene);
                        stage.show();
                        alert.showAndWait();
                        
                        
                        
                        
                    }else {
                        System.out.println("Data gagal diperbaharui");
                    }
            } catch (SQLException ex) {
                System.out.println("Error updating data: " + ex.getMessage());
            }
        }
    }
    
    @FXML
    public void initialize (URL url, ResourceBundle rb) {
        Pasien currUser = Pasien.getCurrUser();
        
        if(currUser != null) {
            lblId.setText(currUser.getId().toString());
            tfNama.setText(currUser.getNama());
            tfJenis.setText(currUser.getJenisKelamin());
            tfLahir.setText(currUser.getTglLahir().toString());
            tfTelp.setText(currUser.getTelp());
            tfEmail.setText(currUser.getEmail());
        }else {
            System.out.println("Tidak ada pengguna yang aktif");
        }
    }

}
