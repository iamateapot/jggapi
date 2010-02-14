package pl.radical.open.gg.packet.http;

import pl.radical.open.gg.GGNullPointerException;
import pl.radical.open.gg.IGGConfiguration;
import pl.radical.open.gg.packet.GGUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created on 2005-01-27
 * 
 * @author <a href="mailto:mati@sz.home.pl">Mateusz Szczap</a>
 */
public class SendAndRemindPasswordRequest extends AbstractTokenRequest {

	private int m_uin = -1;
	private String m_email = null;

	// FIXME IllegalArgumentException
	public SendAndRemindPasswordRequest(final IGGConfiguration configuration, final int uin, final String email, final String tokenID, final String tokenVal) throws IOException {
		super(configuration, tokenID, tokenVal);
		if (uin < 0) {
			throw new IllegalArgumentException("uin cannot be less than 0");
		}
		if (email == null) {
			// FIXME Other exception instead?
			throw new GGNullPointerException("email cannot be null");
		}
		m_uin = uin;
		m_email = email;
	}

	/**
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @see pl.radical.open.gg.packet.http.HttpRequest#getResponse()
	 */
	@Override
	public HttpResponse getResponse() throws IOException {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(m_huc.getInputStream(), GGUtils.WINDOW_ENCODING));
		final String line = reader.readLine();

		return new SendAndRemindPasswordResponse(line);
	}

	/**
	 * @see pl.radical.open.gg.packet.http.HttpRequest#getURL()
	 */
	@Override
	protected String getURL() {
		return m_ggconfiguration.getSendPasswordURL();
		// return "http://retr.gadu-gadu.pl/appsvc/fmsendpwd3.asp";
	}

	/**
	 * @see pl.radical.open.gg.packet.http.HttpRequest#getRequestBody()
	 */
	@Override
	protected String getRequestBody() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("userid=");
		buffer.append(m_uin);
		buffer.append('&');
		buffer.append("email=");
		buffer.append(m_email);
		buffer.append('&');
		buffer.append("tokenid=");
		buffer.append(getTokenID());
		buffer.append('&');
		buffer.append("tokenval=");
		buffer.append(getTokenVal());
		buffer.append('&');
		buffer.append("code=");
		buffer.append(getHashCode(String.valueOf(m_uin)));

		return buffer.toString();
	}

	/**
	 * @see pl.radical.open.gg.packet.http.HttpRequest#wannaWrite()
	 */
	@Override
	protected boolean wannaWrite() {
		return true;
	}

	private int getHashCode(final String uin) {
		if (uin == null) {
			throw new GGNullPointerException("uin cannot be null");
		}

		int a, b, c;

		b = -1;

		for (int i = 0; i < uin.length(); i++) {
			c = uin.charAt(i);
			a = (c ^ b) + (c << 8);
			b = a >>> 24 | a << 8;
		}

		return b < 0 ? -b : b;
	}

	public static class SendAndRemindPasswordResponse extends HttpResponse {

		private final String m_responseString;

		public SendAndRemindPasswordResponse(final String responseString) {
			m_responseString = responseString;
		}

		/**
		 * @see pl.radical.open.gg.packet.http.HttpResponse#isErrorResponse()
		 */
		@Override
		public boolean isOKResponse() {
			return m_responseString.equals("pwdsend_success");
		}

		/**
		 * @see pl.radical.open.gg.packet.http.HttpResponse#getResponseMessage()
		 */
		@Override
		public String getResponseMessage() {
			return m_responseString;
		}

	}

}
