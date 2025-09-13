package aModels;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Activite implements Parcelable {

    public String date;
    public String titre;
    public String lieu;
    public static enum Stade {DOMICILE, EXTERIEUR};
    public Stade stade;
    public int confirmation;

    public Activite(String date, String titre, String lieu, Stade stade, int confirmation) {
        this.date = date;
        this.titre = titre;
        this.lieu = lieu;
        this.stade = stade;
        this.confirmation = confirmation;
    }

    protected Activite(Parcel in) {
        date = in.readString();
        titre = in.readString();
        lieu = in.readString();
        stade = Stade.valueOf(in.readString());
        confirmation = in.readInt();
    }

    public static final Creator<Activite> CREATOR = new Creator<Activite>() {
        @Override
        public Activite createFromParcel(Parcel in) {
            return new Activite(in);
        }

        @Override
        public Activite[] newArray(int size) {
            return new Activite[size];
        }
    };

    public String getDate() {
        return date;
    }

    public String getTitre() {
        return titre;
    }

    public String getLieu() {
        return lieu;
    }

    public Stade getStade() {
        return stade;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    @Override
    public String toString() {
        return titre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(titre);
        dest.writeString(lieu);
        dest.writeString(stade.name());
        dest.writeInt(confirmation);
    }


}
