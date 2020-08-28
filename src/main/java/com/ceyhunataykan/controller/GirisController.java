package com.ceyhunataykan.controller;

import com.ceyhunataykan.model.Kullanicilar;
import com.ceyhunataykan.util.HibernateUtil;
import org.hibernate.Session;

public class GirisController {

    private static Session sessionObj;

    public static void yoneticiEkle(Kullanicilar kullanici) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Veritabanı sorgusu ile ilgili kullanıcı sorgusu yapılıyor.
            Kullanicilar yonetici = (Kullanicilar) sessionObj.createQuery("FROM Kullanicilar U WHERE U.kullanici_adi = :kullanici_adi").setParameter("kullanici_adi", kullanici.getKullanici_adi())
                    .uniqueResult();

            // Gelen veri null ise kayıt ediliyor.
            if (yonetici == null) {
                sessionObj.save(kullanici);
                sessionObj.getTransaction().commit();
            }

        } catch (Exception e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public static void kullaniciEkle(Kullanicilar kullanici) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            sessionObj.save(kullanici);
            // Verileri veritabanına işliyoruz.
            sessionObj.getTransaction().commit();
        } catch (Exception e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public static boolean girisKontrol(String kullaniciAdi, String parola) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Giriş formundan gelen veriler ile veritabanı sorgusu yapılıyor.
            Kullanicilar kullanici = (Kullanicilar) sessionObj.createQuery("FROM Kullanicilar U WHERE U.kullanici_adi = :kullanici_adi").setParameter("kullanici_adi", kullaniciAdi)
                    .uniqueResult();

            // Sonuç başarılı ise true dönüyor.
            if (kullanici != null && kullanici.getKullanici_parola().equals(parola)) {
                return true;
            }

            // Verileri veritabanına işliyoruz.
            sessionObj.getTransaction().commit();
        } catch (Exception e) {

            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public static int girisYetkiliKontrol(String kullaniciAdi, String parola) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Giriş formundan gelen veriler ile veritabanı sorgusu yapılıyor.
            Kullanicilar kullanici = (Kullanicilar) sessionObj.createQuery("FROM Kullanicilar U WHERE U.kullanici_adi = :kullanici_adi").setParameter("kullanici_adi", kullaniciAdi)
                    .uniqueResult();

             // Sonuç başarılı ve rol admin ise 1, user ise 2 dönüyor. Giriş yapan kullanıcının bilgileri MevcutKullanıcı classında değişkenlerde tutuluyor.
            if (kullanici != null && kullanici.getKullanici_parola().equals(parola)) {
                if (kullanici.getRol().equals("admin")) {
                    MevcutKullanici.id = kullanici.getKullanici_id();
                    MevcutKullanici.kullanici_adi = kullanici.getKullanici_adi();
                    MevcutKullanici.rol = "admin";
                    return 1;
                } else if (kullanici.getRol().equals("user")) {
                    MevcutKullanici.id = kullanici.getKullanici_id();
                    MevcutKullanici.kullanici_adi = kullanici.getKullanici_adi();
                    MevcutKullanici.rol = "user";
                    return 2;
                }
            }
             // Verileri veritabanına işliyoruz.
            sessionObj.getTransaction().commit();
        } catch (Exception e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return 0;
    }
}
