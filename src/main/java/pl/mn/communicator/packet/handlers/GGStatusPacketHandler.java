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
import pl.mn.communicator.IRemoteStatus;
import pl.mn.communicator.IUser;
import pl.mn.communicator.packet.GGUtils;
import pl.mn.communicator.packet.in.GGStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2004-11-28
 * 
 * @author <a href="mailto:mati@sz.home.pl">Mateusz Szczap</a>
 * @version $Id: GGStatusPacketHandler.java,v 1.1 2005/11/05 23:34:53 winnetou25 Exp $
 */
public class GGStatusPacketHandler implements PacketHandler {

	private final Logger logger = LoggerFactory.getLogger(GGStatusPacketHandler.class);

	/**
	 * @see pl.mn.communicator.packet.handlers.PacketHandler#handle(pl.mn.communicator.packet.handlers.Context)
	 */
	public void handle(final PacketContext context) throws GGException {
		if (logger.isDebugEnabled()) {
			logger.debug("Received GGStatus packet.");
			logger.debug("PacketHeader: " + context.getHeader());
			logger.debug("PacketBody: " + GGUtils.prettyBytesToString(context.getPackageContent()));
		}
		final GGStatus status = new GGStatus(context.getPackageContent());
		context.getSessionAccessor().notifyGGPacketReceived(status);
		final IUser user = status.getUser();
		final IRemoteStatus statusBiz = status.getStatus();
		context.getSessionAccessor().notifyUserChangedStatus(user, statusBiz);
	}

}
