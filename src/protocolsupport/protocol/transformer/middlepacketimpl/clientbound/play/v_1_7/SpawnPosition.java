package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_7;

import java.util.Collection;
import java.util.Collections;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleSpawnPosition;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.transformer.middlepacketimpl.SupportedVersions;

@SupportedVersions({ProtocolVersion.MINECRAFT_1_7_10, ProtocolVersion.MINECRAFT_1_7_5})
public class SpawnPosition extends MiddleSpawnPosition<Collection<PacketData>> {

	@Override
	public Collection<PacketData> toData(ProtocolVersion version) {
		PacketDataSerializer serializer = PacketDataSerializer.createNew(version);
		serializer.writeInt(position.getX());
		serializer.writeInt(position.getY());
		serializer.writeInt(position.getZ());
		return Collections.singletonList(new PacketData(ClientBoundPacket.PLAY_SPAWN_POSITION_ID, serializer));
	}

}
