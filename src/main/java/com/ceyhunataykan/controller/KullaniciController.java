package com.ceyhunataykan.controller;

import com.ceyhunataykan.model.Kullanicilar;
import com.ceyhunataykan.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class KullaniciController {

    private static Session sessionObj;

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

    public static void kullaniciGuncelle(int kullanici_id, String ad, String soyad, String kullanici_adi, String kullanici_parola) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen verileri entitylere ekliyoruz.
            Kullanicilar kullanici = (Kullanicilar) sessionObj.get(Kullanicilar.class, kullanici_id);
            kullanici.setAd(ad);
            kullanici.setSoyad(soyad);
            kullanici.setKullanici_adi(kullanici_adi);
            kullanici.setKullanici_parola(kullanici_parola);
            kullanici.setRol("user");
            // Verileri veritabanına işliyoruz.
            sessionObj.getTransaction().commit();
        } catch (HibernateException sqlException) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    public static void kullaniciSil(int kullanici_id) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili kullanıcı bulunuyor.
            Kullanicilar kullanici = kullaniciBul(kullanici_id);
            sessionObj.delete(kullanici);

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

    public static Kullanicilar kullaniciBul(int kullanici_id) {
        Kullanicilar kullanici = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili kullanıcı bulunuyor.
            kullanici = (Kullanicilar) sessionObj.load(Kullanicilar.class, kullanici_id);
        } catch (HibernateException e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return kullanici;
    }

    public static DefaultTableModel kullaniciGetir() {
        ArrayList<Kullanicilar> liste;
        DefaultTableModel tm = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();

            String hql = "from Kullanicilar";
            // Veritabanından kullanıcıları getiriyoruz.
            Query sorgu = sessionObj.createQuery(hql);
            Iterator it = sorgu.iterate();
            liste = new ArrayList();
            tm = new DefaultTableModel();
             // Döngü ile veri tabanından çekilen verileri liste'ye ekliyoruz.
            while (it.hasNext()) {
                Kullanicilar kullanici = (Kullanicilar) it.next();

                liste.add(kullanici);
            }
            // Tablomuzun başlık kısımlarını belirliyoruz.
            tm.addColumn("Kullanici ID");
            tm.addColumn("Ad");
            tm.addColumn("Soyad");
            tm.addColumn("Kullanici Adı");
            tm.addColumn("Kullanıcı Parola");

            // Tablomuzda eklenecek verileri listeden seçiyoruz.
            for (int i = 0; i < liste.size(); i++) {
                tm.addRow(new Object[]{
                    liste.get(i).getKullanici_id(),
                    liste.get(i).getAd(),
                    liste.get(i).getSoyad(),
                    liste.get(i).getKullanici_adi(),
                    "********"
                });
            }
            return tm;
        } catch (Exception e) {
        }
        return tm;
    }
}
