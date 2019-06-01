package dao;

import java.io.Serializable;

public class Docent implements Serializable {
    private int id;
    private String voorNaam;
    private String achterNaam;
    private int departementID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    public void setVoorNaam(String voorNaam) {
        this.voorNaam = voorNaam;
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public void setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
    }

    public int getDepartementID() {
        return departementID;
    }

    public void setDepartementID(int departementID) {
        this.departementID = departementID;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Docent{");
        sb.append("id=").append(id);
        sb.append(", voorNaam='").append(voorNaam).append('\'');
        sb.append(", achterNaam='").append(achterNaam).append('\'');
        sb.append(", departementID=").append(departementID);
        sb.append('}');
        return sb.toString();
    }
}
