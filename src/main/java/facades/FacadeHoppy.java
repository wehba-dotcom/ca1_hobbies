package facades;

import dtos.HoppyDTO;
import entities.Hoppy;
import errorhandling.MissingInputException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class FacadeHoppy {
    public static FacadeHoppy instance;
    public static EntityManagerFactory emf;

    private FacadeHoppy() {
    }
    public static FacadeHoppy getFacadeHoppy(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeHoppy();
        }
        return instance;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public HoppyDTO createHoppy(HoppyDTO hoppyDTO)throws MissingInputException,Exception {
        Hoppy hoppy = new Hoppy(hoppyDTO.getName(), hoppyDTO.getDescription());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hoppy);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HoppyDTO(hoppy);
    }
    public HoppyDTO getHoppyById(long id)throws MissingInputException {
        EntityManager em = emf.createEntityManager();
        return new HoppyDTO(em.find(Hoppy.class, id));
    }
    public HoppyDTO getById(long id){
        EntityManager em = emf.createEntityManager();
        return new HoppyDTO(em.find(Hoppy.class, id));
    }
    public long getHoppyCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long hoppyCount = (long)em.createQuery("SELECT COUNT(r) FROM Hoppy r").getSingleResult();
            return hoppyCount;
        }finally{
            em.close();
        }
    }

    public List<HoppyDTO> getAll()throws MissingInputException{
        EntityManager em = emf.createEntityManager();
        TypedQuery<Hoppy> query = em.createQuery("SELECT hoppy FROM Hoppy hoppy", Hoppy.class);
        List<Hoppy> hoppyList = query.getResultList();
        return HoppyDTO.getHoppyDtos(hoppyList);
    }
    public static void main(String[] args) throws MissingInputException {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeHoppy fh = getFacadeHoppy(emf);
        fh.getAll().forEach(dto->System.out.println(dto));
    }
}
