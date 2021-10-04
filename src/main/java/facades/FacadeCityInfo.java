package facades;

import dtos.CityInfoDTO;
import dtos.PersonDTO;
import entities.Address;
import entities.CityInfo;
import entities.Person;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class FacadeCityInfo {
    private static FacadeCityInfo instance;
    private static EntityManagerFactory emf;

    public FacadeCityInfo() {
    }
    public static FacadeCityInfo getFacadeCityinfo(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeCityInfo();
        }
        return instance;
    }

    public List<CityInfoDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<CityInfo> query = em.createQuery("SELECT r FROM CityInfo r", CityInfo.class);
        List<CityInfo> rms = query.getResultList();
        return CityInfoDTO.getCityInfoDtos(rms);
    }

    public static void main(String[] args) {


        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Address a1 = new Address("toftevej", 234234);
        Address a2 = new Address("syreavej", 234234);
        Address a3 = new Address("dtafgfg", 234234);
          CityInfo c1 = new CityInfo(3453,"DUBAI");
         c1.addAddress(a1);
         c1.addAddress(a2);
        try {
            em.getTransaction().begin();
            em.persist(c1);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

}