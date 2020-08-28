package com.ceyhunataykan.controller;

import com.ceyhunataykan.model.Markalar;
import com.ceyhunataykan.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class MarkaController {

    private static Session sessionObj;

    public static void markaEkle(String marka_adi) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen verileri entitylere ekliyoruz.
            Markalar marka = new Markalar();
            marka.setMarka_adi(marka_adi);
            sessionObj.save(marka);
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

    public static void markaGuncelle(int marka_id, String marka_adi) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili marka bulunuyor.
            Markalar marka = (Markalar) sessionObj.get(Markalar.class, marka_id);
            // Formdan gelen verileri entity'e ekliyoruz.
            marka.setMarka_adi(marka_adi);

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

    public static void markaSil(int marka_id) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili marka bulunuyor.
            Markalar marka = markaBul(marka_id);
            sessionObj.delete(marka);

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

    public static Markalar markaBul(int marka_id) {
        Markalar marka = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili marka bulunuyor.
            marka = (Markalar) sessionObj.load(Markalar.class, marka_id);
        } catch (HibernateException e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return marka;
    }

    public static int markaIdBul(String marka_adi) {
        Markalar marka = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            String QUERY = "from Markalar where marka_adi = '" + marka_adi + "'";
            // Formdan gelen marka adı ile ilgili markayı buluyoruz.
            Query sorgu = sessionObj.createQuery(QUERY);
            Iterator it = sorgu.iterate();
            while (it.hasNext()) {
                marka = (Markalar) it.next();
            }
        } catch (HibernateException e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return marka.getMarka_id();
    }

    //  Urunler formunda marka combobox için kullanılan fonksiyon.    
    public static List<Markalar> markaListe() {
        List<Markalar> markaListe = new ArrayList<>();

        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Veritabanında marka sorgusu yapılıyor
            markaListe = sessionObj.createQuery("from Markalar").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sessionObj.flush();
            sessionObj.close();
        }
        return markaListe;
    }

    public static DefaultTableModel markaGetir() {
        ArrayList<Markalar> liste;
        DefaultTableModel tm = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();

            String hql = "from Markalar";
            // Veritabanından markaları getiriyoruz.
            Query sorgu = sessionObj.createQuery(hql);
            Iterator it = sorgu.iterate();
            liste = new ArrayList();
            tm = new DefaultTableModel();            
            // Döngü ile veri tabanından çekilen verileri liste'ye ekliyoruz.
            while (it.hasNext()) {
                Markalar marka = (Markalar) it.next();

                liste.add(marka);
            }
            // Tablomuzun başlık kısımlarını belirliyoruz.
            tm.addColumn("Marka ID");
            tm.addColumn("Marka Adı");

            // Tablomuzda eklenecek verileri listeden seçiyoruz.
            for (int i = 0; i < liste.size(); i++) {
                tm.addRow(new Object[]{
                    liste.get(i).getMarka_id(),
                    liste.get(i).getMarka_adi()});
            }
            return tm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tm;
    }
}
