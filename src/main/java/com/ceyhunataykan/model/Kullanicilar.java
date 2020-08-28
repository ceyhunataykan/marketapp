/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceyhunataykan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author ceyhunataykan
 */
@Entity(name = "Kullanicilar")
public class Kullanicilar {

    @Id
    @SequenceGenerator(name = "KULLANICILAR_SEQUENCE_GENERATOR", sequenceName = "KULLANICILAR_SEQUENCE", initialValue = 1)
    @GeneratedValue(generator = "KULLANICILAR_SEQUENCE_GENERATOR")
    private int kullanici_id;
    
    private String ad;
    private String soyad;
    private String kullanici_adi;
    private String kullanici_parola;
    private String rol;

    public Kullanicilar() {
    }

    public Kullanicilar(String ad, String soyad, String kullanici_adi, String kullanici_parola, String rol) {
        this.ad = ad;
        this.soyad = soyad;
        this.kullanici_adi = kullanici_adi;
        this.kullanici_parola = kullanici_parola;
        this.rol = rol;
    }
    
    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getKullanici_parola() {
        return kullanici_parola;
    }

    public void setKullanici_parola(String kullanici_parola) {
        this.kullanici_parola = kullanici_parola;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
