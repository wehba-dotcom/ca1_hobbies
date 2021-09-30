package facades;

import dtos.CityInfoDTO;
import dtos.PersonDTO;
import entities.CityInfo;
import entities.Person;

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
}
