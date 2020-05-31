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

public class SignUp extends AppCompatActivity {

    private EditText loginRegister;
    private EditText passwordRegister;
    private EditText pass;
    private Button signUp;
    private Button redirectLogin;

    miniProjetDBHelper db = new miniProjetDBHelper(SignUp.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loginRegister = (EditText) findViewById(R.id.registerlogin);
        passwordRegister  = (EditText) findViewById(R.id.registerpassword);
        pass = (EditText) findViewById(R.id.pass);

        signUp = (Button) findViewById(R.id.signUp);
        redirectLogin = (Button) findViewById(R.id.redirectLogin);

        final String RegisterLogin = loginRegister.getText().toString().trim();
        final String RegisterPassword = passwordRegister.getText().toString().trim();
        final String Pass = pass.getText().toString().trim();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "user ="+RegisterLogin+" "+Pass, Toast.LENGTH_SHORT).show();
                if(RegisterLogin.isEmpty() || RegisterPassword.isEmpty() || Pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    loginRegister.setText("");
                    passwordRegister.setText("");
                    pass.setText("");
                }
                else {
                    if(!RegisterPassword.equals(Pass)) {
                        Toast.makeText(getApplicationContext(), "Veuillez bien verifier votre mode de passe", Toast.LENGTH_SHORT).show();
                        loginRegister.setText("");
                        passwordRegister.setText("");
                        pass.setText("");
                    }
                    else {
                        db.saveNewUser(new User(RegisterLogin,RegisterPassword));
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }

                }


            }
        });

        redirectLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}
