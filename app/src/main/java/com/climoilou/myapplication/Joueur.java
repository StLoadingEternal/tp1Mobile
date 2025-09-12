package com.climoilou.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Joueur implements Parcelable {

    private String nom;
    private String prenom;
    private String addresseCourriel;


    public static final Creator<Joueur> CREATOR = new Creator<Joueur>() {
        @Override
        public Joueur createFromParcel(Parcel in) {
            return new Joueur(in);
        }

        @Override
        public Joueur[] newArray(int size) {
            return new Joueur[size];
        }
    };

    public Joueur(String nom, String prenom, String addresseCourriel) {
        this.nom = nom;
        this.prenom = prenom;
        this.addresseCourriel = addresseCourriel;
    }

    protected Joueur(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        addresseCourriel = in.readString();
    }


    @Override
    public String toString() {
        return "Joueur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", addresseCourriel='" + addresseCourriel + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAddresseCourriel() {
        return addresseCourriel;
    }

    public void setAddresseCourriel(String addresseCourriel) {
        this.addresseCourriel = addresseCourriel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.nom);
        parcel.writeString(this.prenom);
        parcel.writeString(this.addresseCourriel);

    }
}
