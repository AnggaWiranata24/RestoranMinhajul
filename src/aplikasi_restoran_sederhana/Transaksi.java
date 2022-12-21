
package aplikasi_restoran_sederhana;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Transaksi {
    PreparedStatement ps;
    ResultSet rs;
    DefaultTableModel model;
    public void insertUD(char operation, int id, String namaP, int idM, String tgl,
                            String namaM, int harga, int jumlahB, int total){
        Connection con = Proses.koneksiDb();
        switch (operation) {
            case 'i':
                try {
                    ps = con.prepareStatement("INSERT INTO transaksi"
                            + "(id_transaksi, nama_pelanggan, id_masakan, tanggal, nama_masakan, harga, jumlah_beli, total_bayar"+") VALUES (?,?,?,?,?,?,?,?)");
                    ps.setInt(1, 0);
                    ps.setString(2, namaP);
                    ps.setInt(3, idM);
                    ps.setString(4, tgl);
                    ps.setString(5, namaM);
                    ps.setInt(6, harga);
                    ps.setInt(7, jumlahB);
                    ps.setInt(8, total);
                    if(ps.executeUpdate() > 0){
                        JOptionPane.showMessageDialog(null, "New Transaksi added");
                    }
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "Insert Gagal");
                }   break;
            case 'u':
                try {
                    ps = con.prepareStatement("UPDATE `transaksi` SET `nama_pelanggan`=?,`id_masakan`=?,"
                            + "`tanggal`=?,`nama_masakan`=?,`harga`=?,`jumlah_beli`=?,`total_bayar`=? WHERE `id_transaksi` = ?");
                    ps.setString(1, namaP);
                    ps.setInt(2, idM);
                    ps.setString(3, tgl);
                    ps.setString(4, namaM);
                    ps.setInt(5, harga);
                    ps.setInt(6, jumlahB);
                    ps.setInt(7, total);
                    ps.setInt(8, id);
                    if(ps.executeUpdate() > 0){
                        JOptionPane.showMessageDialog(null, "Data Transaksi Updated");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }   break;
            case 'd':
                try {
                    ps = con.prepareStatement("DELETE FROM `transaksi` WHERE `id_transaksi` = ?");
                    ps.setInt(1, id);
                    if(ps.executeUpdate() > 0){
                        JOptionPane.showMessageDialog(null, "Data Transaksi Deleted");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }   break;
            default:
                break;
        }
}
    public void refrehTableTransaksi(JTable table){
        model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Nama Pelanggan");
        model.addColumn("ID Masakan");
        model.addColumn("Tanggal");
        model.addColumn("Nama Masakan");
        model.addColumn("Harga");
        model.addColumn("Jumlah Beli");
        model.addColumn("Total");
        table.setModel(model);
        try {
            ps = Proses.koneksiDb().prepareStatement("select * from transaksi");
            rs = ps.executeQuery();
            while(rs.next()){
               Object [] row = new Object[8];
                 row[0] = rs.getInt(1);
                 row[1] = rs.getString(2);
                 row[2] = rs.getInt(3);
                 row[3] = rs.getDate(4);
                 row[4] = rs.getString(5);
                 row[5] = rs.getInt(6);
                 row[6] = rs.getInt(7);
                 row[7] = rs.getInt(8);
                 model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Ambil Data");
        }
    }

  
}
