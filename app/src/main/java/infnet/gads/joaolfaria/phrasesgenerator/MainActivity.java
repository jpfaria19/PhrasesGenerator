package infnet.gads.joaolfaria.phrasesgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView contentMessage;

    String[] array = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.generateMessage);
        contentMessage = (TextView) findViewById(R.id.mensagem);

    }

    public void onGenerate(View v) {
        array[0] = getString(R.string.albertEinstein_0);
        array[1] = getString(R.string.winstonChurchill_1);
        array[2] = getString(R.string.charlesChaplin_2);
        array[3] = getString(R.string.charliBrownJr_3);
        array[4] = getString(R.string.martinLutherKing_4);

        String randomStr = array[new Random().nextInt(array.length)];

        //StringBuilder messageBuilder = new StringBuilder();

        /*for (String str : array) {
            messageBuilder.append("\n").append(str);
        }*/

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
