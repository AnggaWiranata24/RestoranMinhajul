/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi_restoran_sederhana;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MINHAJUL ABIDIN
 */
public class masakan {
    
    PreparedStatement ps;
    ResultSet rs;
    DefaultTableModel model;
    public void insertUD(char operation, int id, String nama, int harga,
                            String status){
        Connection con = Proses.koneksiDb();
        switch (operation) {
            case 'i':
                try {
                    ps = con.prepareStatement("INSERT INTO masakan(id_masakan, nama_masakan, harga, status) VALUES (?,?,?,?)");
                    ps.setInt(1, 0);
                    ps.setString(2, nama);
                    ps.setInt(3, harga);
                    ps.setString(4, status);
                    if(ps.executeUpdate() > 0){
                        JOptionPane.showMessageDialog(null, "New Food added");
                    }
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "Insert Gagal");
                }   break;
            case 'u':
                try {
                    ps = con.prepareStatement("UPDATE `masakan` SET `nama_masakan`=?,`harga`=?,`status`=? WHERE `id_masakan` = ?");
                    ps.setString(1, nama);
                    ps.setInt(2, harga);
                    ps.setString(3, status);
                    ps.setInt(4, id);
                    if(ps.executeUpdate() > 0){
                        JOptionPane.showMessageDialog(null, "Data Food Updated");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }   break;
            case 'd':
                try {
                    ps = con.prepareStatement("DELETE FROM `masakan` WHERE `id_masakan` = ?");
                    ps.setInt(1, id);
                    if(ps.executeUpdate() > 0){
                        JOptionPane.showMessageDialog(null, "Data Food Deleted");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }   break;
            default:
                break;
        }
}
    public void refrehTableUser(JTable table){
        model = new DefaultTableModel();
        model.addColumn("Id Masakan");
        model.addColumn("Nama Masakan");
        model.addColumn("Harga");
        model.addColumn("Status");
        table.setModel(model);
        try {
            ps = Proses.koneksiDb().prepareStatement("select * from masakan");
            rs = ps.executeQuery();
            while(rs.next()){
               Object [] row = new Object[4];
                 row[0] = rs.getInt(1);
                 row[1] = rs.getString(2);
                 row[2] = rs.getInt(3);
                 row[3] = rs.getString(4);
                 model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Ambil Data");
        }
    }

}
