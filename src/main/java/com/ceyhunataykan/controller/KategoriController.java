package com.ceyhunataykan.controller;

import com.ceyhunataykan.model.Kategoriler;
import com.ceyhunataykan.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class KategoriController {

    private static Session sessionObj;

    public static void kategoriEkle(String kategori_adi) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen verileri entitylere ekliyoruz.
            Kategoriler kategori = new Kategoriler();
            kategori.setKategori_adi(kategori_adi);
            sessionObj.save(kategori);
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

    public static void kategoriGuncelle(int kategori_id, String kategori_adi) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili kategori bulunuyor.
            Kategoriler kategori = (Kategoriler) sessionObj.get(Kategoriler.class, kategori_id);
            // Formdan gelen verileri entity'e ekliyoruz.
            kategori.setKategori_adi(kategori_adi);

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

    public static void kategoriSil(int kategori_id) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili kategori bulunuyor.
            Kategoriler kategori = kategoriBul(kategori_id);
            sessionObj.delete(kategori);

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

    public static Kategoriler kategoriBul(int kategori_id) {
        Kategoriler kategori = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili marka bulunuyor.
            kategori = (Kategoriler) sessionObj.load(Kategoriler.class, kategori_id);
        } catch (HibernateException e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return kategori;
    }

    public static int kategoriIdBul(String kategori_adi) {
        Kategoriler kategori = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            String QUERY = "from Kategoriler where kategori_adi = '" + kategori_adi + "'";
            // Formdan gelen marka adı ile ilgili markayı buluyoruz.
            Query sorgu = sessionObj.createQuery(QUERY);
            Iterator it = sorgu.iterate();
            while (it.hasNext()) {
                kategori = (Kategoriler) it.next();
            }
        } catch (HibernateException e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return kategori.getKategori_id();
    }

    //  Urunler formunda kategori combobox için kullanılan fonksiyon. 
    public static List<Kategoriler> kategoriListe() {
        List<Kategoriler> kategoriListe = new ArrayList<>();

        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Veritabanında kategori sorgusu yapılıyor
            kategoriListe = sessionObj.createQuery("from Kategoriler").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sessionObj.flush();
            sessionObj.close();
        }
        return kategoriListe;
    }

    public static DefaultTableModel kategoriGetir() {
        ArrayList<Kategoriler> liste;
        DefaultTableModel tm = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();

            String QUERY = "from Kategoriler";
            // Veritabanından kategorileri getiriyoruz.
            Query sorgu = sessionObj.createQuery(QUERY);
            Iterator it = sorgu.iterate();
            liste = new ArrayList();
            tm = new DefaultTableModel();
            // Döngü ile veri tabanından çekilen verileri liste'ye ekliyoruz.
            while (it.hasNext()) {
                Kategoriler kategori = (Kategoriler) it.next();

                liste.add(kategori);
            }
            // Tablomuzun başlık kısımlarını belirliyoruz.
            tm.addColumn("Kategori ID");
            tm.addColumn("Kategori Adı");

            // Tablomuzda eklenecek verileri listeden seçiyoruz.
            for (int i = 0; i < liste.size(); i++) {
                tm.addRow(new Object[]{
                    liste.get(i).getKategori_id(),
                    liste.get(i).getKategori_adi()});
            }
            return tm;
        } catch (Exception e) {
        }
        return tm;
    }
}
