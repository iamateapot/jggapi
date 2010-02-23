package pl.radical.open.gg.packet.out;

import pl.radical.open.gg.GGNullPointerException;
import pl.radical.open.gg.ILocalStatus;
import pl.radical.open.gg.packet.GGConversion;
import pl.radical.open.gg.packet.GGStatuses;
import pl.radical.open.gg.packet.GGUtils;

import org.apache.commons.collections.primitives.ArrayByteList;
import org.apache.commons.collections.primitives.ByteList;

/**
 * Packet that sets new status of the Gadu-Gadu user.
 * 
 * @author <a href="mailto:mnaglik@gazeta.pl">Marcin Naglik</a>
 * @author <a href="mailto:mati@sz.home.pl">Mateusz Szczap</a>
 */
public class GGNewStatus80 implements GGOutgoingPackage, GGStatuses {

	public static final int GG_NEW_STATUS80 = 0x0038;

	private static final int MAX_DESCRIPTION = 255;

	private ILocalStatus m_localStatus = null;

	final int FLAGS_UNKNOWN = 0x00000001;
	final int FLAGS_VIDEOCHAT = 0x00000002;
	final int FLAGS_MOBILE = 0x00100000;
	final int FLAGS_LINKS = 0x00800000;

	/**
	 * The protocol status constructor.
	 */
	public GGNewStatus80(final ILocalStatus localStatus) {
		if (localStatus == null) {
			throw new GGNullPointerException("status cannot be null");
		}
		m_localStatus = localStatus;
	}

	/**
	 * @see pl.radical.open.gg.packet.out.GGOutgoingPackage#getPacketType()
	 */
	public int getPacketType() {
		return GG_NEW_STATUS80;
	}

	/**
	 * @see pl.radical.open.gg.packet.out.GGOutgoingPackage#getLength()
	 */
	public int getLength() {
		int length = 4;

		if (m_localStatus.getStatusType().isDescriptionStatus() && m_localStatus.isDescriptionSet()) {
			length += m_localStatus.getDescription().length() + 1;
			if (m_localStatus.isReturnDateSet()) {
				length += 4;
			}
		}

		return length;
	}

	/**
	 * @see pl.radical.open.gg.packet.out.GGOutgoingPackage#getContents()
	 */
	public byte[] getContents() {
		final int statusToSend = GGConversion.getProtocolStatus(m_localStatus, m_localStatus.isFriendsOnly(), false);

		final ByteList byteList = new ArrayByteList(getLength());

		byteList.add((byte) (statusToSend & 0xFF));
		byteList.add((byte) (statusToSend >> 8 & 0xFF));
		byteList.add((byte) (statusToSend >> 16 & 0xFF));
		byteList.add((byte) (statusToSend >> 24 & 0xFF));

		byteList.add((byte) (FLAGS_UNKNOWN & 0xFF));
		byteList.add((byte) (FLAGS_UNKNOWN >> 8 & 0xFF));
		byteList.add((byte) (FLAGS_UNKNOWN >> 16 & 0xFF));
		byteList.add((byte) (FLAGS_UNKNOWN >> 24 & 0xFF));

		if (m_localStatus.getStatusType().isDescriptionStatus() && m_localStatus.isDescriptionSet()) {
			byteList.add((byte) (m_localStatus.getDescription().length() & 0xFF));
			byteList.add((byte) (m_localStatus.getDescription().length() >> 8 & 0xFF));
			byteList.add((byte) (m_localStatus.getDescription().length() >> 16 & 0xFF));
			byteList.add((byte) (m_localStatus.getDescription().length() >> 24 & 0xFF));

			final String description = trimDescription(m_localStatus.getDescription());
			final byte[] descBytes = description.getBytes();
			for (final byte descByte : descBytes) {
				byteList.add(descByte);
			}
			if (m_localStatus.isReturnDateSet()) {
				final int timeInSeconds = GGUtils.millisToSeconds(m_localStatus.getReturnDate().getTime());
				byteList.add((byte) (timeInSeconds & 0xFF));
				byteList.add((byte) (timeInSeconds >> 8 & 0xFF));
				byteList.add((byte) (timeInSeconds >> 16 & 0xFF));
				byteList.add((byte) (timeInSeconds >> 24 & 0xFF));
			}
		}
		return byteList.toArray();
	}

	private String trimDescription(String description) {
		if (description == null) {
			return null;
		}
		if (description.length() > MAX_DESCRIPTION) {
			description = description.substring(0, MAX_DESCRIPTION - 1);
		}
		return description;
	}

}
