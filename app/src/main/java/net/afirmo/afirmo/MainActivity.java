package net.afirmo.afirmo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button btn_login;
    Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = (Button) findViewById(R.id.buttonUsuario);
        btn_registrar = (Button) findViewById(R.id.buttonRegistrarse);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn_login = new Intent(MainActivity.this, LoginAfirmo.class);

                startActivity(btn_login);
            }
        });
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn_registrar = new Intent(MainActivity.this, registrarUser.class);

                startActivity(btn_registrar);
            }
        });


    }
}
