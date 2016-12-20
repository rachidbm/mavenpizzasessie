package eu.luminis.kenteken.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KentekenParser {

	private static final Logger LOG = LoggerFactory.getLogger(KentekenParser.class);
	
	public boolean isValidKenteken(final String kenteken) {
		if (kenteken.length() != 8) {
			LOG.debug("Kenteken must be 8 characters, not {}", kenteken.length());
			return false;
		}
		if (kenteken.replace("-", "").length() != 6) {
			LOG.debug("Kenteken must have two dashes");
			return false;
		}
		final String[] parts = kenteken.split("-");
		if (parts.length != 3) {
			LOG.debug("Kenteken must have three parts");
			return false;
		}
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].length() < 1 || parts[i].length() > 3) {
				LOG.debug("Parts must be between one and three characters long, not {}", parts[i].length());
				return false;
			}
			if (!(StringUtils.containsOnly(parts[i], "0123456789") || StringUtils.containsOnly(parts[i].toUpperCase(), "ABCDFGHJKLMNPRSTUVWXYZ"))) {
				LOG.debug("Parts must contain either all numeric characters or all letters, not mixed like '{}'", parts[i]);
				return false;
			}
		}
		return true;
	}
}
