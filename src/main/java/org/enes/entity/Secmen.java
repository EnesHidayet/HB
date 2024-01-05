package org.enes.entity;

import jakarta.persistence.*;
import org.enes.utility.enums.Cinsiyet;
@NamedQueries({
        @NamedQuery(name = "Secmen.findAll", query = "FROM Secmen s"), // HQL
        @NamedQuery(name = "Secmen.findById", query = "SELECT s FROM Secmen s WHERE s.id = :id") // JPQL
})

@Entity
@Table(name = "tbl_secmen")
public class Secmen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ad;
    String soyad;
    @Column(unique = true,nullable = false,length = 11)
    String tckimlik;
    @Enumerated(EnumType.STRING)
    Cinsiyet cinsiyet;

    public Secmen() {
    }

    public Secmen(String ad, String soyad, String tckimlik, Cinsiyet cinsiyet) {
        this.ad = ad;
        this.soyad = soyad;
        this.tckimlik = tckimlik;
        this.cinsiyet = cinsiyet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTckimlik() {
        return tckimlik;
    }

    public void setTckimlik(String tckimlik) {
        this.tckimlik = tckimlik;
    }

    public Cinsiyet getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(Cinsiyet cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    @Override
    public String toString() {
        return "Secmen{" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", soyad='" + soyad + '\'' +
                ", tckimlik='" + tckimlik + '\'' +
                ", cinsiyet=" + cinsiyet +
                '}';
    }
}
