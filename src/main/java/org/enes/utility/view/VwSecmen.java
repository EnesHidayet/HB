package org.enes.utility.view;

public class VwSecmen {
    String ad;
    String tckimlik;

    public VwSecmen(String ad, String tckimlik) {
        this.ad = ad;
        this.tckimlik = tckimlik;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getTckimlik() {
        return tckimlik;
    }

    public void setTckimlik(String tckimlik) {
        this.tckimlik = tckimlik;
    }

    @Override
    public String toString() {
        return "VwSecmen{" +
                "ad='" + ad + '\'' +
                ", tckimlik='" + tckimlik + '\'' +
                '}';
    }
}
