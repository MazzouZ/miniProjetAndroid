package com.mz.miniprojetandroid.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mz.miniprojetandroid.Dao.miniProjetDBHelper;
import com.mz.miniprojetandroid.Models.User;
import com.mz.miniprojetandroid.R;

public class MainActivity extends AppCompatActivity {

    EditText login,password,pass;
    Button signIn,redirectRegister;
    miniProjetDBHelper db = new miniProjetDBHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);
        redirectRegister = findViewById(R.id.redirectRegister);

        final String Login = login.getText().toString();
        final String Password = password.getText().toString();

        redirectRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Login.isEmpty() || Password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    login.setText("");
                    password.setText("");
                }
                else{
                    if(db.checkUser(new User(Login,Password))== true)
                        startActivity(new Intent(getApplicationContext(), Navigateur.class));
                    else
                        Toast.makeText(getApplicationContext(), "Erreur404", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
