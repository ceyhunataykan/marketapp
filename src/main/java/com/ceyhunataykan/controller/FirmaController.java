package com.ceyhunataykan.controller;

import com.ceyhunataykan.model.FirmaBilgileri;
import com.ceyhunataykan.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class FirmaController {

    private static Session sessionObj;

    public static void firmaBilgileriEkle(String firma_unvani, String firma_vergiD, String firma_vergiN, String firma_tel, String firma_adres, String firma_ilce, String firma_il) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen verileri entitylere ekliyoruz.
            FirmaBilgileri firma = new FirmaBilgileri();
            firma.setFirma_unvani(firma_unvani);
            firma.setFirma_vergiD(firma_vergiD);
            firma.setFirma_vergiN(firma_vergiN);
            firma.setFirma_tel(firma_tel);
            firma.setFirma_adres(firma_adres);
            firma.setFirma_ilce(firma_ilce);
            firma.setFirma_il(firma_il);

            sessionObj.save(firma);
            // Verileri veritabanına işliyoruz.
            sessionObj.getTransaction().commit();
        } catch (HibernateException e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    public static void firmaBilgileriGuncelle(int firma_id, String firma_unvani, String firma_vergiD, String firma_vergiN, String firma_tel, String firma_adres, String firma_ilce, String firma_il) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili kategori bulunuyor.
            FirmaBilgileri firma = (FirmaBilgileri) sessionObj.get(FirmaBilgileri.class, firma_id);
            // Formdan gelen verileri entity'e ekliyoruz.
            firma.setFirma_unvani(firma_unvani);
            firma.setFirma_vergiD(firma_vergiD);
            firma.setFirma_vergiN(firma_vergiN);
            firma.setFirma_tel(firma_tel);
            firma.setFirma_adres(firma_adres);
            firma.setFirma_ilce(firma_ilce);
            firma.setFirma_il(firma_il);

            // Verileri veritabanına işliyoruz.
            sessionObj.getTransaction().commit();
        } catch (HibernateException e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    public static FirmaBilgileri firmaBilgileriGetir() {
        FirmaBilgileri firma = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();

            String QUERY = "FROM FirmaBilgileri ORDER BY firma_id DESC";
            // Veritabanından tek kayıt getiriliyor.
            Query sorgu = sessionObj.createQuery(QUERY);
            sorgu.setMaxResults(1);
            firma = (FirmaBilgileri) sorgu.uniqueResult();

        } catch (HibernateException sqlException) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
        return firma;
    }
}
