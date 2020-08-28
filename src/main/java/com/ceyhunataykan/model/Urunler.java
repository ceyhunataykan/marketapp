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
@Entity(name = "Urunler")
public class Urunler {

    @Id
    @SequenceGenerator(name = "URUNLER_SEQUENCE_GENERATOR", sequenceName = "URUNLER_SEQUENCE", initialValue = 1)
    @GeneratedValue(generator = "URUNLER_SEQUENCE_GENERATOR")
    private int urun_id;
    private String urun_barkod;
    private String urun_adi;
    private int urun_adet;
    private double urun_alis;
    private double urun_satis;
    private int urun_kdv;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date urun_eklenme_tarih;

    @Temporal(TemporalType.TIMESTAMP)
    private Date urun_guncellenme_tarih;
    
    private boolean urun_durum;
    
    @OneToOne
    @JoinColumn(name = "kategori_id")
    private Kategoriler kategoriler;
    
    @OneToOne
    @JoinColumn(name = "marka_id")
    private Markalar markalar;

    public Urunler() {

    }

    public Urunler(String urun_barkod, String urun_adi, int urun_adet, double urun_alis, double urun_satis, int urun_kdv, Date urun_eklenme_tarih, Date urun_guncellenme_tarih, boolean urun_durum, Kategoriler kategoriler, Markalar markalar) {
        this.urun_barkod = urun_barkod;
        this.urun_adi = urun_adi;
        this.urun_adet = urun_adet;
        this.urun_alis = urun_alis;
        this.urun_satis = urun_satis;
        this.urun_kdv = urun_kdv;
        this.urun_eklenme_tarih = urun_eklenme_tarih;
        this.urun_guncellenme_tarih = urun_guncellenme_tarih;
        this.urun_durum = urun_durum;
        this.kategoriler = kategoriler;
        this.markalar = markalar;
    }


    public int getUrun_id() {
        return urun_id;
    }

    public void setUrun_id(int urun_id) {
        this.urun_id = urun_id;
    }

    public String getUrun_barkod() {
        return urun_barkod;
    }

    public void setUrun_barkod(String urun_barkod) {
        this.urun_barkod = urun_barkod;
    }

    public String getUrun_adi() {
        return urun_adi;
    }

    public void setUrun_adi(String urun_adi) {
        this.urun_adi = urun_adi;
    }

    public int getUrun_adet() {
        return urun_adet;
    }

    public void setUrun_adet(int urun_adet) {
        this.urun_adet = urun_adet;
    }

    public double getUrun_alis() {
        return urun_alis;
    }

    public void setUrun_alis(double urun_alis) {
        this.urun_alis = urun_alis;
    }

    public double getUrun_satis() {
        return urun_satis;
    }

    public void setUrun_satis(double urun_satis) {
        this.urun_satis = urun_satis;
    }

    public int getUrun_kdv() {
        return urun_kdv;
    }

    public void setUrun_kdv(int urun_kdv) {
        this.urun_kdv = urun_kdv;
    }

    public Date getUrun_eklenme_tarih() {
        return urun_eklenme_tarih;
    }

    public void setUrun_eklenme_tarih(Date urun_eklenme_tarih) {
        this.urun_eklenme_tarih = urun_eklenme_tarih;
    }

    public Date getUrun_guncellenme_tarih() {
        return urun_guncellenme_tarih;
    }

    public void setUrun_guncellenme_tarih(Date urun_guncellenme_tarih) {
        this.urun_guncellenme_tarih = urun_guncellenme_tarih;
    }

    public boolean isUrun_durum() {
        return urun_durum;
    }

    public void setUrun_durum(boolean urun_durum) {
        this.urun_durum = urun_durum;
    }

    public Kategoriler getKategoriler() {
        return kategoriler;
    }

    public void setKategoriler(Kategoriler kategoriler) {
        this.kategoriler = kategoriler;
    }

    public Markalar getMarkalar() {
        return markalar;
    }

    public void setMarkalar(Markalar markalar) {
        this.markalar = markalar;
    }

    @Override
    public String toString() {
        return "Urunler{" + "urun_id=" + urun_id + ", urun_barkod=" + urun_barkod + ", urun_adi=" + urun_adi + ", urun_adet=" + urun_adet + ", urun_alis=" + urun_alis + ", urun_satis=" + urun_satis + ", urun_kdv=" + urun_kdv + ", urun_eklenme_tarih=" + urun_eklenme_tarih + ", urun_guncellenme_tarih=" + urun_guncellenme_tarih + ", urun_durum=" + urun_durum + ", kategoriler=" + kategoriler + ", markalar=" + markalar + '}';
    }

}
