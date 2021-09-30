package dtos;

import entities.Hoppy;

import java.util.ArrayList;
import java.util.List;

public class HoppyDTO {
    private Long id;
    private String name;
    private String description;

    public HoppyDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public HoppyDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public HoppyDTO(Hoppy hoppy) {
        if(hoppy.getId()!=null)
        {
            this.id=hoppy.getId();
            this.name= hoppy.getName();
            this.description=hoppy.getDescription();
        }
    }

    public static List<HoppyDTO> getHoppyDTO(List<Hoppy> hoppyList)
    {
       List<HoppyDTO> hoppyDTOList= new ArrayList();
       hoppyList.forEach(hoppy -> hoppyDTOList.add(new HoppyDTO(hoppy)));
       return hoppyDTOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
