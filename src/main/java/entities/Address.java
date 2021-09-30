package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "address")
@Entity
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String street;
    private int hoseNumber;

    public Address(String street, int hoseNumber) {
        this.street = street;
        this.hoseNumber = hoseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHoseNumber() {
        return hoseNumber;
    }

    public void setHoseNumber(int hoseNumber) {
        this.hoseNumber = hoseNumber;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}