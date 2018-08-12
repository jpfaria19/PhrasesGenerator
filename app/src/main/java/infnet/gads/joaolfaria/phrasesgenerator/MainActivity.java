package infnet.gads.joaolfaria.phrasesgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView contentMessage;

    String[] array = new String[5];

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.generateMessage);
        contentMessage = findViewById(R.id.mensagem);

        MobileAds.initialize(this, "ca-app-pub-6659624410187836~2074423175");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("865C96CE1AB84F7CD2C4450687F6AAA3").build();
        mAdView.loadAd(adRequest);

        if (adRequest.isTestDevice(this)){
            Toast.makeText(this, "TEST ADMOB", Toast.LENGTH_LONG).show();
        }

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



    // ***** ALERTA NA TELA *****

        /*AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Nome do Autor");
        alertDialog.setMessage("Mensagem do Autor");
        alertDialog.show();
        */

    // **************************
}
