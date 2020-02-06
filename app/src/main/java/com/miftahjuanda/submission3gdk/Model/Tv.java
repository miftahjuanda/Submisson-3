package com.miftahjuanda.submission3gdk.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Tv extends RootModel<TvData> implements Parcelable {

    public Tv() {
    }

    protected Tv(Parcel in) {
    }

    public static final Creator<Tv> CREATOR = new Creator<Tv>() {
        @Override
        public Tv createFromParcel(Parcel in) {
            return new Tv(in);
        }

        @Override
        public Tv[] newArray(int size) {
            return new Tv[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
