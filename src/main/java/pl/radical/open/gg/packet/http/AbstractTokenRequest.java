package pl.radical.open.gg.packet.http;

import pl.radical.open.gg.GGNullPointerException;
import pl.radical.open.gg.IGGConfiguration;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2005-01-27
 * 
 * @author <a href="mailto:mati@sz.home.pl">Mateusz Szczap</a>
 * @author <a href="mailto:lukasz.rzanek@radical.com.pl>Łukasz Rżanek</a>
 */
public abstract class AbstractTokenRequest extends HttpRequest {
	private final Logger log = LoggerFactory.getLogger(AbstractTokenRequest.class);

	String tokenID = null;
	String tokenVal = null;

	protected AbstractTokenRequest(final IGGConfiguration configuration, final String tokenID, final String tokenVal) throws IOException {
		super(configuration);

		if (log.isTraceEnabled()) {
			log.trace("Creating {} object instance", getClass());
		}

		if (tokenID == null) {
			log.error("tokeID cannot be null");
			throw new GGNullPointerException("tokenID cannot be null");
		}
		if (tokenVal == null) {
			log.error("tokenVal cannot be null");
			throw new GGNullPointerException("tokenVal cannot be null");
		}
		this.tokenID = tokenID;
		this.tokenVal = tokenVal;
	}

	protected int getHashCode(final String email, final String password) {
		if (password == null) {
			log.error("password cannot be null");
			throw new IllegalArgumentException("password cannot be null");
		}
		if (email == null) {
			log.error("email cannot be null");
			throw new IllegalArgumentException("email cannot be null");
		}

		int a, b, c;

		b = -1;

		for (int i = 0; i < email.length(); i++) {
			c = email.charAt(i);
			a = (c ^ b) + (c << 8);
			b = a >>> 24 | a << 8;
		}

		for (int i = 0; i < password.length(); i++) {
			c = password.charAt(i);
			a = (c ^ b) + (c << 8);
			b = a >>> 24 | a << 8;
		}

		final int hashCode = b < 0 ? -b : b;

		if (log.isInfoEnabled()) {
			log.info("Value of computed hash is {}", hashCode);
		}

		return hashCode;
	}

}
