package dev.spimy.titles.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import dev.spimy.titles.Main;
import dev.spimy.titles.commands.SubCommand;

public class Delete implements SubCommand {

	private Main main;
	
	public Delete(Main main) {
		this.main = main;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if (!sender.hasPermission("customtitles.give")) {
			sender.sendMessage(main.ChatUtils().format(main.getConfig().getString("NoPerm")));
			return;
		}
		
		if (args.length < 1) {
			main.ChatUtils().helpMsg(sender);
			return;
		}
		
		if (args.length > 1) {
			main.ChatUtils().helpMsg(sender);
			return;
		}
		
		String title = args[0];
		
		Scoreboard scoreboard = main.getServer().getScoreboardManager().getMainScoreboard();
		Team team = scoreboard.getTeam(title);
		
		if (!main.TitleConfig().getTitleConfig().contains("titles." + title) || team == null) {
			sender.sendMessage(main.ChatUtils().format(main.getConfig().getString("TitleNotFound").replace("{title}", title)));
			return;
		}
		
		String msg = main.getConfig().getString("TitleDeleted");
		msg = msg.replace("{title}", main.TitleConfig().getTitleConfig().getString("titles." + title + ".title"));
		msg = msg.replace("{title_name}", title);
		
		sender.sendMessage(main.ChatUtils().format(msg));
		
		main.TitleConfig().getTitleConfig().set(String.format("titles.%s", title), null);
		main.TitleConfig().saveTitleConfig();
		team.unregister();
		
		return;
		
	}
	
}
