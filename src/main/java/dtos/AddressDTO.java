package dtos;

import entities.Address;
import entities.Hoppy;

import java.util.ArrayList;
import java.util.List;

public class AddressDTO {
    private int id;
    private String street;
    private int hoseNumber;

    public AddressDTO(int id, String street, int hoseNumber) {
        this.id = id;
        this.street = street;
        this.hoseNumber = hoseNumber;
    }
    public AddressDTO(Address address) {
        if(address.getId()!=null)
        {
            this.street= address.getStreet();
            this.hoseNumber=address.getHoseNumber();
        }
    }

    public static List<AddressDTO> getDtos(List<Address> addresses)
    {
        List<AddressDTO> addressDTOList= new ArrayList();
        addresses.forEach(address -> addressDTOList.add(new AddressDTO(address)));
        return addressDTOList;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHoseNumber() {
        return hoseNumber;
    }

    public void setHoseNumber(int hoseNumber) {
        this.hoseNumber = hoseNumber;
    }
}
