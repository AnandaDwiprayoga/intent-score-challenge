package id.putraprima.skorbola;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class TeamModel implements Parcelable {
    public String namaTeamHome;
    public String namaTeamAway;
    public Bitmap logoTeamHome;
    public Bitmap logoTeamAway;

    public TeamModel(String namaTeamHome, String namaTeamAway, Bitmap logoTeamHome, Bitmap logoTeamAway) {
        this.namaTeamHome = namaTeamHome;
        this.namaTeamAway = namaTeamAway;
        this.logoTeamHome = logoTeamHome;
        this.logoTeamAway = logoTeamAway;
    }

    protected TeamModel(Parcel in) {
        namaTeamHome = in.readString();
        namaTeamAway = in.readString();
        logoTeamHome = in.readParcelable(Bitmap.class.getClassLoader());
        logoTeamAway = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<TeamModel> CREATOR = new Creator<TeamModel>() {
        @Override
        public TeamModel createFromParcel(Parcel in) {
            return new TeamModel(in);
        }

        @Override
        public TeamModel[] newArray(int size) {
            return new TeamModel[size];
        }
    };

    public String getNamaTeamHome() {
        return namaTeamHome;
    }

    public void setNamaTeamHome(String namaTeamHome) {
        this.namaTeamHome = namaTeamHome;
    }

    public String getNamaTeamAway() {
        return namaTeamAway;
    }

    public void setNamaTeamAway(String namaTeamAway) {
        this.namaTeamAway = namaTeamAway;
    }

    public Bitmap getLogoTeamHome() {
        return logoTeamHome;
    }

    public void setLogoTeamHome(Bitmap logoTeamHome) {
        this.logoTeamHome = logoTeamHome;
    }

    public Bitmap getLogoTeamAway() {
        return logoTeamAway;
    }

    public void setLogoTeamAway(Bitmap logoTeamAway) {
        this.logoTeamAway = logoTeamAway;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(namaTeamHome);
        dest.writeString(namaTeamAway);
        dest.writeParcelable(logoTeamHome, flags);
        dest.writeParcelable(logoTeamAway, flags);
    }
}
