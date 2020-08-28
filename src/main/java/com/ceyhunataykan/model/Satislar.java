/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceyhunataykan.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ceyhunataykan
 */
@Entity(name = "Satislar")
public class Satislar {

    @Id
    @SequenceGenerator(name = "SATISLAR_SEQUENCE_GENERATOR", sequenceName = "SATISLAR_SEQUENCE", initialValue = 1)
    @GeneratedValue(generator = "SATISLAR_SEQUENCE_GENERATOR")
    private int satis_id;
    private int satis_adet;
    private double satis_birimfiyat;
    private double satis_fiyat;

    @Temporal(TemporalType.TIMESTAMP)
    private Date satis_tarih;
        
    @OneToOne
    @JoinColumn(name = "urun_id")
    private Urunler urunler;

    @OneToOne
    @JoinColumn(name = "fis_id")
    private Fisler fisler;

    @OneToOne
    @JoinColumn(name = "kullanici_id")
    private Kullanicilar kullanicilar;
    
    public Satislar() {

    }

    public Satislar(int satis_id, int satis_adet, double satis_birimfiyat, double satis_fiyat, Urunler urunler, Date satis_tarih, Fisler fisler, Kullanicilar kullanicilar) {
        this.satis_id = satis_id;
        this.satis_adet = satis_adet;
        this.satis_birimfiyat = satis_birimfiyat;
        this.satis_fiyat = satis_fiyat;
        this.urunler = urunler;
        this.satis_tarih = satis_tarih;
        this.fisler = fisler;
        this.kullanicilar = kullanicilar;
    }

    public int getSatis_id() {
        return satis_id;
    }

    public void setSatis_id(int satis_id) {
        this.satis_id = satis_id;
    }

    public int getSatis_adet() {
        return satis_adet;
    }

    public void setSatis_adet(int satis_adet) {
        this.satis_adet = satis_adet;
    }

    public double getSatis_birimfiyat() {
        return satis_birimfiyat;
    }

    public void setSatis_birimfiyat(double satis_birimfiyat) {
        this.satis_birimfiyat = satis_birimfiyat;
    }

    public double getSatis_fiyat() {
        return satis_fiyat;
    }

    public void setSatis_fiyat(double satis_fiyat) {
        this.satis_fiyat = satis_fiyat;
    }

    public Urunler getUrunler() {
        return urunler;
    }

    public void setUrunler(Urunler urunler) {
        this.urunler = urunler;
    }

    public Date getSatis_tarih() {
        return satis_tarih;
    }

    public void setSatis_tarih(Date satis_tarih) {
        this.satis_tarih = satis_tarih;
    }

    public Fisler getFisler() {
        return fisler;
    }

    public void setFisler(Fisler fisler) {
        this.fisler = fisler;
    }

    public Kullanicilar getKullanicilar() {
        return kullanicilar;
    }

    public void setKullanicilar(Kullanicilar kullanicilar) {
        this.kullanicilar = kullanicilar;
    }

    @Override
    public String toString() {
        return "Satislar{" + "satis_id=" + satis_id + ", satis_adet=" + satis_adet + ", satis_birimfiyat=" + satis_birimfiyat + ", satis_fiyat=" + satis_fiyat + ", urunler=" + urunler + ", satis_tarih=" + satis_tarih + ", fisler=" + fisler + ", kullanicilar=" + kullanicilar + '}';
    }      

}
