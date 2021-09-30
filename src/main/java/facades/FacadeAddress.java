package facades;

import dtos.AddressDTO;
import dtos.PersonDTO;
import entities.Address;
import entities.Person;

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
    public AddressDTO getAddressById(long id){
        EntityManager em = emf.createEntityManager();
        return new AddressDTO(em.find(Address.class, id));
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public AddressDTO create(AddressDTO addressDTO){
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

    public List<AddressDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Address> query = em.createQuery("SELECT r FROM Address r", Address.class);
        List<Address> rms = query.getResultList();
        return AddressDTO.getDtos(rms);
    }
    public long getAddressCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long AddressCount = (long)em.createQuery("SELECT COUNT(r) FROM Address r").getSingleResult();
            return AddressCount;
        }finally{
            em.close();
        }
    }

}
