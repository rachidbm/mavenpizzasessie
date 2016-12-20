package eu.luminis.kenteken.rest;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.luminis.kenteken.common.KentekenParser;
import eu.luminis.kenteken.search.NoVehicleFoundException;
import eu.luminis.kenteken.search.SearchKenteken;
import eu.luminis.kenteken.search.Vehicle;

@Component
@Path("/kenteken")
public class KentekenResource {

    private static final Logger LOG = LoggerFactory.getLogger(KentekenResource.class);

    @Autowired
    private SearchKenteken searchService;

    private final KentekenParser kentekenParser;
    
    public KentekenResource() {
    	kentekenParser = new KentekenParser();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKenteken(@QueryParam("kenteken") final String kenteken) {
        LOG.debug("Get info for kenteken {}", kenteken);
        if (kentekenParser.isValidKenteken(kenteken)) {
	        final Vehicle vehicle;
			try {
				vehicle = searchService.findVehicle(kenteken);
			} catch (final NoVehicleFoundException e) {
				throw new NotFoundException(String.format("Voertuig met kenteken %s is niet gevonden"));
			}
	        return Response.ok(vehicle).build();
        } else {
        	throw new BadRequestException(String.format("%s is geen geldig kenteken", kenteken));
        }
    }
}
