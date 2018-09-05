package infnet.gads.joaolfaria.phrasesgenerator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import infnet.gads.joaolfaria.phrasesgenerator.R;
import infnet.gads.joaolfaria.phrasesgenerator.adapter.UserAdapter;
import infnet.gads.joaolfaria.phrasesgenerator.domain.User;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    String fileName = "listUsers.txt";

    List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setTitle("Lista de usuários cadastrados");

        userAdapter = new UserAdapter(users);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        FileInputStream fis = null;
                        try {
                            fis = openFileInput(fileName);
                            InputStreamReader isr = new InputStreamReader(fis);
                            BufferedReader br = new BufferedReader(isr);
                            String line = br.readLine();
                            while (line != null) {
                                if (line.equals("#")) {
                                    String name = br.readLine();
                                    String email = br.readLine();
                                    String password = br.readLine();
                                    String confirmPassword = br.readLine();
                                    String cpf = br.readLine();
                                    User user = new User(name, email, password, confirmPassword, cpf);
                                    users.add(user);
                                }
                                line = br.readLine();
                            }
                        } catch (final FileNotFoundException fileNotFound) {
                            fileNotFound.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        recyclerView.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        UserAdapter adapter = new UserAdapter(users);
                                        recyclerView.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                        );
                    }
                }
        ).start();
    }


}
