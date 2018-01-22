package beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ismael on 22/01/2018.
 * Classe que representa o objeto professor retornado pelo Endpoint do spring
 */

public class Professor implements Parcelable {
    private long id;
    private String nome;

    public Professor(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private Professor(Parcel in){
        this.setId(in.readLong());
        this.setNome(in.readString());
    }


    public static final Parcelable.Creator<Professor> CREATOR = new Creator<Professor>() {
        @Override
        public Professor createFromParcel(Parcel source) {
            return new Professor(source);
        }

        @Override
        public Professor[] newArray(int size) {
            return new Professor[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(this.getId() != 0){
            dest.writeByte((byte) 1);
            dest.writeLong(this.getId());
        }
        else{
            dest.writeByte((byte) 0);
        }
        if(this.getNome() != null){
            dest.writeByte((byte) 1);
            dest.writeString(this.getNome());
        }
        else{
            dest.writeByte((byte)0);
        }
    }
}
