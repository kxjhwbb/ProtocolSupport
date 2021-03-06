package protocolsupport.api.tab;

import java.io.IOException;

import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_9_R2.PacketPlayOutPlayerListHeaderFooter;
import protocolsupport.api.chat.ChatAPI;
import protocolsupport.api.chat.components.BaseComponent;
import protocolsupport.api.chat.components.TextComponent;
import protocolsupport.protocol.serializer.ChainedProtocolSupportPacketDataSerializer;

public class TabAPI {

	private static int maxTabSize = Math.min(Bukkit.getMaxPlayers(), 60);

	public static int getMaxTabSize() {
		return maxTabSize;
	}

	public static void setMaxTabSize(int maxSize) {
		maxTabSize = maxSize;
	}


	private static BaseComponent currentHeader;
	private static BaseComponent currentFooter;

	public static void setDefaultHeader(BaseComponent header) {
		currentHeader = header;
	}

	public static void setDefaultFooter(BaseComponent footer) {
		currentFooter = footer;
	}

	public static BaseComponent getDefaultHeader() {
		return currentHeader;
	}

	public static BaseComponent getDefaultFooter() {
		return currentFooter;
	}

	private static final BaseComponent empty = new TextComponent("");

	public static void sendHeaderFooter(Player player, BaseComponent header, BaseComponent footer) {
		Validate.notNull(player, "Player can't be null");
		ChainedProtocolSupportPacketDataSerializer serializer = new ChainedProtocolSupportPacketDataSerializer();
		serializer.writeString(ChatAPI.toJSON(header != null ? header : empty));
		serializer.writeString(ChatAPI.toJSON(footer != null ? footer : empty));
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		try {
			packet.a(serializer.getNativeSerializer());
		} catch (IOException e) {
		}
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

}
