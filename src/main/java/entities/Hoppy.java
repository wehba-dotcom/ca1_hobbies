package entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Hoppy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mame;
    private String description;

    public Hoppy(String mame, String description) {
        this.mame = mame;
        this.description = description;
    }

    public Hoppy(Long id, String mame, String description) {
        this.id = id;
        this.mame = mame;
        this.description = description;
    }

    public Hoppy() {

    }

    public String getMame() {
        return mame;
    }

    public void setMame(String mame) {
        this.mame = mame;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Hoppy{" +
                "id=" + id +
                ", mame='" + mame + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}