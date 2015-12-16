package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.storage.LocalStorage;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedPlayer;

public abstract class MiddleLogin<T> extends ClientBoundMiddlePacket<T> {

	protected int playerEntityId;
	protected int gamemode;
	protected int dimension;
	protected int difficulty;
	protected int maxplayers;
	protected String leveltype;

	@Override
	public void readFromServerData(PacketDataSerializer serializer) {
		playerEntityId = serializer.readInt();
		gamemode = serializer.readByte();
		dimension = serializer.readByte();
		difficulty = serializer.readByte();
		maxplayers = serializer.readByte();
		leveltype = serializer.readString(16);
	}

	@Override
	public void handle(LocalStorage storage) {
		storage.addWatchedSelfPlayer(new WatchedPlayer(playerEntityId));
	}

}
