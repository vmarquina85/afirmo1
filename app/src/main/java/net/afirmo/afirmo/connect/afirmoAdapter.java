package net.afirmo.afirmo.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vic2_ on 16/11/2016.
 */
public class afirmoAdapter implements connectInterface {
    Connection connect;
    @Override
    public void conectar(String BDnombre, String usuario, String pass) throws SQLException, ClassNotFoundException{
        String cadena = "jdbc:postgresql://192.168.1.4:5432/" + BDnombre;

        Class.forName("org.postgresql.Driver");
        connect = DriverManager.getConnection(cadena,usuario,pass);

    }

    @Override
    public String ejecutar(String sql)throws SQLException   {
        java.sql.Statement st =connect.createStatement();
        String consulta=sql;
        ResultSet result = st.executeQuery(consulta);
        String usuario= result.getString("usr_login");
        connect.close();
        return usuario;




    }
}