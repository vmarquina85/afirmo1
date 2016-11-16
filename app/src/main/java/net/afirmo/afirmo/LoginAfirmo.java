package net.afirmo.afirmo;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.afirmo.afirmo.connect.afirmoAdapter;

import java.sql.SQLException;

public class LoginAfirmo extends AppCompatActivity {
    Button signIn;
    String tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_afirmo);
        signIn = (Button) findViewById(R.id.buttonIngresar);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent button = new Intent(LoginAfirmo.this, MainMenu.class);
                // startActivity(button);
                afirmoAdapter adapter = new afirmoAdapter();
                String Respuesta;


                try {
                    adapter.conectar("sicop", "postgres", "humanidad");
                    AlertDialog.Builder dialogOk = new AlertDialog.Builder(LoginAfirmo.this);
                    dialogOk.setTitle("Mensaje Afirmo");
                    Respuesta=adapter.ejecutar("Select usr_login from usuarios where usr_login='MARLON.FALLA'");
                    dialogOk.setMessage(Respuesta);
                    dialogOk.show();
                } catch (ClassNotFoundException | SQLException e) {

                    AlertDialog.Builder dialogError = new AlertDialog.Builder(LoginAfirmo.this);
                    dialogError.setTitle("Error Afirmo");
                    dialogError.setMessage(e.getMessage());
                    dialogError.show();
                }

            }

        });
    }
}