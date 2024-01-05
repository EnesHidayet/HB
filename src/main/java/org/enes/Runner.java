package org.enes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.enes.entity.Adres;
import org.enes.entity.Secmen;
import org.enes.repository.AdresRepository;
import org.enes.repository.SecmenRepository;
import org.enes.utility.enums.Cinsiyet;

import java.util.List;
import java.util.Optional;

public class Runner {
    public static void main(String[] args) {

//        new SecmenRepository().save(new Secmen("Bahar","TAŞ","11111111123",Cinsiyet.KADIN));
////        new SecmenRepository().findAll().forEach(System.out::println);
//
//        new SecmenRepository().getAllTcKimlik().forEach(System.out::println);
//
//        Optional<Secmen> secmen=new SecmenRepository().findById(4L);
//        secmen.ifPresent(System.out::println);
//
//        new SecmenRepository().getTcKimlikAndName().forEach(o->{
//            System.out.println("ad...:"+o[1]+"  tc...:"+o[0]);
//        });
//
//        new SecmenRepository().getAllViewSecmen().forEach(System.out::println);
//
//        new SecmenRepository().getAdCount().forEach(o->{
//            System.out.println("ad...:"+o[0]+" adet...:"+o[1]);
//        });

//        new SecmenRepository().findAllNativeSQL().forEach(o->{
//            System.out.println("ad...:"+o[1]);
//        });

//        new SecmenRepository().findAllNativeSQLSecmen().forEach(System.out::println);

//        new SecmenRepository().findAllNamedQuery().forEach(System.out::println);


    }

    private static void extracted() {
        /**
         * SORU:
         * Secmenlerin tanımlandığı bir Entity sınıfı oluşturun ve seçmen ekleyin.
         * Secmen.java
         * [
         * id
         * tckimlik -> benzersiz
         * ad
         * soyad
         * cinsiyet -> enum
         * ]
         */
        Secmen secmen=new Secmen();
        secmen.setAd("Muhammet");
        secmen.setSoyad("HOCA");
        secmen.setTckimlik("1111111111");
        secmen.setCinsiyet(Cinsiyet.ERKEK);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM");
        EntityManager em = emf.createEntityManager();
        /**
         * Transaction nedir?
         * SQ -> 1.2.3.4.5.6.7.8.9
         */

        em.getTransaction().begin();
        em.persist(secmen);
        em.getTransaction().commit();
        em.close();
        emf.close();
    } // main içini boşaltmak için bunu metot haline getirdik kullanmıyoruz.
}
