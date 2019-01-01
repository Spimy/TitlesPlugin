package dev.spimy.titles.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import dev.spimy.titles.Main;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import net.minecraft.server.v1_8_R1.PlayerConnection;

public class ChatUtils {

	private Main main;

	public ChatUtils(Main main) {
		this.main = main;
	}

	public String format(String string) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			string = string.replace("{prefix}", main.getConfig().getString("Prefix"));
			string = string.replace("{player}", player.getName());
			string = ChatColor.translateAlternateColorCodes('&', string);
		}
		return string;
	}

	public void helpMsg(CommandSender sender) {

		if (!(sender instanceof Player)) return;

		Player player = (Player) sender;
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(
				"[\"\",{\"text\":\"-=-=-=-=-=-\",\"color\":\"gray\",\"strikethrough\":true},{\"text\":\" Titles \",\"color\":\"gold\",\"strikethrough\":false},{\"text\":\"-=-=-=-=-=-\\n\",\"color\":\"gray\",\"strikethrough\":true},{\"text\":\"/title create <title name> <title> <permission>\\n\",\"color\":\"green\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/title create\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Creates a new Title\",\"color\":\"gold\"}]}},\"strikethrough\":false},{\"text\":\"/title delete <title>\\n\",\"color\":\"green\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/title delete\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Deletes a Title\",\"color\":\"gold\"}]}}},{\"text\":\"/title give <player> <title>\\n\",\"color\":\"green\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/title give\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Gives a Title to specified Player\",\"color\":\"gold\"}]}}},{\"text\":\"/title remove <player> <title>\\n\",\"color\":\"green\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/title remove\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Removes Title from specified Player\",\"color\":\"gold\"}]}}},{\"text\":\"/title help\\n\",\"color\":\"green\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/title help\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Shows this Message\",\"color\":\"gold\"}]}}},{\"text\":\"/title reload\\n\",\"color\":\"green\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/title reload\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Reloads the Plugin's Config Files\",\"color\":\"gold\"}]}}},{\"text\":\"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\",\"color\":\"gray\",\"strikethrough\":true}]"));

		connection.sendPacket(packet);

		return;
	}

}
