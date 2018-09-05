package infnet.gads.joaolfaria.phrasesgenerator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import infnet.gads.joaolfaria.phrasesgenerator.R;

public class DetailsUserActivity extends AppCompatActivity {

    TextView detailName, detailEmail, detailPassword, detailCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_user);


        detailName = findViewById(R.id.detailName);
        detailEmail = findViewById(R.id.detailEmail);
        detailPassword = findViewById(R.id.detailPassword);
        detailCPF = findViewById(R.id.detailCPF);



        //NOME
        Bundle extraName = getIntent().getExtras();
        String Name = extraName.getString("name");
        detailName.setText(Name);

        getSupportActionBar().setTitle("Detalhes do(a) amiguinho(a): " + Name);



        //EMAIL
        Bundle extraEmail = getIntent().getExtras();
        String Email = extraEmail.getString("email");
        detailEmail.setText(Email);

        //SENHA
        Bundle extraSenha = getIntent().getExtras();
        String Senha = extraSenha.getString("password");
        detailPassword.setText(Senha);

        //CPF
        Bundle extraCPF = getIntent().getExtras();
        String CPF = extraCPF.getString("cpf");
        detailCPF.setText(CPF);
    }
}
