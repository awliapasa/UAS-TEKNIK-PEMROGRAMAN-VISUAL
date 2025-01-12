/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Micel
 */
public class Antrian {
    private String id;
    private int nomor;
    
    public Antrian(String id, int nomor) {
        this.id = id;
        this.nomor = nomor;
    }
    
    public String getId() {
        return id;
    }
    
    public int getNomor() {
        return nomor;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setNomor(int nomor) {
        this.nomor = nomor;
    }
}
