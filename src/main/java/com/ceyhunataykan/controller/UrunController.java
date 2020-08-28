package com.ceyhunataykan.controller;

import com.ceyhunataykan.model.Kategoriler;
import com.ceyhunataykan.model.Markalar;
import com.ceyhunataykan.model.Urunler;
import com.ceyhunataykan.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class UrunController {

    private static Session sessionObj;

    public static void urunEkle(String urun_barkod, String urun_adi, int urun_adet, double urun_alis, double urun_satis, int urun_kdv, Kategoriler kategoriler, Markalar markalar) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen verileri entitylere ekliyoruz.
            Urunler urun = new Urunler();
            urun.setUrun_barkod(urun_barkod);
            urun.setUrun_adi(urun_adi);
            urun.setKategoriler(kategoriler);
            urun.setMarkalar(markalar);
            urun.setUrun_alis(urun_alis);
            urun.setUrun_satis(urun_satis);
            urun.setUrun_kdv(urun_kdv);
            urun.setUrun_adet(urun_adet);
            urun.setUrun_eklenme_tarih(new Date());
            urun.setUrun_durum(true);
            sessionObj.save(urun);
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

    public static void urunGuncelle(int urun_id, String urun_barkod, String urun_adi, int urun_adet, double urun_alis, double urun_satis, int urun_kdv, Kategoriler kategoriler, Markalar markalar) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili ürün bulunuyor.
            Urunler urun = (Urunler) sessionObj.get(Urunler.class, urun_id);
            // Formdan gelen verileri entity'e ekliyoruz.
            urun.setUrun_barkod(urun_barkod);
            urun.setUrun_adi(urun_adi);
            urun.setKategoriler(kategoriler);
            urun.setMarkalar(markalar);
            urun.setUrun_alis(urun_alis);
            urun.setUrun_satis(urun_satis);
            urun.setUrun_kdv(urun_kdv);
            urun.setUrun_adet(urun_adet);
            urun.setUrun_guncellenme_tarih(new Date());

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

    public static void urunSil(int urun_id) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();
          
            // Formdan gelen id ile ilgili ürün bulunuyor.
            Urunler urun = urunBul(urun_id);
            // Ürün satışlar ve fiş ile bağlantılı olduğu için tam olarak silmiyoruz.
            urun.setUrun_barkod("");
            urun.setUrun_durum(false);

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

    public static void urunAdetGuncelle(int urun_id, int urun_adet) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili ürün bulunuyor.
            Urunler urun = (Urunler) sessionObj.get(Urunler.class, urun_id);
            // Urun adedi güncelleniyor.
            urun.setUrun_adet(urun_adet);
            // Verileri veritabanına işliyoruz.
            sessionObj.getTransaction().commit();
        } catch (HibernateException e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public static Urunler urunBul(int urun_id) {
        Urunler urun = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen id ile ilgili ürün bulunuyor.
            urun = (Urunler) sessionObj.load(Urunler.class, urun_id);
        } catch (HibernateException e) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return urun;
    }

    public static int urunIdBul(String urun_barkod) {
        Urunler urun = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            String QUERY = "from Urunler where urun_barkod = '" + urun_barkod + "'";

            // Formdan gelen barkod ile ilgili urunu buluyoruz.
            Query sorgu = sessionObj.createQuery(QUERY);
            Iterator it = sorgu.iterate();
            
            // Döngü yardımıyla gelen verileri listeye ekliyoruz.
            while (it.hasNext()) {
                urun = (Urunler) it.next();
            }
        } catch (HibernateException e) {
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return urun.getUrun_id();
    }

    public static DefaultTableModel urunGetir() {
        ArrayList<Urunler> liste;
        DefaultTableModel tm = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz.
            sessionObj = HibernateUtil.getSessionFactory().openSession();

            String hql = "from Urunler where urun_durum = 1";
            // Veritabanından aktif olan ürünleri getiriyoruz.
            Query sorgu = sessionObj.createQuery(hql);
            Iterator it = sorgu.iterate();
            liste = new ArrayList();
            tm = new DefaultTableModel();
            // Döngü ile veri tabanından çekilen verileri liste'ye ekliyoruz.
            while (it.hasNext()) {
                Urunler urun = (Urunler) it.next();

                liste.add(urun);
            }
            // Tablomuzun başlık kısımlarını belirliyoruz.
            tm.addColumn("ID");
            tm.addColumn("Barkod");
            tm.addColumn("Urun Adı");
            tm.addColumn("Eklenme Tarihi");
            tm.addColumn("Marka");
            tm.addColumn("Kategori");
            tm.addColumn("Stok Adet");
            tm.addColumn("Alış Fiyatı");
            tm.addColumn("Satış Fiyatı");
            tm.addColumn("KDV");

            // Tablomuzda eklenecek verileri listeden seçiyoruz.
            for (int i = 0; i < liste.size(); i++) {
                tm.addRow(new Object[]{
                    liste.get(i).getUrun_id(),
                    liste.get(i).getUrun_barkod(),
                    liste.get(i).getUrun_adi(),
                    liste.get(i).getUrun_eklenme_tarih(),
                    liste.get(i).getMarkalar().getMarka_adi(),
                    liste.get(i).getKategoriler().getKategori_adi(),
                    liste.get(i).getUrun_adet(),
                    liste.get(i).getUrun_alis(),
                    liste.get(i).getUrun_satis(),
                    liste.get(i).getUrun_kdv()
                });
            }
            return tm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tm;
    }
}
