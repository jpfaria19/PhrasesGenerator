package infnet.gads.joaolfaria.phrasesgenerator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

import infnet.gads.joaolfaria.phrasesgenerator.R;

public class PhrasesGeneratorActivity extends AppCompatActivity {

    private TextView contentMessage;

    String[] array = new String[5];

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases_generator);

        getSupportActionBar().setTitle("Frases para você");

        contentMessage = findViewById(R.id.mensagem);

        MobileAds.initialize(this, "ca-app-pub-6659624410187836~2074423175");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);
    }

    public void onGenerate(View v) {
        array[0] = getString(R.string.albertEinstein_0);
        array[1] = getString(R.string.winstonChurchill_1);
        array[2] = getString(R.string.charlesChaplin_2);
        array[3] = getString(R.string.charliBrownJr_3);
        array[4] = getString(R.string.martinLutherKing_4);

        String randomStr = array[new Random().nextInt(array.length)];

        contentMessage.setText(randomStr);
    }

    public void viewAllContacts(View view) {
        Intent toList = new Intent(this, ListActivity.class);
        startActivity(toList);
    }


    // ***** ALERTA NA TELA *****

        /*AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Nome do Autor");
        alertDialog.setMessage("Mensagem do Autor");
        alertDialog.show();
        */

    // **************************
}
