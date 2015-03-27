/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agh.webstore;

import agh.webstore.db.StoreDb;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author ag
 */
@Path("/store")
public class StoreService {

    private StoreDb db = new StoreDb();
    private final static int LICZBA_KLOCKOW = 10;
    private final static int BRAK_KLOCKOW = 0;
    private final static int DODAJ_KLOCKI = 40;

    @GET
    @Path("/{param}")
    public Response pobierzKlocki(@PathParam("param") String rodzaj) {
        boolean b = db.zmniejszO1LiczbeKlockow(rodzaj);
        String output;
        if(b){
            output =  Integer.toString(LICZBA_KLOCKOW);
        }
        else {
            output = Integer.toString(BRAK_KLOCKOW);
            db.zmienIloscWMagazynie(rodzaj, DODAJ_KLOCKI);
        }
        
        return Response.status(200).entity(output).build();

    }
}
