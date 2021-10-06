package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.HoppyDTO;
import dtos.PersonDTO;
import dtos.PhoneDTO;
import entities.Person;
import entities.Phone;
import errorhandling.HoppyNotFoundException;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;
import facades.FacadeMapper;
import utils.EMF_Creator;
import facades.FacadePerson;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static facades.FacadeHoppy.emf;


@Path("person")
public class PersonResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final FacadePerson FACADE = FacadePerson.getFacadePerson(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo()throws PersonNotFoundException {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonsCount() throws PersonNotFoundException {
        long count = FACADE.getPersonCount();
        return "{\"count\":" + count + "}";
    }
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPerson()throws PersonNotFoundException, MissingInputException {
        List<PersonDTO> reslt = FACADE.getAll();
        return Response.ok().entity(GSON.toJson(reslt)).build();
    }
    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(String a) throws PersonNotFoundException,MissingInputException{
        PersonDTO personDTO = GSON.fromJson(a, PersonDTO.class);
        PersonDTO reslt = FACADE.createPerson(personDTO.getEmail(),personDTO.getFirstName(),personDTO.getLastName());
        return Response.ok().entity(GSON.toJson(reslt)).build();
    }
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPhonesByName(@PathParam("name")String  name, String a) throws PersonNotFoundException,MissingInputException
    {
        PhoneDTO phoneDTO = GSON.fromJson(a,PhoneDTO.class);
        if(phoneDTO!=null) {
        System.out.println("PhoneDTO:" + phoneDTO.toString());
        }
        List<PhoneDTO> result = FACADE.getPhonesByPersonName(name);
        return Response.ok().entity(GSON.toJson(result)).build();
    }
  }