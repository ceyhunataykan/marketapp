package com.ceyhunataykan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "Kategoriler")
public class Kategoriler{

    @Id
    @SequenceGenerator(name = "KATEGORILER_SEQUENCE_GENERATOR", sequenceName = "KATEGORILER_SEQUENCE", initialValue = 1)
    @GeneratedValue(generator = "KATEGORILER_SEQUENCE_GENERATOR")
    private int kategori_id;
    private String kategori_adi;

    public int getKategori_id() {
        return kategori_id;
    }

    public void setKategori_id(int kategori_id) {
        this.kategori_id = kategori_id;
    }

    public String getKategori_adi() {
        return kategori_adi;
    }

    public void setKategori_adi(String kategori_adi) {
        this.kategori_adi = kategori_adi;
    }

    @Override
    public String toString() {
        return "Kategoriler{" + "kategori_id=" + kategori_id + ", kategori_adi=" + kategori_adi + '}';
    }

}
