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
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ceyhunataykan
 */
@Entity
public class Fisler {

    @Id
    @SequenceGenerator(name = "FISLER_SEQUENCE_GENERATOR", sequenceName = "FISLER_SEQUENCE", initialValue = 1)
    @GeneratedValue(generator = "FISLER_SEQUENCE_GENERATOR")
    private int fis_id;
    private String fis_odeme_tur;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fis_tarih;
    private double fis_fiyat;

    public Fisler() {
    }

    public Fisler(int fis_id, String fis_odeme_tur, Date fis_tarih, double fis_fiyat) {
        this.fis_id = fis_id;
        this.fis_odeme_tur = fis_odeme_tur;
        this.fis_tarih = fis_tarih;
        this.fis_fiyat = fis_fiyat;
    }
    
    public int getFis_id() {
        return fis_id;
    }

    public void setFis_id(int fis_id) {
        this.fis_id = fis_id;
    }

    public String getFis_odeme_tur() {
        return fis_odeme_tur;
    }

    public void setFis_odeme_tur(String fis_odeme_tur) {
        this.fis_odeme_tur = fis_odeme_tur;
    }

    public Date getFis_tarih() {
        return fis_tarih;
    }

    public void setFis_tarih(Date fis_tarih) {
        this.fis_tarih = fis_tarih;
    }

    public double getFis_fiyat() {
        return fis_fiyat;
    }

    public void setFis_fiyat(double fis_fiyat) {
        this.fis_fiyat = fis_fiyat;
    }

    @Override
    public String toString() {
        return "Fisler{" + "fis_id=" + fis_id + ", fis_odeme_tur=" + fis_odeme_tur + ", fis_tarih=" + fis_tarih + ", fis_fiyat=" + fis_fiyat + '}';
    }

}
