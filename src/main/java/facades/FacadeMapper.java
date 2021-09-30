package facades;

import dtos.PersonDTO;
import entities.Hoppy;
import entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class FacadeMapper {
    private static FacadeMapper instance;
    private static EntityManagerFactory emf;
    private FacadeHoppy facadeHoppy;
    private FacadePerson facadePerson;

    public FacadeMapper() {
    }

    public static FacadeMapper getFacadeMapper(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeMapper();
        }
        return instance;
    }

    public List<PersonDTO> getAllPersonesDTO() {
        List<PersonDTO> personList = facadePerson.getAll();

        return personList;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PersonDTO addPersonDTO(PersonDTO personDTO) {
        return facadePerson.createPerson(personDTO);
    }

    public long getPersoneCount() {
        long getcounts = facadePerson.getPersonCount();
        return getcounts;

    }


}
