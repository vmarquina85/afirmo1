package net.afirmo.afirmo;

import android.app.AlertDialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.afirmo.afirmo.connect.postgresqlAdapter;

import java.sql.SQLException;

public class LoginAfirmo extends AppCompatActivity {
    Button signIn;
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
                postgresqlAdapter adapter = new postgresqlAdapter();
                String Respuesta;

/*
                try {
                    adapter.conectar("sicop", "webserver", "12345");
                    AlertDialog.Builder dialogOk = new AlertDialog.Builder(LoginAfirmo.this);
                    dialogOk.setTitle("Mensaje Afirmo");
                    Respuesta=adapter.ejecutar("Select usr_login from usuarios where usr_login='MARLON.FALLA'");
                    dialogOk.setMessage(Respuesta);
                    dialogOk.show();
                } catch (ClassNotFoundException e) {

                    AlertDialog.Builder dialogError = new AlertDialog.Builder(LoginAfirmo.this);
                    dialogError.setTitle("CE Afirmo");
                    dialogError.setMessage(e.getMessage());
                    dialogError.show();
                } catch (SQLException e) {

                    AlertDialog.Builder dialogError = new AlertDialog.Builder(LoginAfirmo.this);
                    dialogError.setTitle("SQLE Afirmo");
                    dialogError.setMessage(e.getMessage());
                    dialogError.show();
                }
*/
            }

        });
    }

}