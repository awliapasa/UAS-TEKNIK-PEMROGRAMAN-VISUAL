
import java.sql.Date;
import javafx.scene.control.DatePicker;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Micel
 */
public class Pasien {
    private static Pasien currUser;
    
    private String id;
    private String nama;
    private String keluhan;
    private Date date;
    private String jenis_kelamin;
    private Date tgl_lahir;
    private String alamat;
    private String telp;
    private String riwayat_alergi;
    private String email;
    private String kata_sandi;
    
    private Integer nomor;
    
    public Pasien(String id, String nama, String keluhan, Date date, String jenis_kelamin,
            Date tgl_lahir,String alamat, String telp, String riwayat_alergi, String email, String kata_sandi) {
        this.id = id;
        this.nama = nama;
        this.keluhan = keluhan;
        this.date = date;
        this.jenis_kelamin = jenis_kelamin;
        this.tgl_lahir = tgl_lahir;
        this.alamat = alamat;
        this.telp = telp;
        this.riwayat_alergi = riwayat_alergi;
        this.email = email;
        this.kata_sandi = kata_sandi;
    }
    
    public static Pasien getCurrUser() {
        return currUser;
    }
    
    public static void setCurrUser(Pasien user) {
        currUser = user;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId (String id) {
        this.id = id;
    }
    
    public String getNama() {
        return nama;
    }
    
    public String getKeluhan() {
        return keluhan;
    }
    
    public Date getDate() {
        return date;
    }
    
    public String getJenisKelamin() {
        return jenis_kelamin;
    }
    
    public Date getTglLahir(){
        return tgl_lahir;
    }
    
    public String getAlamat() {
        return alamat;
    }
    
    public String getTelp () {
        return telp;
    }
    
    public String getRiwayat_alergi() {
        return riwayat_alergi;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getKataSandi() {
        return kata_sandi;
    }
    
    public Integer getNomor() {
        return nomor;
    }
    
    public void setNomor(Integer nomor) {
        this.nomor = nomor;
    }
    
}
