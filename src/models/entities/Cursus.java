package models.entities;

import java.io.Serializable;

public class Cursus implements Serializable {
    private int id;
    private String naamCursus;
    private String startDatum;
    private String eindDatum;
    private int departementID;

    public int getDepartementID() {
        return departementID;
    }

    public void setDepartementID(int departementID) {
        this.departementID = departementID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaamCursus() {
        return naamCursus;
    }

    public void setNaamCursus(String naamCursus) {
        this.naamCursus = naamCursus;
    }

    public String getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    public String getEindDatum() {
        return eindDatum;
    }

    public void setEindDatum(String eindDatum) {
        this.eindDatum = eindDatum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cursus{");
        sb.append("id=").append(id);
        sb.append(", naamCursus='").append(naamCursus).append('\'');
        sb.append(", startDatum='").append(startDatum).append('\'');
        sb.append(", eindDatum='").append(eindDatum).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
