package com.upinads.roadsideassistance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText name, username, email, address, phone, password;
    Button registerButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DatabaseHelper(this);

        name = findViewById(R.id.etName);
        username = findViewById(R.id.etUsername);
        email = findViewById(R.id.etEmail);
        address = findViewById(R.id.etAddress);
        phone = findViewById(R.id.etPhone);
        password = findViewById(R.id.etPassword);
        registerButton = findViewById(R.id.btnRegister);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = name.getText().toString();
                String usernameStr = username.getText().toString();
                String emailStr = email.getText().toString();
                String addressStr = address.getText().toString();
                String phoneStr = phone.getText().toString();
                String passwordStr = password.getText().toString();

                if (nameStr.isEmpty() || usernameStr.isEmpty() || emailStr.isEmpty() ||
                        addressStr.isEmpty() || phoneStr.isEmpty() || passwordStr.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = db.addUser(nameStr, usernameStr, emailStr, addressStr, phoneStr, passwordStr);
                    if (isInserted) {
                        Toast.makeText(RegistrationActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
} 