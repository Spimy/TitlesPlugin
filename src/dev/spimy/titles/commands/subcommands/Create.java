package dev.spimy.titles.commands.subcommands;

import org.bukkit.command.CommandSender;

import dev.spimy.titles.Main;
import dev.spimy.titles.commands.SubCommand;
import dev.spimy.titles.utils.TitleManager;

public class Create implements SubCommand {

	private Main main;
	private TitleManager createtitle;
	
	public Create(Main main) {
		this.main = main;
		this.createtitle = new TitleManager();
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if (!sender.hasPermission("customtitles.create")) {
			sender.sendMessage(main.ChatUtils().format(main.getConfig().getString("NoPerm")));
			return;
		}
		
		if (args.length < 3) {
			main.ChatUtils().helpMsg(sender);
			return;
		}
		
		if (args.length > 3) {
			main.ChatUtils().helpMsg(sender);
			return;
		}
		
		String title_name = args[0];
		String title = args[1];
		String perm = args[2];
		
		if (title.length() > 16) {
			sender.sendMessage(main.ChatUtils().format(main.getConfig().getString("TitleTooLong")));
			return;
		}
		
		if (main.TitleConfig().getTitleConfig().contains("titles." + title_name)) {
			String msg = main.getConfig().getString("TitleAlreadyExist");
			msg = msg.replace("{title}", title);
			msg = msg.replace("{title_name}", title_name);
			sender.sendMessage(main.ChatUtils().format(msg));
			return;
		}
				
		main.TitleConfig().getTitleConfig().set(String.format("titles.%s.title", title_name), title + " ");
		main.TitleConfig().getTitleConfig().set(String.format("titles.%s.permission", title_name), perm);
		main.TitleConfig().saveTitleConfig();
		
		createtitle.RegisterTitle(main, title_name, title);
		
		String msg = main.getConfig().getString("TitleCreated");
		msg = msg.replace("{title}", title);
		msg = msg.replace("{title_name}", title_name);
		
		sender.sendMessage(main.ChatUtils().format(msg));
		return;
	}
	
}
