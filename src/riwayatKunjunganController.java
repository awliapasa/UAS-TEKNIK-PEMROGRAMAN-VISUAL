
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class riwayatKunjunganController implements Initializable {

    @FXML
    private Button btnBeranda;

    @FXML
    private MenuItem btnLogOut;

    @FXML
    private MenuItem btnProfile;
    
    @FXML
    private TableColumn<Kunjungan, Date> colDate;
    @FXML
    private TableColumn<Kunjungan, String> colDiagnosa;

    @FXML
    private TableColumn<Kunjungan, String> colTindakan;

    @FXML
    private TableView<Kunjungan> tvKunjungan;

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
    
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klinik", "root", "M1celH1u!!");
            return conn;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    private ObservableList<Kunjungan> kunjunganList = FXCollections.observableArrayList();
    
    public ObservableList<Kunjungan> getKunjunganList() {
        Connection conn = getConnection();
        Pasien currUser = Pasien.getCurrUser();
        if(currUser == null) {
            System.out.println("Pengguna belum tercatat");
            return kunjunganList;
        }
        
        String query = "SELECT tanggal, tindakan, diagnosa FROM riwayat_kunjungan WHERE id_pasien = ? ORDER BY tanggal DESC;";
        try {
            if(conn == null) {
                System.out.println("Koneksi database tidak tersedia");
                return kunjunganList;
            }
            kunjunganList.clear();
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, currUser.getId());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                kunjunganList.add(new Kunjungan(rs.getDate("tanggal"), rs.getString("tindakan"), rs.getString("diagnosa")));
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return kunjunganList;
    }
    
    @FXML
    public void initialize (URL url, ResourceBundle rb) {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTindakan.setCellValueFactory(new PropertyValueFactory<>("tindakan"));
        colDiagnosa.setCellValueFactory(new PropertyValueFactory<>("diagnosa"));
        showKunjungan();
    }
    
    void showKunjungan() {
        ObservableList<Kunjungan> list = getKunjunganList();
        tvKunjungan.setItems(list);
    }

}
