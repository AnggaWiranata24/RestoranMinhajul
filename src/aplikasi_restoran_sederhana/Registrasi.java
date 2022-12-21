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
public class Registrasi {
    // registrasi
    PreparedStatement ps;
    ResultSet rs;
    DefaultTableModel model;
    Proses p = new Proses();
    public void insertUD(char operation, int id, String nama, String user,
                            String pass, int idLevel){
        Connection con = p.koneksiDb();
        PreparedStatement ps;
        if(operation == 'i'){
            try {
                ps = con.prepareStatement("INSERT INTO user(id_user,username, password, nama_user, id_level) VALUES (?,?,?,?,?)");
                ps.setInt(1, 0);
                ps.setString(2, nama);
                ps.setString(3, user);
                ps.setString(4, pass);
                ps.setInt(5, idLevel);
                if(ps.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null, "New Account added");
                }
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Insert Gagal");
            }
        }
        else if(operation == 'u'){
            try {
                ps = con.prepareStatement("UPDATE `user` SET `nama_user`=?, `username`=?,`password`=?,`id_level`=? WHERE `id_user` = ?");
                ps.setString(1, nama);
                ps.setString(2, user);
                ps.setString(3, pass);
                ps.setInt(4, idLevel);
                ps.setInt(5, id);
                if(ps.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null, "Data User Updated");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Update gagall boss");
            }
        }
        else if(operation == 'd'){
            try {
                ps = con.prepareStatement("DELETE FROM `user` WHERE `id_user` = ?");
                ps.setInt(1, id);
                if(ps.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null, "Data User Deleted");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
}
    public void refrehTableUser(JTable table){
        model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nama");
        model.addColumn("UserName");
        model.addColumn("Password");
        model.addColumn("ID Level");
        table.setModel(model);
        try {
            ps = p.koneksiDb().prepareStatement("select * from user");
            rs = ps.executeQuery();
            while(rs.next()){
               Object [] row = new Object[5];
                 row[0] = rs.getInt(1);
                 row[1] = rs.getString(2);
                 row[2] = rs.getString(3);
                 row[3] = rs.getString(4);
                 row[4] = rs.getInt(5);
                 model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
