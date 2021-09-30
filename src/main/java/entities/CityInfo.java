package entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NamedQuery(name = "CityInfo.deleteAllRows", query = "DELETE from CityInfo")
public class CityInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int zipcode;
    private String city;

    public CityInfo() {
    }

    public CityInfo(int zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}