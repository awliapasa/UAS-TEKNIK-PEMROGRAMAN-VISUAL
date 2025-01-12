import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.IOException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DaftarController {

    @FXML
    private Button btnBeranda;

    @FXML
    private MenuItem btnLogOut;

    @FXML
    private MenuItem btnProfile;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField tfDate;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfKeluhan;

    @FXML
    private TextField tfNama;
    
    @FXML
    private TableColumn<Pasien, String> colId;
    
    @FXML
    private TableColumn<Pasien, Integer> colNomor;

    @FXML
    private TableView<Pasien> tvListAntrian;
    
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
        Stage stage = (Stage) btnLogOut.getParentPopup().getOwnerWindow();

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
    private void submitDaftar(ActionEvent event) throws IOException {
        if (event.getSource() == btnSubmit) 
            daftarRecord();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pendaftaran Berhasil!");
        alert.setHeaderText(null);
        alert.setContentText("Terima kasih telah mendaftar, silahkan kembali ke beranda dan melihat giliran Anda pada menu List Antrian!");
        alert.showAndWait();
        
        tfId.clear();
        tfNama.clear();
        tfKeluhan.clear();
        tfDate.clear();
    }
    
    void daftarRecord() {
        String query = "INSERT INTO daftar_antrian (tanggal, id_pasien, nama_pasien, keluhan) VALUES('" + tfDate.getText() + "','" + tfId.getText() + "','" + tfNama.getText()
+ "','" + tfKeluhan.getText() + "')";
        executeQuery(query);
    }
    
    void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
}
    
