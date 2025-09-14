package aModels;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Joueur implements Parcelable {

    private String nom;
    private String prenom;
    private String addresseCourriel;
    private String contact;

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

    public Joueur(String nom, String prenom, String addresseCourriel, String contact) {
        this.nom = nom;
        this.prenom = prenom;
        this.addresseCourriel = addresseCourriel;
        this.contact = contact;
    }

    protected Joueur(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        addresseCourriel = in.readString();
        contact = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return Objects.equals(contact, joueur.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(contact);
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
        parcel.writeString(this.contact);

    }
}
