package com.ceyhunataykan.controller;

import com.ceyhunataykan.util.HibernateUtil;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;

public class RaporController {

    private static Session sessionObj;

    public static DefaultTableModel gunlukSatis() {
        // Sayıların formatlı bir biçimde yazılması için DeCimalFormat sınıfını kullanıyoruz.
        DecimalFormat df = new DecimalFormat("#0.00");

        DefaultTableModel tm = null;
        try {
            // Veritabanı bağlantımızı açarak sessionumuzu başlatıyoruz. 
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            // Session üzerinden transaction işlemini başlatıyoruz.
            sessionObj.beginTransaction();

            // İlgili rapora göre veritabanı sorgusu yapılıor. Veriler listeye ekleniyor.
            String hql = "select cast(satis_tarih as date) as Tarih, count(*) as ToplamSatis, sum(satis_adet) as ToplamAdet, sum(satis_fiyat) as ToplamTutar from Satislar group by cast(satis_tarih as date)";
            List<?> liste = sessionObj.createQuery(hql).list();
            String hql2 = "select cast(fis_tarih as date), count(fis_id) from Fisler group by CAST(fis_tarih as date)";
            List<?> liste2 = sessionObj.createQuery(hql2).list();
            
            tm = new DefaultTableModel();
            
            
            // Tablomuzun başlık kısımlarını belirliyoruz.
            tm.addColumn("Tarih");
            tm.addColumn("Toplam Fiş");
            tm.addColumn("Toplam Satılan Ürün Adedi");
            tm.addColumn("Toplam Tutar");

            // Tablomuzda eklenecek verileri listeden seçiyoruz.
            for (int i = 0; i < liste.size(); i++) {
                Object[] row = (Object[]) liste.get(i);
                Object[] row2 = (Object[]) liste2.get(i);
                tm.addRow(new Object[]{
                    row[0],
                    row2[1],
                    row[2],
                    df.format(row[3])
                });
            }
            return tm;

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sessionObj.flush();
            sessionObj.close();
        }
        return tm;
    }
}
