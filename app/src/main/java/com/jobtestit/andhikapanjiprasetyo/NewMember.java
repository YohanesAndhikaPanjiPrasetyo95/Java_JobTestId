package com.jobtestit.andhikapanjiprasetyo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewMember extends AppCompatActivity {
    SqliteHelper sqliteHelper;
    Button btnSave;
    EditText edt_nama, edt_alamat, edt_username, edt_pass, edt_tgl_lahir;
    Spinner spin_jenis_kelamin;
    DatePickerDialog dt_tgl_lahir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_member);

        sqliteHelper = new SqliteHelper(this);
        btnSave = findViewById(R.id.btn_simpan);
        edt_nama = findViewById(R.id.edt_Nama);
        edt_alamat = findViewById(R.id.edt_Alamat);
        edt_username = findViewById(R.id.edt_Username);
        edt_pass = findViewById(R.id.edt_Password);
        spin_jenis_kelamin = findViewById(R.id.spin_jenis_kelamin);
//        dt_tgl_lahir = findViewById(R.id.edt_Tgl_Lahir);
        edt_tgl_lahir = findViewById(R.id.edt_Tgl_Lahir);
        edt_tgl_lahir.setInputType(InputType.TYPE_NULL);
        edt_tgl_lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                dt_tgl_lahir = new DatePickerDialog(NewMember.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edt_tgl_lahir.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                dt_tgl_lahir.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("CLICK","Get Clicker");
//                Date date1= (Date) new Date
//                        (dt_tgl_lahir.getYear(), dt_tgl_lahir.getMonth(), dt_tgl_lahir.getDayOfMonth());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String dateString = sdf.format(date1);
                int countMember = 0;
                String kodemember = "";
                SQLiteDatabase db = sqliteHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("SELECT member.kode_member " +
                        "FROM member INNER JOIN user ON member.kode_member = user.kode_member WHERE user.id_role = 2", null);

                cursor.moveToFirst();
                for (int cc = 0; cc < cursor.getCount(); cc++) {
                    cursor.moveToPosition(cc);
                    countMember++;
                }
                kodemember = "MBR" + String.valueOf(countMember + 1);
                Log.w("Kode Member","Get" + kodemember);
                // TODO Auto-generated method stub
                db.execSQL("insert into member(kode_member, nama, tanggal_lahir, alamat, jenis_kelamin, username, password) values('" +
                        kodemember + "','" +
                        edt_nama.getText().toString() + "','" +
                        edt_tgl_lahir.AUTOFILL_TYPE_DATE + "','" +
                        edt_alamat.getText().toString() + "','" +
                        spin_jenis_kelamin.getSelectedItem().toString() + "','" +
                        edt_username.getText().toString() + "','" +
                        edt_pass.getText().toString() + "')");
                db.execSQL("insert into user(kode_member, id_role) values('" +
                        kodemember + "','" +
                        "2" + "')");
                Toast.makeText(getApplicationContext(), "Berhasil Menambahkan Data Member", Toast.LENGTH_LONG).show();
                HomeAdminActivity.ma.RefreshList("");
                finish();
            }
        });
    }
}