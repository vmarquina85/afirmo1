package net.afirmo.afirmo.connect;

        import android.app.ProgressDialog;
        import android.support.annotation.NonNull;
        import android.text.TextUtils;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

        import net.afirmo.afirmo.registrarUser;


/**
 * Created by vic2_ on 19/11/2016.
 */
public class firebaseAdapter implements LoginInterface {
    @Override
    public void signIn(String email, String password) {

    }

    @Override
    public void registrar(String email, String password,  registrarUser contexto) {
         registrarUser activity = contexto;
        ProgressDialog progressDialog = new ProgressDialog(contexto);
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(contexto, "Por favor ingresar Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(contexto, "Por favor ingresar Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registrando Usuario...");
        progressDialog.show();

    }
}
