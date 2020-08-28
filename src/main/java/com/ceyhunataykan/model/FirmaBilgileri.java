package com.ceyhunataykan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class FirmaBilgileri {

    @Id
    @SequenceGenerator(name = "FIRMABILGILERI_SEQUENCE_GENERATOR", sequenceName = "FIRMABILGILERI_SEQUENCE", initialValue = 1)
    @GeneratedValue(generator = "FIRMABILGILERI_SEQUENCE_GENERATOR")
    private int firma_id;
    private String firma_unvani;
    private String firma_vergiD;
    private String firma_vergiN;
    private String firma_tel;
    private String firma_adres;
    private String firma_ilce;
    private String firma_il;

    public FirmaBilgileri() {
    }

    public FirmaBilgileri(String firma_unvani, String firma_vergiD, String firma_vergiN, String firma_tel, String firma_adres, String firma_ilce, String firma_il) {
        this.firma_unvani = firma_unvani;
        this.firma_vergiD = firma_vergiD;
        this.firma_vergiN = firma_vergiN;
        this.firma_tel = firma_tel;
        this.firma_adres = firma_adres;
        this.firma_ilce = firma_ilce;
        this.firma_il = firma_il;
    }
    
    public int getFirma_id() {
        return firma_id;
    }

    public void setFirma_id(int firma_id) {
        this.firma_id = firma_id;
    }

    public String getFirma_unvani() {
        return firma_unvani;
    }

    public void setFirma_unvani(String firma_unvani) {
        this.firma_unvani = firma_unvani;
    }

    public String getFirma_vergiD() {
        return firma_vergiD;
    }

    public void setFirma_vergiD(String firma_vergiD) {
        this.firma_vergiD = firma_vergiD;
    }

    public String getFirma_vergiN() {
        return firma_vergiN;
    }

    public void setFirma_vergiN(String firma_vergiN) {
        this.firma_vergiN = firma_vergiN;
    }

    public String getFirma_tel() {
        return firma_tel;
    }

    public void setFirma_tel(String firma_tel) {
        this.firma_tel = firma_tel;
    }

    public String getFirma_adres() {
        return firma_adres;
    }

    public void setFirma_adres(String firma_adres) {
        this.firma_adres = firma_adres;
    }

    public String getFirma_ilce() {
        return firma_ilce;
    }

    public void setFirma_ilce(String firma_ilce) {
        this.firma_ilce = firma_ilce;
    }

    public String getFirma_il() {
        return firma_il;
    }

    public void setFirma_il(String firma_il) {
        this.firma_il = firma_il;
    }

}
