package net.afirmo.afirmo.connect;

import java.sql.SQLException;

/**
 * Created by vic2_ on 16/11/2016.
 */
public interface connectInterface {
    public void conectar(String BDnombre,String usuario, String pass)throws SQLException,ClassNotFoundException;
    public String ejecutar(String sql)throws SQLException ;
}
