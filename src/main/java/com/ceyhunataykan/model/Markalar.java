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
@Entity(name = "Markalar")
public class Markalar {

    @Id
    @SequenceGenerator(name = "MARKALAR_SEQUENCE_GENERATOR", sequenceName = "MARKALAR_SEQUENCE", initialValue = 1)
    @GeneratedValue(generator = "MARKALAR_SEQUENCE_GENERATOR")
    private int marka_id;
    private String marka_adi;

    public Markalar(){
        
    }
    
    public Markalar(int marka_id, String marka_adi) {
        this.marka_id = marka_id;
        this.marka_adi = marka_adi;
    }

    public int getMarka_id() {
        return marka_id;
    }

    public void setMarka_id(int marka_id) {
        this.marka_id = marka_id;
    }

    public String getMarka_adi() {
        return marka_adi;
    }

    public void setMarka_adi(String marka_adi) {
        this.marka_adi = marka_adi;
    }

    @Override
    public String toString() {
        return "Markalar{" + "marka_id=" + marka_id + ", marka_adi=" + marka_adi + '}';
    }
    
}
