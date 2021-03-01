package com.example.gezi;


public class Upload {
    public String sehir;
    public String imageURL;
    public String metin;
    public String kullanıcı;

    public Upload(){}

    public Upload(String msehir,String mmetin,String kull,String url) {
        this.sehir = msehir;
        this.metin = mmetin;
        this.kullanıcı=kull;
        this.imageURL = url;
    }

    public String getSehir() {
        return sehir;
    }

    public String getMetin() {
        return metin;
    }

    public String getKullanıcı() {
        return kullanıcı;
    }

    public String getImageURL() {
        return imageURL;
    }
}

