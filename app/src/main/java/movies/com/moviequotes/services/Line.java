package movies.com.moviequotes.services;


import android.os.Parcel;
import android.os.Parcelable;

public class Line implements Parcelable {
    private String character;
    private String text;

    public Line(String character, String text) {
        this.character = character;
        this.text = text;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Parcelling part
    public Line(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.character = data[0];
        this.text = data[1];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.character,
                this.text});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Line createFromParcel(Parcel in) {
            return new Line(in);
        }

        public Line[] newArray(int size) {
            return new Line[size];
        }
    };
}
