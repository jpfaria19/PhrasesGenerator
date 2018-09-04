package infnet.gads.joaolfaria.phrasesgenerator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import infnet.gads.joaolfaria.phrasesgenerator.R;

public class MainActivity extends AppCompatActivity {

    EditText edtLogin, edtLoginPassword;

    AdView adView;

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

    }

    public void submitLogin(View view){
        Intent toPhrases = new Intent(this, PhrasesGeneratorActivity.class);
        startActivity(toPhrases);
        //TODO: LOGICA PARA LOGAR O USUARIO E DIRECIONAR PARA A PÁGINA DE FRASES
    }

    public void registerNewUser(View view){
        Intent toRegister = new Intent(this, RegisterActivity.class);
        startActivity(toRegister);
    }
}
