/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Andri
 */
public class Koneksi {

    /**
     * @param args the command line arguments
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";

    //user pass db
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try {
            //            mendaftarkan class JDBC Driver
            Class.forName(JDBC_DRIVER);

//            melakukan koneksi database
            System.out.println("Menghubungkan ke MYSQL");
            connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            System.out.println("Berhasil berhubung ke MySQL\n");
//          mengecek, apakah database sudah dibuat atau belum
            boolean ketemu = false;
            ResultSet result = connection.getMetaData().getCatalogs();
            while (result.next()) {
                String databaseName = result.getString(1);
                if (databaseName.equals("db_tokobuku")) {
                    ketemu = true;
                    System.err.print("Database "+ databaseName+" Sudah ada");
                }
            }

            if (ketemu == false) {
//            membuat db_tokobuku
                System.out.println("Membuat Database db_tokobuku");
                sql = "CREATE DATABASE db_tokobuku";
                statement.executeUpdate(sql);
                System.out.println("Berhasil membuat db_tokobuku");

//            menggunakan database db_tokobuku
                System.out.println("Menggunakan db_tokobuku");
                sql = "USE db_tokobuku";
                statement.execute(sql);
                System.out.println("Berhasil mengakses db_tokobuku");

//            membuat tabel
            System.out.println("Membuat tbl_barang..");
            sql = "CREATE TABLE tbl_barang (kode_barang CHAR(5) NOT NULL, "
                    + "nama_barang VARCHAR (50), satuan_barang VARCHAR (8), "
                    + "harga_barang INTEGER, PRIMARY KEY (kode_barang))";
            statement.executeUpdate(sql);
            System.out.println("Berhasil membuat tbl_barang..\n");
//            memasukkan data kedalam tabel
//            System.out.println("Memasukkan data ke tbl_barang");
//            sql = "INSERT INTO tbl_barang VALUES('PS-01', 'Penghapus Staedler ', 'Pcs', '1500')";
//            statement.executeUpdate(sql);
//            System.out.println("Berhasil Memasukkan data ke tbl_barang\n");
//            menghapus data
//            System.out.println("delete kode barang ps-01");
//            sql = "DELETE FROM tbl_barang WHERE tbl_barang.kode_barang = 'PS-01'";
//            statement.executeUpdate(sql);
//            System.out.println("Berhasil delete");
//          membuat tabel
//            System.out.println("Membuat tbl_supplier..");
//            sql = "CREATE TABLE tbl_supplier (kode_supplier CHAR(5) PRIMARY KEY, nama_supplier VARCHAR(50),no_telp CHAR(15), alamat TEXT)";
//            statement.executeUpdate(sql);
//            System.out.println("Berhasil membuat tbl_supplier..\n");
//
//            System.out.println("Membuat kolom kode_supplier pada tbl_barang");
//            sql = "ALTER TABLE tbl_barang ADD kode_supplier CHAR(5) AFTER kode_barang";
//            statement.execute(sql);
//            System.err.println("berhasil membuat kolom kode_supplier pada tbl_barang\n");
//            
//            System.out.println("Buat foreign key (index) pada kolom kode_supplier di tbl_barang");
//            sql = "ALTER TABLE tbl_barang ADD INDEX(kode_supplier)";
//            statement.execute(sql);
//            System.err.println("Berhasil Buat foreign key (index) pada kolom kode_supplier di tbl_barang\n");
//            
//            System.out.print("hubungkan kode suplier pada kedua tabel");
//            sql = "ALTER TABLE tbl_barang ADD FOREIGN KEY (kode_supplier) REFERENCES tbl_supplier(kode_supplier) ON DELETE CASCADE ON UPDATE CASCADE";
//            statement.execute(sql);
//            System.err.print("berhasil hubungkan kode suplier pada kedua tabel");
            }
        } catch (Exception e) {

        }
    }

}
