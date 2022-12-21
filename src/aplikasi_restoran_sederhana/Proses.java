/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_restoran_sederhana;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author MINHAJUL ABIDIN
 */
public class Proses {
    
    static final String url="jdbc:mysql://localhost:3306/db_restoran";
    static final String user = "root";
    static final String pass = "";
    static Connection con;
    
    public static Connection koneksiDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
        try {
            con = (Connection) DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
            System.out.println("koneksi gagal");
        }
        return con;
    }
}
