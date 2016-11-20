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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginAfirmo extends AppCompatActivity {
    EditText email;
    EditText password;
    Button signIn;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_afirmo);
        signIn = (Button) findViewById(R.id.buttonIngresar);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoguearUsuario();
            }

        });
    }

    private void LoguearUsuario() {
        String strEmail = email.getText().toString().trim();
        String strPassword = password.getText().toString().trim();
        if (TextUtils.isEmpty(strEmail)) {
            Toast.makeText(this, "Por favor ingresar Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strPassword)) {
            Toast.makeText(this, "Por favor ingresar Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registrando Usuario...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Intent btn_login = new Intent(LoginAfirmo.this, MainMenu.class);
                    finish();
                    startActivity(btn_login);

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginAfirmo.this, "No se pudo Iniciar la Sesión.. por favor Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}