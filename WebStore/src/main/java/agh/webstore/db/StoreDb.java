package agh.webstore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
//        utworzPrzykladoweDane("podluzny");
//        utworzPrzykladoweDane("kwadratowy");
    }

    public boolean stworzTabele() {
        try {
            String stworzKlocki = "CREATE TABLE IF NOT EXISTS klocki (id_klocka INTEGER PRIMARY KEY AUTOINCREMENT, rodzaj varchar(255), ilosc INTEGER)";
            stat.execute(stworzKlocki);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean utworzPrzykladoweDane(String rodzaj) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into klocki values (NULL, ?,?);");
            prepStmt.setString(1, rodzaj);
            prepStmt.setInt(2, 50);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu przykladowych danych");
            return false;
        }
        return true;
    }

    public boolean zmienIloscWMagazynie(String rodzaj, int ilosc) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "update klocki set ilosc = ? where rodzaj =?");
            prepStmt.setInt(1, ilosc);
            prepStmt.setString(2, rodzaj);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy zmianie ilosci przykladowych danych");
            return false;
        }
        return true;
    }

    public int pobierzIloscKlockow(String rodzaj) {
        String stmt = "SELECT ilosc from klocki where rodzaj = ? ";
        int ilosc = -1;
        try {
            PreparedStatement st = conn.prepareStatement(stmt);
            st.setString(1, rodzaj);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ilosc = rs.getInt("ilosc");
            }
        } catch (SQLException ex) {
            System.err.println("Blad przy zmianie ilosci przykladowych danych");
        }
        return ilosc;
    }

    public boolean zmniejszO1LiczbeKlockow(String rodzaj) {
        if (pobierzIloscKlockow(rodzaj) > 9) {
            zmienIloscWMagazynie(rodzaj, pobierzIloscKlockow(rodzaj) - 10);
            return true;
        }
        return false;
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
