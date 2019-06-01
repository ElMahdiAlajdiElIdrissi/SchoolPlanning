package dao;

import java.io.Serializable;

public class Departement implements Serializable {
    private int id;
    private String departementNaam;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartementNaam() {
        return departementNaam;
    }

    public void setDepartementNaam(String departementNaam) {
        this.departementNaam = departementNaam;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Departement{");
        sb.append("id=").append(id);
        sb.append(", departementNaam='").append(departementNaam).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
