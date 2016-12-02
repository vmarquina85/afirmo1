package net.afirmo.afirmo.ActivitiesJava;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.afirmo.afirmo.LoginAfirmo;
import net.afirmo.afirmo.R;


public class MainActivity extends AppCompatActivity {
    TextView Login;
    Button btn_registrar;
    LinearLayout Mainlayout;
    private static final int DURACION=300;
    private static final int TIEMPO_DESPUES=300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = (TextView) findViewById(R.id.linkLogin);
        btn_registrar = (Button) findViewById(R.id.buttonRegistrarse);
        Mainlayout = (LinearLayout) findViewById(R.id.Llmain);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn_login = new Intent(MainActivity.this, LoginAfirmo.class);

                startActivity(btn_login);
                setTitle("Iniciar sesión en Afirmo");
            }
        });
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn_registrar = new Intent(MainActivity.this, registrarUser.class);

                startActivity(btn_registrar);
                setTitle("Regístrate en Afirmo");
            }
        });

        AlphaAnimation fadein = new AlphaAnimation(0.0f,1.0f );
        fadein.setDuration(DURACION);
        fadein.setStartOffset(TIEMPO_DESPUES);
        fadein.setFillAfter(true);

        Mainlayout.startAnimation(fadein);


    }
}
