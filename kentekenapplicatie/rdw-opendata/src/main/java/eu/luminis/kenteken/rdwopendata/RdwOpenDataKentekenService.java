package eu.luminis.kenteken.rdwopendata;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import eu.luminis.kenteken.search.NoVehicleFoundException;
import eu.luminis.kenteken.search.SearchKenteken;
import eu.luminis.kenteken.search.Vehicle;

@Service
public class RdwOpenDataKentekenService implements SearchKenteken {

    private static final Logger LOG = LoggerFactory.getLogger(RdwOpenDataKentekenService.class);
    
    @Override
    public Vehicle findVehicle(final String kenteken) throws NoVehicleFoundException {
        final List<VoertuigData> vehicles = findVoertuigen(kenteken);
        if (vehicles.isEmpty()) {
            throw new NoVehicleFoundException(kenteken);
        }
        final VoertuigData firstVehicle = vehicles.iterator().next();
        return new Vehicle(firstVehicle.getMake(), firstVehicle.getModel(), firstVehicle.getColor(), firstVehicle.getNumberOfCylinders());
    }

    public List<VoertuigData> findVoertuigen(final String kenteken) {
    	if (StringUtils.isEmpty(kenteken)) {
    		throw new IllegalArgumentException("Kenteken must be filled");
    	}
        final String url = "https://opendata.rdw.nl/resource/m9d7-ebf2.json?kenteken={kenteken}";
        final String normalizedKenteken = kenteken.replace("-", "").toUpperCase();
        LOG.info("Normalized kenteken: {}", normalizedKenteken);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> entity = new HttpEntity<String>("", headers);

        final RestTemplate restClient = new RestTemplate();
        restClient.setInterceptors(Collections.singletonList(new LoggingRequestInterceptor()));
        final ResponseEntity<List<VoertuigData>> result = restClient.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<VoertuigData>>() {}, normalizedKenteken);
        LOG.debug("Kenteken search returned status code {}", result.getStatusCodeValue());
        return result.getBody();
    }
}