package com.ceyhunataykan.controller;

import com.ceyhunataykan.model.Fisler;
import com.ceyhunataykan.model.Kullanicilar;
import com.ceyhunataykan.model.Satislar;
import com.ceyhunataykan.model.Urunler;
import com.ceyhunataykan.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class SatisController {

    private static Session sessionObj;

    public static void satisEkle(int satis_adet, double satis_birimfiyat, double satis_fiyat, Urunler urunler, Date satis_tarih, Fisler fisler, Kullanicilar kullanicilar) {
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // Formdan gelen verileri entitylere ekliyoruz.
            Satislar satis = new Satislar();
            satis.setSatis_adet(satis_adet);
            satis.setSatis_birimfiyat(satis_birimfiyat);
            satis.setSatis_fiyat(satis_fiyat);
            satis.setUrunler(urunler);
            satis.setSatis_tarih(satis_tarih);
            satis.setFisler(fisler);
            satis.setKullanicilar(kullanicilar);
            
            sessionObj.save(satis);
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

    public static DefaultTableModel satisGetir() {
        ArrayList<Satislar> liste;
        DefaultTableModel tm = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();

            String hql = "from Satislar";
            // Veritabanından satışları getiriyoruz.
            Query sorgu = sessionObj.createQuery(hql);
            Iterator it = sorgu.iterate();
            liste = new ArrayList();
            tm = new DefaultTableModel();
            // Döngü ile veri tabanından çekilen verileri liste'ye ekliyoruz.
            while (it.hasNext()) {
                Satislar satis = (Satislar) it.next();

                liste.add(satis);
            }           
            // Tablomuzun başlık kısımlarını belirliyoruz.
            tm.addColumn("Barkod");
            tm.addColumn("Ürün Adı");
            tm.addColumn("Adet");
            tm.addColumn("Birim Fiyat");
            tm.addColumn("Toplam Tutar");

            // Tablomuzda eklenecek verileri listeden seçiyoruz.
            for (int i = 0; i < liste.size(); i++) {
                tm.addRow(new String[]{
                    liste.get(i).getUrunler().getUrun_barkod(),
                    liste.get(i).getUrunler().getUrun_adi(),
                    String.valueOf(liste.get(i).getSatis_adet()),
                    String.valueOf(liste.get(i).getSatis_birimfiyat()),
                    String.valueOf(liste.get(i).getSatis_fiyat())
                });
            }
            return tm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tm;
    }
}
