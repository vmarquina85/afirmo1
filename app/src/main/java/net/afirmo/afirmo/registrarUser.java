package net.afirmo.afirmo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.afirmo.afirmo.connect.firebaseAdapter;


public class registrarUser extends AppCompatActivity {

    Button registrar;
    EditText email;
    EditText password;
    EditText password2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        registrar = (Button) findViewById(R.id.btn_registrar);
        email =(EditText)  findViewById(R.id.editEmail);
        password = (EditText)  findViewById(R.id.editPassword);
//        password2 = (EditText)  findViewById(R.id.editPassword2);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail=email.getText().toString().trim();
                String strPassword = password.getText().toString().trim();

                firebaseAdapter adapter = new firebaseAdapter();
                adapter.registrar(strEmail,strPassword,registrarUser.this);

            }
        });
    }



}
