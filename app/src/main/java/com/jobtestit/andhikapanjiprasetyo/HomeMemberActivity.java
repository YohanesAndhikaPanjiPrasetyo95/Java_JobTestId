package com.jobtestit.andhikapanjiprasetyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeMemberActivity extends AppCompatActivity {
    Button btn_lihat_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_member);
        btn_lihat_data = (Button) findViewById(R.id.btn_lihat_data);
        btn_lihat_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMemberActivity.this, LihatMember.class);
                startActivity(intent);
            }
        });
    }

}