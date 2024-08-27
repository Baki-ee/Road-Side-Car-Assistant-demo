package com.upinads.roadsideassistance;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditRegistrationActivity extends AppCompatActivity {

    EditText name, username, email, address, phone, password;
    Button saveButton;
    DatabaseHelper db;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_registration);

        db = new DatabaseHelper(this);

        name = findViewById(R.id.etName);
        username = findViewById(R.id.etUsername);
        email = findViewById(R.id.etEmail);
        address = findViewById(R.id.etAddress);
        phone = findViewById(R.id.etPhone);
        password = findViewById(R.id.etPassword);
        saveButton = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        loadUserDetails();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = name.getText().toString();
                String usernameStr = username.getText().toString();
                String emailStr = email.getText().toString();
                String addressStr = address.getText().toString();
                String phoneStr = phone.getText().toString();
                String passwordStr = password.getText().toString();

                boolean isUpdated = db.updateUser(userId, nameStr, usernameStr, emailStr, addressStr, phoneStr, passwordStr);

                if (isUpdated) {
                    Toast.makeText(EditRegistrationActivity.this, "Details updated successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditRegistrationActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadUserDetails() {
        Cursor userCursor = db.getUserById(userId);
        if (userCursor != null && userCursor.moveToFirst()) {
            name.setText(userCursor.getString(userCursor.getColumnIndex("name")));
            username.setText(userCursor.getString(userCursor.getColumnIndex("username")));
            email.setText(userCursor.getString(userCursor.getColumnIndex("email")));
            address.setText(userCursor.getString(userCursor.getColumnIndex("address")));
            phone.setText(userCursor.getString(userCursor.getColumnIndex("phone")));
            password.setText(userCursor.getString(userCursor.getColumnIndex("password")));
        }
    }
}