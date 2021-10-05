package com.example.demo;

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
import com.google.firebase.database.FirebaseDatabase;

public class demoregister extends AppCompatActivity implements View.OnClickListener{
    private TextView register;
    private EditText name,email,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demoregister);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth=FirebaseAuth.getInstance();
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(this);
        name=(EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                registerUser();
                break;
        }

    }
    private void registerUser(){
        String namee=name.getText().toString().trim();
        String emaill=email.getText().toString().trim();
        String passwordd=password.getText().toString().trim();
        if(namee.isEmpty()){
            name.setError("Full name is required");
            name.requestFocus();
            return;
        }
        if(emaill.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }
        if(passwordd.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;

        }
        if(passwordd.length()<6){
            password.setError("Min password length should be 6 characters!");
            password.requestFocus();
            return;

        }
        mAuth.createUserWithEmailAndPassword(emaill,passwordd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user =new User(namee,emaill);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(demoregister.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(demoregister.this, "Failed to register!Try again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(demoregister.this,"Already registered",Toast.LENGTH_LONG).show();


                        }
                    }
                });


    }
}