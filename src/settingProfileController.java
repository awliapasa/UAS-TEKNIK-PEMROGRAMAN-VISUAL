import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class settingProfileController implements Initializable {

    @FXML
    private Button btnBeranda;
    
    @FXML
    private Button btnEdit;

    @FXML
    private MenuItem btnLogOut;

    @FXML
    private MenuItem btnProfile;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblId;

    @FXML
    private Label lblKelamin;

    @FXML
    private Label lblLahir;

    @FXML
    private Label lblNama;

    @FXML
    private Label lblTelp;
    
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
        Stage stage = (Stage) btnLogOut.getParentPopup().getOwnerWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();
      
        stage.setScene(new Scene(root));
        stage.show();
    }

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
    void editProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editProfile.fxml"));
        Parent riwayatRoot = loader.load();
        
        Stage stage = (Stage) btnEdit.getScene().getWindow();
        
        Scene scene = new Scene(riwayatRoot);
        stage.setScene(scene);
        stage.show();
    }
    
    public void updateProfile(Pasien updatedUser) {
        if(updatedUser != null) {
            lblId.setText(String.valueOf(updatedUser.getId()));
            lblNama.setText(updatedUser.getNama());
            lblKelamin.setText(updatedUser.getJenisKelamin());
            lblLahir.setText(updatedUser.getTglLahir().toString());
            lblTelp.setText(updatedUser.getTelp());
            lblEmail.setText(updatedUser.getEmail());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pasien currUser = Pasien.getCurrUser();
        
        if(currUser != null) {
            lblId.setText(String.valueOf(currUser.getId()));
            lblNama.setText(currUser.getNama());
            lblKelamin.setText(currUser.getJenisKelamin());
            lblLahir.setText(currUser.getTglLahir().toString());
            lblTelp.setText(currUser.getTelp());
            lblEmail.setText(currUser.getEmail());
        } else {
            System.out.println("Tidak ada pengguna yang aktif");
        }
    }
}
