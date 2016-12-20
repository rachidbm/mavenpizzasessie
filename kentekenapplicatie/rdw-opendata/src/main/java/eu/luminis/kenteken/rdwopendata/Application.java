package eu.luminis.kenteken.rdwopendata;

import java.util.List;

import eu.luminis.kenteken.common.KentekenParser;

public class Application {

    public static void main(final String[] args) {
    	final String kenteken = args[0];
    	if (kenteken != null && new KentekenParser().isValidKenteken(kenteken)) {
    		final RdwOpenDataKentekenService service = new RdwOpenDataKentekenService();
    		final List<VoertuigData> voertuigen = service.findVoertuigen(kenteken);
    		final VoertuigData voertuig = voertuigen.get(0);
    		System.out.println(String.format("Gevonden voertuig: %s %s in de kleur %s met %d cilinders", voertuig.getMake(), voertuig.getModel(), voertuig.getColor(), voertuig.getNumberOfCylinders()));
    	} else {
    		System.out.println(String.format("%s is geen geldig kenteken", kenteken));
    	}
    }
}
