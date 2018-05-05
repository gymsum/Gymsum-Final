package com.tugas.hisyam.gymsum;

/**
 * Created by HISYAM on 5/3/2018.
 */


    public class infolist {
        //deklarasi variable yang akan digunakan
        String video ;
        String nama;
        String desc;

        //method setter
        public infolist(String video, String nama, String desc){
            this.video=video;
            this.nama=nama;
            this.desc=desc;
        }

        //method mengambil gambar
        public String getVideo() {
            return video;
        }

        //method mengambil nama
        public String getNama() {
            return nama;
        }

        //method mengambil deskripsi
        public String getDesc() {
            return desc;
        }
    }

