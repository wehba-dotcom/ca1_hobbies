package facades;

import dtos.PersonDTO;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entities.Phone;
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
    private FacadePerson() {}
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
       /* FacadePerson fe = getFacadePerson(emf);
        fe.getAll().forEach(dto->System.out.println(dto));*/
      /*  emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Person p1 = new Person("wewe@email","wehba","korouni");
        Phone phone1= new Phone("345345","mobel");
        Phone phone2= new Phone("9999999","mobel");
        Phone phone3= new Phone("8888888","mobel");
        p1.addPhone(phone1);
        p1.addPhone(phone2);
        p1.addPhone(phone3);
        try{
            em.getTransaction().begin();
           // em.persist(p1);
            em.getTransaction().commit();

        }finally {
            em.close();
        }*/
    }

}
