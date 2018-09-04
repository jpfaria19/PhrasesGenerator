package infnet.gads.joaolfaria.phrasesgenerator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import infnet.gads.joaolfaria.phrasesgenerator.DAO.ConfiguracaoFirebase;
import infnet.gads.joaolfaria.phrasesgenerator.R;
import infnet.gads.joaolfaria.phrasesgenerator.domain.User;

public class MainActivity extends AppCompatActivity {

    EditText edtLogin, edtLoginPassword;
    AdView adView;
    FirebaseAuth auth;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Login");

        edtLogin = findViewById(R.id.edtLogin);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        MobileAds.initialize(this, "ca-app-pub-6659624410187836~2074423175");

        adView = findViewById(R.id.adViewLogin);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);

        auth = ConfiguracaoFirebase.getFirebaseAuth();
    }

    public void submitLogin(View view) {

        String email = "jp@test.com";
        String password = "1Pdu!m";

        //TODO: LOGICA PARA LOGAR O USUARIO E DIRECIONAR PARA A PÁGINA DE FRASES


        if (userLogado()) {

            Intent toPhrases = new Intent(this, PhrasesGeneratorActivity.class);
            startActivity(toPhrases);

        } else {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Logado com sucesso", Toast.LENGTH_LONG).show();
                                Intent toPhrases = new Intent(MainActivity.this, PhrasesGeneratorActivity.class);
                                startActivity(toPhrases);
                            } else {
                                Toast.makeText(MainActivity.this, "Falha no login", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private Boolean userLogado() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            return true;
        } else {
            return false;
        }
    }

    public void registerNewUser(View view) {
        Intent toRegister = new Intent(this, RegisterActivity.class);
        startActivity(toRegister);
    }
}
