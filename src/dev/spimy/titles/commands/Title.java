package dev.spimy.titles.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.spimy.titles.Main;
import dev.spimy.titles.commands.subcommands.Create;
import dev.spimy.titles.commands.subcommands.Delete;
import dev.spimy.titles.commands.subcommands.Give;
import dev.spimy.titles.commands.subcommands.Remove;

public class Title implements CommandExecutor {
	
	private Main main;
	private Create create;
	private Give give;
	private Delete delete;
	private Remove remove;
	
	public Title(Main main) {
		this.main = main;
		this.create = new Create(this.main);
		this.give = new Give(this.main);
		this.delete = new Delete(this.main);
		this.remove = new Remove(this.main);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length == 0) {
			sender.sendMessage(main.ChatUtils().format("&cMissing Args"));
            return true;
		}
		
		String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
        
        if (args[0].equalsIgnoreCase("create")) {
        	this.create.execute(sender, newArgs);
        	return true;
        }
        
        if (args[0].equalsIgnoreCase("delete")) {
        	this.delete.execute(sender, newArgs);
        	return true;
        }
        
        if (args[0].equalsIgnoreCase("give")) {
        	this.give.execute(sender, newArgs);
        	return true;
        }
        
        if (args[0].equalsIgnoreCase("remove")) {
        	this.remove.execute(sender, newArgs);
        	return true;
        }
        
        if (args[0].equalsIgnoreCase("help")) {
        	main.ChatUtils().helpMsg(sender);
        	return true;
        }
        
        if (args[0].equalsIgnoreCase("reload")) {
        	main.reloadConfig();
        	main.TitleConfig().reloadTitleConfig();
        	sender.sendMessage(main.ChatUtils().format(main.getConfig().getString("Reloaded")));
        	return true;
        }
        
        main.ChatUtils().helpMsg(sender);
		return true;
	}
	
}
