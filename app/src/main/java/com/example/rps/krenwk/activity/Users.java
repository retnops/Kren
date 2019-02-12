package com.example.rps.krenwk.activity;

public class Users {
    String _id, nem, nophone, mail,  lati, longi;

    public Users() {
    }

    public Users(String _id,
                 String namae,
                 String nomorphone,
                 String alamatmail,
                 String latitude,
                 String longitude) {

        this._id = _id;
        this.nem = namae;
        this.nophone = nomorphone;
        this.mail = alamatmail;
        this.lati = latitude;
        this.longi = longitude;
    }

    public String get_Id() {
        return _id;
    }

    public void set_Id(String _id) {
        this._id = _id;
    }

    public String getNamae() {
        return nem;
    }

    public void setNamae(String namae) {
        this.nem = namae;
    }

    public String getNomorphone() {
        return nophone;
    }

    public void setNomorphone(String nomorphone) {
        this.nophone = nomorphone;
    }

    public String getAlamatmail(){
        return mail;
    }

    public void setAlamatmail(String alamatmail) {
        this.mail = alamatmail;
    }

    public String getLatitude(){
        return lati;
    }

    public void setLatitude(String latitude) {
        this.lati = latitude;
    }

    public String getLongitude(){
        return longi;
    }

    public void setLongitude(String longitude) {
        this.longi = longitude;
    }

}