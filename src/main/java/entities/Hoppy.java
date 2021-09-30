package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@NamedQuery(name = "Hoppy.deleteAllRows", query = "DELETE from Hoppy ")
public class Hoppy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToMany(mappedBy = "hoppyList",cascade=CascadeType.PERSIST)
    private List<Person> personList;
    public void addPerson(Person person)
    {
        if(person != null)
        {
            this.personList.add(person);
            person.getHoppyList().add(this);
        }
    }
    public Hoppy(List<Person> personList) {
        this.personList = personList;
    }

    public Hoppy() {
    }

    public Hoppy(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Hoppy(String name, String description) {
        this.name = name;
        this.description = description;
        this.personList= new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}