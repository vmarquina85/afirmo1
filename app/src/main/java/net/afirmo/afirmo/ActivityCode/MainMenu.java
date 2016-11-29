package net.afirmo.afirmo.ActivityCode;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.afirmo.afirmo.LoginAfirmo;
import net.afirmo.afirmo.R;

import java.io.File;

public class MainMenu extends AppCompatActivity {
    Button btn_registrarFirma,valorizar,historial,logout,verificar;
    static final int CAM_REQUEST = 1;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    TextView textViewUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btn_registrarFirma = (Button) findViewById(R.id.btn_firma);
        logout = (Button) findViewById(R.id.btnLogout);
        verificar = (Button) findViewById(R.id.btnVerirficar);
        valorizar = (Button) findViewById(R.id.btnValorizar);
        historial = (Button) findViewById(R.id.btnHistorial);



        textViewUser= (TextView) findViewById(R.id.textViewUserName);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginAfirmo.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUser.setText("Bienvenido " + user.getEmail());

        btn_registrarFirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();

                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);

            }
        });
        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenu.this, "Historia Pendiente", Toast.LENGTH_SHORT).show();
            }
        });
        valorizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenu.this, "Historia Pendiente", Toast.LENGTH_SHORT).show();
            }
        });
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenu.this, "Historia Pendiente", Toast.LENGTH_SHORT).show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(MainMenu.this,LoginAfirmo.class));
            }
        });
    }

    private File getFile() {
        File folder = new File("sdcard/afirmo_app");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File image_file = new File(folder, "cam_image.jpg");
        return image_file;

    }


}
