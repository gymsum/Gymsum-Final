package com.tugas.hisyam.gymsum.model;

/**
 * Created by HISYAM on 4/7/2018.
 */

public class mProfil {
    String nama;
    int beratBadan;
    int tinggi;
    int usia;
    private String userID;
    private String imagePost;

    public mProfil() {
    }

    public mProfil(String userID, String nama, int beratBadan, int tinggi, int usia, String mImagePost) {
       this.nama = nama;
        this.beratBadan = beratBadan;
        this.tinggi = tinggi;
        this.usia = usia;
        this.userID = userID;
        this.imagePost = mImagePost;
    }
    public String getImagePost() {
        return imagePost;
    }

    public int getUsia() {
        return usia;
    }

    public String getNama() {
        return nama;
    }

    public int getBeratBadan() {
        return beratBadan;
    }

    public int getTinggi() {
        return tinggi;
    }
    public String getUserID(){
        return userID;
    }
}
