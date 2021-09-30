package entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Phone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String information;

    public Phone(String number, String information) {
        this.number = number;
        this.information = information;
    }

    public Phone() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}