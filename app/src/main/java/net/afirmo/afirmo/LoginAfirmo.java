package net.afirmo.afirmo;

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

import net.afirmo.afirmo.ActivitiesJava.MainMenu;
import net.afirmo.afirmo.ActivitiesJava.registrarUser;





public class LoginAfirmo extends AppCompatActivity {
    EditText email,password;
    Button signIn;
    TextView register;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_afirmo);
        signIn = (Button) findViewById(R.id.buttonIngresar);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        register =(TextView) findViewById(R.id.tvRegistrar);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoguearUsuario();
            }

        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRegistrar = new Intent(LoginAfirmo.this, registrarUser.class);
                finish();
                startActivity(goRegistrar);
                setTitle("Regístrate en Afirmo");
            }
        });

    }

    private void LoguearUsuario() {
        String strEmail = email.getText().toString().trim();
        String strPassword = password.getText().toString().trim();
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
        progressDialog.setMessage("Iniciando sesión, por favor espere un momento...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Intent btn_login = new Intent(LoginAfirmo.this, MainMenu.class);
                    finish();
                    startActivity(btn_login);
                    setTitle("Menú Principal");

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginAfirmo.this, "No se pudo Iniciar la Sesión.. por favor Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}