
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Micel
 */
public class Kunjungan {

    private Date date;
    private String tindakan;
    private String diagnosa;
    
    public Kunjungan (Date date, String tindakan, String diagnosa) {
        this.date = date;
        this.tindakan = tindakan;
        this.diagnosa = diagnosa;    
    }
    
    public Date getDate() {
        return date;
    }
    
    public String getTindakan() {
        return tindakan;
    }
    
    public String getDiagnosa() {
        return diagnosa;
    }
}
