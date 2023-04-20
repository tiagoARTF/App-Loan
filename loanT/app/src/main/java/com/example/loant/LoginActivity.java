package com.example.loant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText userEdt;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        setVariable();
    }

    private void initView() {
        userEdt = findViewById(R.id.editTextTextPersonName);
        loginBtn=findViewById(R.id.loginBtn);
    }
    private void setVariable(){
        loginBtn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(userEdt.getText().toString().trim())) {
                Toast.makeText(LoginActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                // al menos uno de los campos está vacío
            } else {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

        });
    }
}