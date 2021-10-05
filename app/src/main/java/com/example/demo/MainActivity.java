package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView registermain,forgotpassword;
    private EditText emailmain,passwordmain;
    private Button login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        registermain=(TextView)findViewById(R.id.registermain);
        registermain.setOnClickListener(this);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(this);
        emailmain=(EditText)findViewById(R.id.emailmain);
        passwordmain=(EditText)findViewById(R.id.passwordmain);
        forgotpassword=(TextView)findViewById(R.id.forgotpassword);
        forgotpassword.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.registermain:
                startActivity(new Intent(this,demoregister.class));
                break;
            case R.id.login:
                login();
            case R.id.forgotpassword:
                startActivity(new Intent(this,ForgotPassword.class));
                break;

        }
    }
    private void login() {
        //progressBar2.setVisibility(View.GONE);

        String email1 = emailmain.getText().toString().trim();
        String password1 = passwordmain.getText().toString().trim();

        if (email1.isEmpty()) {
            emailmain.setError("Email is required");
            emailmain.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            emailmain.setError("Please provide valid email");
            emailmain.requestFocus();
            return;
        }
        if (password1.isEmpty()) {
            passwordmain.setError("Password is required");
            passwordmain.requestFocus();
            return;

        }
        if (password1.length() < 6) {
            passwordmain.setError("Min password length should be 6 characters!");
            passwordmain.requestFocus();
            return;

        }
        mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()) {
                        startActivity(new Intent(MainActivity.this, departments.class));
                        // progressBar2.setVisibility(View.GONE);
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Check email to verify account!!", Toast.LENGTH_LONG).show();

                       // Toast.makeText(MainActivity.this, "Check email to verify account!!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Failed to login!!", Toast.LENGTH_LONG).show();
                    //progressBar2.setVisibility(View.GONE);
                }
            }
        });
    }
}