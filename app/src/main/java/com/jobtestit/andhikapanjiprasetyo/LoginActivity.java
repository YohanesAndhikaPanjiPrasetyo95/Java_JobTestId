package com.jobtestit.andhikapanjiprasetyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import static android.text.Html.fromHtml;

public class LoginActivity extends AppCompatActivity {
    //Declaration EditTexts
    EditText editTextUsername;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutUsername;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    SharedPreferences preferences;
    Intent intent, intentAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sqliteHelper = new SqliteHelper(this);
        editTextUsername = (EditText) findViewById(R.id.edt_Username);
        editTextPassword = (EditText) findViewById(R.id.edt_Password);
        buttonLogin = (Button) findViewById(R.id.btn_login);
        preferences = getSharedPreferences("member_details", MODE_PRIVATE);
        intent = new Intent(LoginActivity.this, HomeMemberActivity.class);
        intentAdmin = new Intent(LoginActivity.this, HomeAdminActivity.class);

        if (preferences.contains("username") && preferences.contains("password")) {
            String username = preferences.getString("username", "");
            Boolean resAdmin =  sqliteHelper.checkadmin(username);
            if(resAdmin){
                startActivity(intentAdmin);
            }
            else {
                startActivity(intent);
            }
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUsername.length() == 0 && editTextPassword.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Masukkan Username dan Password", Toast.LENGTH_SHORT).show();
                } else if (editTextUsername.length() == 0 || editTextPassword.length() == 0) {
                    if (editTextUsername.length() == 0) {
                        Toast.makeText(LoginActivity.this, "Masukkan Username", Toast.LENGTH_SHORT).show();
                    } else if (editTextPassword.length() == 0) {
                        Toast.makeText(LoginActivity.this, "Masukkan Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String username = editTextUsername.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();
                    Boolean res = sqliteHelper.checkuser(username, password);

                    if (res == true) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.commit();
                        Toast.makeText(LoginActivity.this, "Anda berhasil masuk..", Toast.LENGTH_SHORT).show();
                        Boolean resAdmin =  sqliteHelper.checkadmin(username);
                        if(resAdmin){
                            startActivity(intentAdmin);
                        }
                        else {
                            startActivity(intent);
                        }
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Username atau Password anda salah!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}