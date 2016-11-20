package net.afirmo.afirmo.connect;

import net.afirmo.afirmo.registrarUser;

/**
 * Created by vic2_ on 19/11/2016.
 */
public interface LoginInterface {
    public void signIn(String email, String password);
    public void registrar(String email, String password,registrarUser Contexto);

}

