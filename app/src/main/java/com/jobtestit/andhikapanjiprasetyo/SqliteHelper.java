package com.jobtestit.andhikapanjiprasetyo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "DB_JobTestIT";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE USER
    public static final String TABLE_USER = "user";

    //TABLE MEMBER
    public static final String TABLE_MEMBER = "member";

    //TABLE ROLE
    public static final String TABLE_ROLE = "role";

    //TABLE USER COLUMNS
    //KODE USER COLUMN @primaryKey
    public static final String KEY_ID = "id";
    //COLUMN kode member
    public static final String KEY_KODE_MEMBER = "kode_member";
    //COLUMN id role
    public static final String KEY_ID_ROLE = "id_role";

    //TABLE MEMBER COLUMNS
    //KODE MEMBER COLUMN @primaryKey
    public static final String KEY_MEMBER = "kode_member";
    //COLUMN nama
    public static final String KEY_NAMA = "nama";
    //COLUMN tanggal lahir
    public static final String KEY_TGL_LAHIR  = "tanggal_lahir";
    //COLUMN alamat
    public static final String KEY_ALAMAT = "alamat";
    //COLUMN jenis kelamin
    public static final String KEY_J_KELAMIN = "jenis_kelamin";
    //COLUMN username
    public static final String KEY_USER_NAME = "username";
    //COLUMN password
    public static final String KEY_PASSWORD = "password";

    //TABLE ROLE COLUMNS
    //ID ROLES COLUMN @primaryKey
    public static final String KEY_ID_ROLES = "id_roles";
    //COLUMN role
    public static final String KEY_ROLE = "role";

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TABLE USER
        String tbl_user = "create table user(id integer primary key autoincrement, kode_member varchar, id_role integer null);";
        Log.d("Data User", "onCreate: " + tbl_user);
        db.execSQL(tbl_user);

        //TABLE MEMBER
        String tbl_member = "create table member(kode_member varchar primary key, nama text null, tanggal_lahir date null, " +
                "alamat varchar null, jenis_kelamin varchar null, username varchar null, password varchar null);";
        Log.d("Data Member", "onCreate: " + tbl_member);
        db.execSQL(tbl_member);

        //TABLE ROLE
        String tbl_role = "create table role(id_roles integer primary key autoincrement, role text null);";
        Log.d("Data Role", "onCreate: " + tbl_role);
        db.execSQL(tbl_role);

        //Data Dummy
        tbl_role = "INSERT INTO role(id_roles, role) VALUES ('1', 'Admin'), ('2', 'Member')";
        db.execSQL(tbl_role);

        tbl_member = "INSERT INTO member(kode_member, nama, tanggal_lahir, alamat, jenis_kelamin, username, password) " +
                "VALUES ('ADMN1', 'Admin', '12/12/1995', 'Jl.Sakura No.32', 'Laki-laki', 'Admin', '123456'), ('MBR1', 'Andhika', '03/07/1990', 'Jl.Durian No.40', 'Perempuan', 'Andhika90', '123456')";
        db.execSQL(tbl_member);

        tbl_user = "INSERT INTO user(id, kode_member, id_role) " +
                "VALUES ('1', 'ADMN1', '1'), ('2', 'MBR1', '2')";
        db.execSQL(tbl_user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        String  query = "DROP TABLE IF EXISTS "+TABLE_USER;
        arg0.execSQL(query);
    }

    public boolean checkuser(String username, String password) {
        String[] columns = { KEY_KODE_MEMBER} ;
        SQLiteDatabase db = getReadableDatabase();
        String selection = KEY_USER_NAME + "=?" + " and " + KEY_PASSWORD + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_MEMBER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }

    public boolean checkadmin(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM member INNER JOIN user ON member.kode_member = user.kode_member " +
                "WHERE member.username = '" + username + "' AND user.id_role = 1",null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }
}
