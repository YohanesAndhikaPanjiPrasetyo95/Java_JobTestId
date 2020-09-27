package com.jobtestit.andhikapanjiprasetyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeAdminActivity extends AppCompatActivity {
    String[] daftar;
//    int[] daftar_id;
    SqliteHelper sqliteHelper;
    protected Cursor cursor;
    ListView ListViewMember;
    ArrayAdapter<String> arrayAdapter;
    public static HomeAdminActivity ma;
    Button btn_input_member, btn_logout;
    SharedPreferences prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        ListViewMember = (ListView) findViewById(R.id.lvDataMember);
        btn_input_member = findViewById(R.id.btn_tambah_member);

        btn_input_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdminActivity.this, NewMember.class);
                startActivity(intent);
            }
        });

        prf = getSharedPreferences("member_details",MODE_PRIVATE);
        btn_logout = (Button) findViewById(R.id.buttonLogout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeAdminActivity.this, LoginActivity.class);
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                startActivity(in);
            }
        });

        sqliteHelper = new SqliteHelper(this);
        List<String> myList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList);
        ListViewMember.setAdapter(arrayAdapter);

        ma = this;
        RefreshList("");
    }

    public void RefreshList(String searchText) {
        SQLiteDatabase db = sqliteHelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT nama " +
                "FROM member INNER JOIN user ON member.kode_member = user.kode_member WHERE user.id_role = 2", null);
        daftar = new String[cursor.getCount()];
//        daftar_id = new int[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString();
//            daftar_id[cc] = cursor.getInt(7);
        }
        ListViewMember.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
    }
}