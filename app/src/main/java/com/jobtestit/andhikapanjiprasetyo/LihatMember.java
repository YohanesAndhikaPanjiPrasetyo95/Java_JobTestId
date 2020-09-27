package com.jobtestit.andhikapanjiprasetyo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class LihatMember extends AppCompatActivity {
    TextView kode_member, nama, tanggal_lahir, alamat, jenis_kelamin, username, password;
    SqliteHelper sqliteHelper;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_member);

        kode_member = (TextView) findViewById(R.id.txtLihatKodeMember);
        nama = (TextView) findViewById(R.id.txtLihatNama);
        tanggal_lahir = (TextView) findViewById(R.id.txtLihatTanggalLahir);
        alamat = (TextView) findViewById(R.id.txtLihatAlamat);
        jenis_kelamin = (TextView) findViewById(R.id.txtLihatJenisKelamin);
        username = (TextView) findViewById(R.id.txtLihatUsername);
        password = (TextView) findViewById(R.id.txtLihatPassword);
        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT kode_member, nama, kelamin, tanggal_lahir, alamat, jenis_kelamin, username, password " +
                "FROM member INNER JOIN user ON member.kode_member = member.kode_member " +
                "WHERE nama = '" + getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            kode_member.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            tanggal_lahir.setText(cursor.getString(2).toString());
            alamat.setText(cursor.getString(3).toString());
            jenis_kelamin.setText(cursor.getString(4).toString());
            username.setText(cursor.getString(5).toString());
            password.setText(cursor.getString(6).toString());
        }
    }
}