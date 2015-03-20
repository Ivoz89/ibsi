package agh.webstore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 *
 * @author ag
 */
public class StoreDb {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:store.db";
    
    private Connection conn;
    private Statement stat;
    
    public StoreDb() {
        try {
            Class.forName(StoreDb.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
 
        stworzTabele();
    }
    
    public boolean stworzTabele()  {
        try {
            String stworzKlocki = "CREATE TABLE IF NOT EXISTS klocki (id_klocka INTEGER PRIMARY KEY AUTOINCREMENT, rodzaj varchar(255))";
            stat.execute(stworzKlocki);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean utworzPrzykladoweDane(){
        String rodzaj1 = "podluzny";
        String rodzaj2 = "kwadratowy";
        Random random;
        boolean b;
        try {
            for(int i = 0 ; i < 50; i++){
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into klocki values (NULL, ?);");
            random = new Random();
            b = random.nextBoolean();
            if(b)
                prepStmt.setString(1, rodzaj1);
            else
                prepStmt.setString(1, rodzaj2);
            prepStmt.execute();
            }
        } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu przykladowych danych");
            return false;
        }
        return true;
    }
    
     public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }
}
