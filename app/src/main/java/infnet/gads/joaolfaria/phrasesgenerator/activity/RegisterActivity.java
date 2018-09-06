package infnet.gads.joaolfaria.phrasesgenerator.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.io.FileOutputStream;

import infnet.gads.joaolfaria.phrasesgenerator.DAO.ConfiguracaoFirebase;
import infnet.gads.joaolfaria.phrasesgenerator.R;
import infnet.gads.joaolfaria.phrasesgenerator.domain.User;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPassword, edtConfirmPassword, edtCPF;
    Boolean flag = false;
    User user = new User();
    FirebaseAuth auth;
    String TAG = "";
    String fileName = "listUsers.txt";
    FileOutputStream outputStream;


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

        auth = ConfiguracaoFirebase.getFirebaseAuth();
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
        if (!flag) {
            user.setName(edtName.getText().toString());
            user.setEmail(edtEmail.getText().toString());
            user.setPassword(edtPassword.getText().toString());
            user.setConfirmPassword(edtConfirmPassword.getText().toString());
            user.setCPF(edtCPF.getText().toString());


            auth.createUserWithEmailAndPassword(user.getEmail().toString(), user.getPassword().toString())
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                insereUsuario();

                                Toast.makeText(RegisterActivity.this, "Usuário cadastrado com sucesso.", Toast.LENGTH_LONG).show();

                                Intent toLogin = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(toLogin);
                                finish();
                            } else {

                                String erroExecucao = "";

                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    erroExecucao = "Digite uma senha mais forte, contendo no mínimo 8 caracteres e que contenha letras e números";
                                    Log.v(TAG, e.toString());
                                } catch (FirebaseAuthUserCollisionException e) {
                                    erroExecucao = "Esse e-mail já está cadastrado!";
                                    Log.v(TAG, e.toString());
                                } catch (Exception e) {
                                    erroExecucao = "Erro ao caddastrar um novo usuário";
                                    Log.v(TAG, e.toString());
                                }

                                Toast.makeText(RegisterActivity.this, erroExecucao, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void insereUsuario(){
        try {
            outputStream = openFileOutput(String.valueOf(fileName), Context.MODE_APPEND | Context.MODE_PRIVATE);

            User usuario = new User(edtName.getText().toString(), edtEmail.getText().toString(), edtPassword.getText().toString(), edtConfirmPassword.getText().toString(), edtCPF.getText().toString());
            usuario.setName(edtName.getText().toString());
            usuario.setEmail(edtEmail.getText().toString());
            usuario.setPassword(edtPassword.getText().toString());
            usuario.setConfirmPassword(edtConfirmPassword.getText().toString());
            usuario.setCPF(edtCPF.getText().toString());


            String separetor = "#";

            outputStream.write(separetor.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.write(usuario.getName().getBytes());
            outputStream.write("\n".getBytes());
            outputStream.write(usuario.getEmail().getBytes());
            outputStream.write("\n".getBytes());
            outputStream.write(usuario.getPassword().getBytes());
            outputStream.write("\n".getBytes());
            outputStream.write(usuario.getConfirmPassword().getBytes());
            outputStream.write("\n".getBytes());
            outputStream.write(usuario.getCPF().getBytes());
            outputStream.write("\n".getBytes());
            outputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
