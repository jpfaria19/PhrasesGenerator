package infnet.gads.joaolfaria.phrasesgenerator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import infnet.gads.joaolfaria.phrasesgenerator.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setTitle("Lista de usuários cadastrados");
    }
}
