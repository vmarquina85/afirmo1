package net.afirmo.afirmo.ActivitiesJava;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import net.afirmo.afirmo.LoginAfirmo;
import net.afirmo.afirmo.R;


public class registrarUser extends AppCompatActivity {

    private Button registrar;
    private EditText email, password, nombre, empresa;
    private TextView iniciarSesion;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        registrar = (Button) findViewById(R.id.btn_registrar);
        email = (EditText) findViewById(R.id.editEmail);
        password = (EditText) findViewById(R.id.editPassword);
        nombre = (EditText) findViewById(R.id.etNombre);
        empresa = (EditText) findViewById(R.id.etEmpresa);
        iniciarSesion= (TextView) findViewById(R.id.tvIniciarSesion);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registrarUsuario();

            }
        });
        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent btn_login = new Intent(registrarUser.this, LoginAfirmo.class);
                finish();
                startActivity(btn_login);

            }
        });

    }

    public void registrarUsuario() {
        String strEmail = email.getText().toString().trim();
        String strPassword = password.getText().toString().trim();
        String strNombre = nombre.getText().toString().trim();
        String strEmpresa = empresa.getText().toString().trim();
        if (TextUtils.isEmpty(strNombre)) {
            Toast.makeText(this, "Por favor ingresar su Nombre", Toast.LENGTH_SHORT).show();
            nombre.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(strEmail)) {
            Toast.makeText(this, "Por favor ingresar Email", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(strPassword)) {
            Toast.makeText(this, "Por favor ingresar Password", Toast.LENGTH_SHORT).show();
            password.requestFocus();
            return;
        }


        progressDialog.setMessage("Registrando User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(registrarUser.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                    Intent btn_login = new Intent(registrarUser.this, LoginAfirmo.class);
                    finish();
                    startActivity(btn_login);

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(registrarUser.this, "Usuario no pudo Registrarse.. por favor Intentelo de nuevo", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}



