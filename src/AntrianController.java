import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
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

public class AntrianController implements Initializable {

    @FXML
    private Button btnBeranda;

    @FXML
    private MenuItem btnLogOut;

    @FXML
    private MenuItem btnProfile;

    @FXML
    private TableColumn<Antrian, String> colId;
    
    @FXML
    private TableColumn<Antrian, Integer> colNomor;

    @FXML
    private TableView<Antrian> tvListAntrian;
    
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
         // Menyiapkan stage (tidak dengan getScene())
        Stage stage = (Stage) btnProfile.getParentPopup().getOwnerWindow();

        // Untuk memuat file FXML baru
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings_Profile.fxml"));
        Parent root = loader.load();

        // Untuk mengganti scene pada stage
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    private ObservableList<Antrian> antrianList = FXCollections.observableArrayList();
    
    public ObservableList<Antrian> getAntrianList() {
        Connection conn = getConnection();
        String query = "SELECT * FROM list_antrian ORDER BY nomor_antrian ASC";
        Statement st;
        ResultSet rs;
        
        try {
            if (conn == null) {
                System.out.println("Koneksi database tidak tersedia.");
                return antrianList;
            }
//            antrianList.clear();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                antrianList.add(new Antrian(rs.getString("id_pasien"), rs.getInt("nomor_antrian")));
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return antrianList;
    }
    
    @FXML
    public void initialize (URL url, ResourceBundle rb) {
        colNomor.setCellValueFactory(new PropertyValueFactory<>("nomor"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        showAntrian();
    }
    
    void showAntrian() {
        ObservableList<Antrian> list = getAntrianList();        
        tvListAntrian.setItems(list);
        
    }    

}
