package dao;

import java.io.Serializable;

public class ToegewezenCursus implements Serializable {
    private int cursusID;
    private int docentID;

    public int getCursusID() {
        return cursusID;
    }

    public void setCursusID(int cursusID) {
        this.cursusID = cursusID;
    }

    public int getDocentID() {
        return docentID;
    }

    public void setDocentID(int docentID) {
        this.docentID = docentID;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ToegewezenCursus{");
        sb.append("cursusID=").append(cursusID);
        sb.append(", docentID=").append(docentID);
        sb.append('}');
        return sb.toString();
    }
}
