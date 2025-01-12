import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private Button btnLogin2;

    @FXML
    private Button btnSignup2;

    @FXML
    private TextField tfAlergi;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfKelamin;

    @FXML
    private TextField tfLahir;

    @FXML
    private TextField tfNama;

    @FXML
    private TextField tfSandi;
    
     @FXML
    private TextField tfAlamat;
     
     @FXML
    private TextField tfTelp;

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
        signUpRecord();
        
        Pasien newUser = new Pasien (
                null,
                tfNama.getText(),
                "",
                null,
                tfKelamin.getText(),
                java.sql.Date.valueOf(tfLahir.getText()),
                tfAlamat.getText(),
                tfTelp.getText(),
                tfAlergi.getText(),
                tfEmail.getText(),
                tfSandi.getText()
        );
        Pasien.setCurrUser(newUser);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent signupRoot = loader.load();
        
        Stage stage = (Stage) btnSignup2.getScene().getWindow();
        
        Scene scene = new Scene(signupRoot);
        stage.setScene(scene);
        stage.show();
    }
    
    public Connection getConnection() {
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klinik", "root", "M1celH1u!!");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    void signUpRecord() {
        String query = "INSERT INTO biodata_pasien (nama_pasien, jenis_kelamin, tanggal_lahir, alamat_pasien, nomor_telepon, riwayat_alergi, email, kata_sandi) values ('"
                            + tfNama.getText() + "','" + tfKelamin.getText() + "','" + tfLahir.getText() + "','" + tfAlamat.getText() 
                            + "','" + tfTelp.getText() + "','" + tfAlergi.getText() +"','" + tfEmail.getText() + "','" + tfSandi.getText() + "')";
        
        executeQuery(query);
    }
    
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
    }

}
