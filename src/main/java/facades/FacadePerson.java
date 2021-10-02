package facades;

import dtos.PersonDTO;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadePerson {

    private static FacadePerson instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadePerson() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadePerson getFacadePerson(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadePerson();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PersonDTO createPerson(String email,String firstName,String lastName){
        Person person = new Person(email,firstName,lastName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }
    public PersonDTO getPersonById(int id){
        EntityManager em = emf.createEntityManager();
        return new PersonDTO(em.find(Person.class, id));
    }
    

    public long getPersonCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long PersonCount = (long)em.createQuery("SELECT COUNT(r) FROM Person r").getSingleResult();
            return PersonCount;
        }finally{  
            em.close();
        }
    }
    
    public List<PersonDTO> getAll()throws PersonNotFoundException,MissingInputException{
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT r FROM Person r", Person.class);
        List<Person> rms = query.getResultList();
        return PersonDTO.getDtos(rms);
    }
    
    public static void main(String[] args) throws PersonNotFoundException , MissingInputException {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadePerson fe = getFacadePerson(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }

}
