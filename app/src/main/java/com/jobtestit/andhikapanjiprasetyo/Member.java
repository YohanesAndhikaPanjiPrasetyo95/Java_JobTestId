package com.jobtestit.andhikapanjiprasetyo;

public class Member {
    private long Kode_Member;
    private String Nama;
    private String Tanggal_Lahir;
    private String Alamat;
    private String Jenis_Kelamin;
    private String Username;
    private String Password;

    public Member(Long kode_Member, String nama, String tanggal_Lahir, String alamat, String jenis_Kelamin, String username, String password) {
        Kode_Member = kode_Member;
        Nama = nama;
        Tanggal_Lahir = tanggal_Lahir;
        Alamat = alamat;
        Jenis_Kelamin = jenis_Kelamin;
        Username = username;
        Password = password;
    }

    public long getKode_Member() {
        return Kode_Member;
    }

    public void setKode_Member(long kode_Member) {
        Kode_Member = kode_Member;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getTanggal_Lahir() {
        return Tanggal_Lahir;
    }

    public void setTanggal_Lahir(String tanggal_Lahir) {
        Tanggal_Lahir = tanggal_Lahir;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getJenis_Kelamin() {
        return Jenis_Kelamin;
    }

    public void setJenis_Kelamin(String jenis_Kelamin) {
        Jenis_Kelamin = jenis_Kelamin;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
