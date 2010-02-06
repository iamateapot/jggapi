/*
 * Copyright (c) 2003-2005 JGGApi Development Team. All Rights Reserved. This program is free software; you can
 * redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version. This program is distributed in
 * the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a
 * copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package pl.mn.communicator.packet.handlers;

import pl.mn.communicator.GGException;
import pl.mn.communicator.PersonalInfo;
import pl.mn.communicator.PublicDirSearchReply;
import pl.mn.communicator.packet.GGUtils;
import pl.mn.communicator.packet.in.GGPubdirReply;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2004-12-15
 * 
 * @author <a href="mailto:mati@sz.home.pl">Mateusz Szczap</a>
 * @version $Id: GGPubdirReplyPacketHandler.java,v 1.1 2005/11/05 23:34:53 winnetou25 Exp $
 */
public class GGPubdirReplyPacketHandler implements PacketHandler {

	private final static Logger logger = LoggerFactory.getLogger(GGPubdirReplyPacketHandler.class);

	/**
	 * @see pl.mn.communicator.packet.handlers.PacketHandler#handle(pl.mn.communicator.packet.handlers.Context)
	 */
	public void handle(final PacketContext context) throws GGException {
		if (logger.isDebugEnabled()) {
			logger.debug("Received GGPubdirReply packet.");
			logger.debug("PacketHeader: " + context.getHeader());
			logger.debug("PacketBody: " + GGUtils.prettyBytesToString(context.getPackageContent()));
		}

		final GGPubdirReply pubdirReply = new GGPubdirReply(context.getPackageContent());
		final int querySeq = pubdirReply.getQuerySeq();

		if (pubdirReply.isPubdirReadReply()) {
			final PersonalInfo publicDirInfo = pubdirReply.getPubdirReadReply();
			context.getSessionAccessor().notifyPubdirRead(querySeq, publicDirInfo);
		} else if (pubdirReply.isPubdirWriteReply()) {
			context.getSessionAccessor().notifyPubdirUpdated(querySeq);
		} else if (pubdirReply.isPubdirSearchReply()) {
			final PublicDirSearchReply pubDirSearchReply = pubdirReply.getPubdirSearchReply();
			context.getSessionAccessor().notifyPubdirGotSearchResults(querySeq, pubDirSearchReply);
		}
	}

}
