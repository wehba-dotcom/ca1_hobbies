package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.HoppyDTO;
import errorhandling.MissingInputException;
import facades.FacadeHoppy;

import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("hoppy")
public class HoppyResource {


    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final FacadeHoppy FACADE = FacadeHoppy.getFacadeHoppy(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllHobbies() throws MissingInputException,Exception {
        List<HoppyDTO> reslt = FACADE.getAll();
        return Response.ok().entity(GSON.toJson(reslt)).build();
    }

    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response addHoppy(String a) throws MissingInputException,Exception{
        HoppyDTO hoppyDTO = GSON.fromJson(a, HoppyDTO.class);
        HoppyDTO reslt = FACADE.createHoppy(hoppyDTO);
        return Response.ok().entity(GSON.toJson(reslt)).build();
    }
}