package pl.radical.open.gg.packet.in;

import pl.radical.open.gg.packet.GGBaseIncomingPacket;
import pl.radical.open.gg.packet.GGIncomingPackage;
import pl.radical.open.gg.packet.IncomingPacket;
import pl.radical.open.gg.packet.handlers.GGDisconnectingPacketHandler;

/**
 * @author <a href="mailto:mati@sz.home.pl">Mateusz Szczap</a>
 * @author <a href="mailto:lukasz.rzanek@radical.com.pl>Łukasz Rżanek</a>
 */
@IncomingPacket(type = 0x000b, label = "GG_DISCONNECTING", handler = GGDisconnectingPacketHandler.class)
public class GGDisconnecting extends GGBaseIncomingPacket implements GGIncomingPackage {

	private static GGDisconnecting instance = null;

	private GGDisconnecting() {
		// private constructor
	}

	public static GGDisconnecting getInstance() {
		if (instance == null) {
			instance = new GGDisconnecting();
		}
		return instance;
	}
}
