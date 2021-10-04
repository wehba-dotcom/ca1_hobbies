package dtos;

import entities.Hoppy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HoppyDTO {
    private long id;
    private String name;
    private String description;



    public HoppyDTO(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public static List<HoppyDTO> getHoppyDtos(List<Hoppy> hoppyList){
        List<HoppyDTO> hoppyDTOList = new ArrayList();
        hoppyList.forEach(hoppy->hoppyDTOList.add(new HoppyDTO(hoppy)));
        return hoppyDTOList;
    }
    public HoppyDTO(Hoppy rm) {
        if(rm.getId() != null)
            this.id = rm.getId();
        this.name= rm.getMame();
        this.description = rm.getDescription();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HoppyDTO)) return false;
        HoppyDTO hoppyDTO = (HoppyDTO) o;
        return getId() == hoppyDTO.getId() && Objects.equals(getName(), hoppyDTO.getName()) && Objects.equals(getDescription(), hoppyDTO.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }
}
