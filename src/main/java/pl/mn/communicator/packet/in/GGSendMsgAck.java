/*
 * Copyright (c) 2003-2005 JGGApi Development Team. All Rights Reserved. This program is free software; you can
 * redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version. This program is distributed in
 * the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a
 * copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package pl.mn.communicator.packet.in;

import pl.mn.communicator.packet.GGUtils;

/**
 * Acknowledgment of successuly delivered message that is recieved from Gadu-Gadu server.
 * 
 * @author <a href="mailto:mati@sz.home.pl">Mateusz Szczap</a>
 * @version $Id: GGSendMsgAck.java,v 1.1 2005/11/05 23:34:52 winnetou25 Exp $
 */
public class GGSendMsgAck implements GGIncomingPackage {

	public final static int GG_SEND_MSG_ACK = 0x0005;

	// Message statuses

	/** Message has not been delivered. */
	public final static int GG_ACK_BLOCKED = 0x0001;

	/** Message has been successfuly delivered. */
	public final static int GG_ACK_DELIVERED = 0x0002;

	/** Message has been queued for later delivery. */
	public final static int GG_ACK_QUEUED = 0x0003;

	/** Message has not been delivered because remote queue is full (max. 20 messages). */
	public final static int GG_ACK_MBOXFULL = 0x0004;

	/** Message has not been delivered. This status is only in case of GG_CLASS_CTCP */
	public final static int GG_ACK_NOT_DELIVERED = 0x0006;

	private int m_messageStatus = -1;
	private int m_recipient = -1;
	private int m_messageSeq = -1;

	public GGSendMsgAck(final byte[] data) {
		m_messageStatus = GGUtils.byteToInt(data, 0);
		m_recipient = GGUtils.byteToInt(data, 4);
		m_messageSeq = GGUtils.byteToInt(data, 8);
	}

	/**
	 * @see pl.mn.communicator.packet.in.GGIncomingPackage#getPacketType()
	 */
	public int getPacketType() {
		return GG_SEND_MSG_ACK;
	}

	/**
	 * @return Status of the message. Available statuses are listed above.
	 */
	public int getMessageStatus() {
		return m_messageStatus;
	}

	/**
	 * @return Gadu-Gadu uin number of the person to whom message was sent.
	 */
	public int getRecipientUin() {
		return m_recipient;
	}

	/**
	 * @return Sequence number of the message that has been sent.
	 */
	public int getMessageSeq() {
		return m_messageSeq;
	}

}
