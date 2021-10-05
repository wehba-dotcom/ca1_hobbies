package facades;

import dtos.AddressDTO;
import dtos.PersonDTO;
import entities.Address;
import entities.Person;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

 public class FacadeAddress {
    private static FacadeAddress instance;
    private static EntityManagerFactory emf;
    public FacadeAddress() {
    }
    public static FacadeAddress getFacadeAddress(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeAddress();
        }
        return instance;
    }
    public AddressDTO getAddressById(long id) {
        EntityManager em = emf.createEntityManager();
        return new AddressDTO(em.find(Address.class, id));
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public AddressDTO create(AddressDTO addressDTO) {
        Address address = new Address(addressDTO.getStreet(), addressDTO.getHoseNumber());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new AddressDTO(address);
    }
    public List<AddressDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Address> query = em.createQuery("SELECT r FROM Address r", Address.class);
        List<Address> rms = query.getResultList();
        return AddressDTO.getDtos(rms);
    }
    public long getAddressCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long AddressCount = (long) em.createQuery("SELECT COUNT(r) FROM Address r").getSingleResult();
            return AddressCount;
        } finally {
            em.close();
        }
    }
    public static void main(String[] args) {
      /*  emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Address a1 = new Address("toftevej",234234);
        Person p1 = new Person("wer@wqer","wehba","sdfsdf");
        Person p2 = new Person("ram@wqer","ram","wetwer");
         a1.addperson(p1);
         a1.addperson(p2);
         try{
             em.getTransaction().begin();
             em.persist(a1);
             em.getTransaction().commit();

         }finally {
             em.close();
         }
    }*/
    }
}
