package com.ceyhunataykan.controller;

import com.ceyhunataykan.model.Fisler;
import com.ceyhunataykan.util.HibernateUtil;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class FisController {

    private static Session sessionObj;

    public static void fisEkle(Fisler fis) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen verileri entitylere ekliyoruz.
            sessionObj.save(fis);
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

    public static void fisGuncelle(int fis_id, double toplamFiyat) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili fis bulunuyor.
            Fisler fis = (Fisler) sessionObj.get(Fisler.class, fis_id);
            // Fiyat güncelleniyor.
            fis.setFis_fiyat(toplamFiyat);

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

    public static Fisler fisBul() {
        Fisler fis = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();

            String QUERY = "FROM Fisler ORDER BY fis_id DESC";
            //Veritabanı sorgusu ile son fis bulunuyor.
            Query sorgu = sessionObj.createQuery(QUERY);
            sorgu.setMaxResults(1);
            fis = (Fisler) sorgu.uniqueResult();

        } catch (HibernateException e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return fis;
    }

    public static DefaultTableModel fisFiltreGetir(int filtre) {
        LocalDate oneday = LocalDate.now().minusDays(1);
        LocalDate threeday = LocalDate.now().minusDays(3);
        LocalDate oneweek = LocalDate.now().minusDays(7);
        LocalDate onemonth = LocalDate.now().minusDays(30);

        // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
        sessionObj = HibernateUtil.getSessionFactory().openSession();

        String QUERY = "from Fisler";

        // Formdan gelen filtre bilgisine göre ilgili case çalışıyor.
        switch (filtre) {
            case 1: {
                QUERY = "from Fisler where fis_tarih > '" + oneday.toString() + "'";
                break;
            }
            case 2: {
                QUERY = "from Fisler where fis_tarih > '" + threeday.toString() + "'";
                break;
            }
            case 3: {
                QUERY = "from Fisler where fis_tarih > '" + oneweek.toString() + "'";
                break;
            }
            case 4: {
                QUERY = "from Fisler where fis_tarih > '" + onemonth.toString() + "'";
                break;
            }
            default:
                break;
        }

        Query sorgu = sessionObj.createQuery(QUERY);
        Iterator it = sorgu.iterate();
        ArrayList<Fisler> liste = new ArrayList();
        DefaultTableModel tm = new DefaultTableModel();
        // Döngü ile veri tabanından çekilen verileri liste'ye ekliyoruz.
        while (it.hasNext()) {
            Fisler fis = (Fisler) it.next();

            liste.add(fis);
        }
        // Tablomuzun başlık kısımlarını belirliyoruz.
        tm.addColumn("Fiş No");
        tm.addColumn("Ödeme Türü");
        tm.addColumn("Fis Tutarı");
        tm.addColumn("Fis Tarihi");

        // Tablomuzda eklenecek verileri listeden seçiyoruz.
        for (int i = 0; i < liste.size(); i++) {
            tm.addRow(new Object[]{
                liste.get(i).getFis_id(),
                liste.get(i).getFis_odeme_tur(),
                (float) liste.get(i).getFis_fiyat(),
                liste.get(i).getFis_tarih(),});
        }
        return tm;
    }

    public static DefaultTableModel fisGetir() {
        ArrayList<Fisler> liste;
        DefaultTableModel tm = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();

            String QUERY = "from Fisler";
            // Veritabanından fisleri getiriyoruz.
            Query sorgu = sessionObj.createQuery(QUERY);
            Iterator it = sorgu.iterate();
            liste = new ArrayList();
            tm = new DefaultTableModel();
            // Döngü ile veri tabanından çekilen verileri liste'ye ekliyoruz.
            while (it.hasNext()) {
                Fisler fis = (Fisler) it.next();

                liste.add(fis);
            }
            // Tablomuzun başlık kısımlarını belirliyoruz.
            tm.addColumn("Fiş No");
            tm.addColumn("Ödeme Türü");
            tm.addColumn("Fis Tutarı");
            tm.addColumn("Fis Tarihi");

            // Tablomuzda eklenecek verileri listeden seçiyoruz.
            for (int i = 0; i < liste.size(); i++) {
                tm.addRow(new Object[]{
                    liste.get(i).getFis_id(),
                    liste.get(i).getFis_odeme_tur(),
                    (float) liste.get(i).getFis_fiyat(),
                    liste.get(i).getFis_tarih(),});
            }
            return tm;
        } catch (Exception e) {
        }
        return tm;
    }
}
