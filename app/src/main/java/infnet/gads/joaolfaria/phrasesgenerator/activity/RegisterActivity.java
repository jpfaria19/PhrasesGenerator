package infnet.gads.joaolfaria.phrasesgenerator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import infnet.gads.joaolfaria.phrasesgenerator.R;
import infnet.gads.joaolfaria.phrasesgenerator.domain.User;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPassword, edtConfirmPassword, edtCPF;
    Boolean flag = false;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Cadastrar novo usuário");

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtCPF = findViewById(R.id.edtCPF);
    }

    public void clearForm(View view) {
        edtName.getText().clear();
        edtPassword.getText().clear();
        edtEmail.getText().clear();
        edtCPF.getText().clear();

        edtName.requestFocus();
    }

    //Validação exclusiva para e-mail
    private boolean isEmailValidation(EditText text) {
        CharSequence chrEmail = text.getText().toString();
        return (!TextUtils.isEmpty(chrEmail) && Patterns.EMAIL_ADDRESS.matcher(chrEmail).matches());
    }

    private void validationForm() {

        flag = false;

        if (edtName.getText().toString().isEmpty() || !edtName.getText().toString().matches("[a-zA-Z-ã.? ]*")) {
            edtName.setError("O campo nome não pode ficar em branco e não são permitidos caracteres especiais.");
            flag = true;
        }
        if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError("O campo senha não pode ficar em branco.");
            flag = true;
        }
        if (edtConfirmPassword.getText().toString().isEmpty() || !edtConfirmPassword.getText().toString().equals(edtPassword.getText().toString())) {
            edtConfirmPassword.setError("É obrigatório confirmar sua senha e elas precisão ser iguais.");
            flag = true;
        }
        if (!isEmailValidation(edtEmail)) {
            edtEmail.setError("Insira um e-mail válido. (XXX@XXXX.XXX)");
            flag = true;
        }
        if (edtCPF.getText().toString().isEmpty()) {
            edtCPF.setError("O campo CPF não pode ficar em branco.");
            flag = true;
        }
    }

    public void saveContact(View view) {
        validationForm();
        if (!flag){
            user.setName(edtName.getText().toString());
            user.setEmail(edtEmail.getText().toString());
            user.setPassword(edtPassword.getText().toString());
            user.setConfirmPassword(edtConfirmPassword.getText().toString());
            user.setCPF(edtCPF.getText().toString());

            //TODO: IMPLEMENTAR A LOGICA PARA SALVAR EM ARQUIVO TXT

            Intent toLogin = new Intent(this, MainActivity.class);
            startActivity(toLogin);
        }

    }
}
