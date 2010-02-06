/*
 * Copyright (c) 2003-2005 JGGApi Development Team. All Rights Reserved. This program is free software; you can
 * redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version. This program is distributed in
 * the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a
 * copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package pl.mn.communicator;

import pl.mn.communicator.packet.GGConversion;

import java.util.Date;

/**
 * The class that represents message that is received from Gadu-Gadu server.
 * <p>
 * Created on 2004-11-21
 * 
 * @author <a href="mailto:mati@sz.home.pl">Mateusz Szczap</a>
 * @version $Id: IncomingMessage.java,v 1.1 2005/11/05 23:34:52 winnetou25 Exp $
 */
public class IncomingMessage extends AbstractMessage implements IIncommingMessage {

	/** The time the message was sent */
	private Date m_messageDate = null;

	/**
	 * Constructor for <code>IncomingMessage</code>.
	 * 
	 * @param uin
	 *            Gadu-Gadu number of the user that sent the message.
	 * @param messageBody
	 *            the body of the message.
	 * @throws IllegalArgumentException
	 *             if the uin or messageID or messageDate is a negative value.
	 * @throws NullPointerException
	 *             if the messageBody is null.
	 */
	public IncomingMessage(final int uin, final String messageBody, final int messageID, final long messageDate, final int protocolMessageClass) {
		super(uin, messageBody, GGConversion.getClientMessageClass(protocolMessageClass));
		if (messageDate < 0) {
			throw new IllegalArgumentException("messageDate cannot be less than 0");
		}
		if (messageID < 0) {
			throw new IllegalArgumentException("messageID cannot be less than 0");
		}
		m_messageDate = new Date(messageDate);
		m_messageID = messageID;
	}

	/**
	 * Returns the time this message was sent.
	 * 
	 * @return Date the time this message was sent.
	 */
	public Date getMessageDate() {
		return m_messageDate;
	}

}
