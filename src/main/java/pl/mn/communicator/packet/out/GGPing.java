/*
 * Copyright (c) 2003-2005 JGGApi Development Team. All Rights Reserved. This program is free software; you can
 * redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version. This program is distributed in
 * the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a
 * copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package pl.mn.communicator.packet.out;

/**
 * Outgoing packet, ping type that is from time to time being send to Gadu-Gadu server.
 * 
 * @author <a href="mailto:mnaglik@gazeta.pl">Marcin Naglik</a>
 * @author <a href="mailto:mati@sz.home.pl">Mateusz Szczap</a>
 * @version $Id: GGPing.java,v 1.1 2005/11/05 23:34:53 winnetou25 Exp $
 */
public final class GGPing implements GGOutgoingPackage {

	public static final int GG_PING = 0x08;

	private static byte[] m_data = null;

	private static GGPing m_ping = null;

	/**
	 * Prywatny konstruktor.
	 */
	private GGPing() {
		m_data = new byte[0];
	}

	public static GGPing getPing() {
		if (m_ping == null) {
			m_ping = new GGPing();
		}
		return m_ping;
	}

	/**
	 * @see pl.mn.communicator.packet.out.GGOutgoingPackage#getPacketType()
	 */
	public int getPacketType() {
		return GG_PING;
	}

	/**
	 * @see pl.mn.communicator.packet.out.GGOutgoingPackage#getLength()
	 */
	public int getLength() {
		return m_data.length;
	}

	/**
	 * @see pl.mn.communicator.packet.out.GGOutgoingPackage#getContents()
	 */
	public byte[] getContents() {
		return m_data;
	}

}
