package io.github.radcraftplay.justfly;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommandExecutor implements CommandExecutor {

    private final Server _server;
    static final String NO_PERMISSIONS_MESSAGE = ChatColor.RED + "You do not have permissions to do this!";

    public FlyCommandExecutor(Server server) {
        _server = server;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("fly"))
            return false;

        if (!_server.getAllowFlight()) {
            sender.sendMessage(ChatColor.RED + "Flying is disabled on this server!");
            return true;
        }

        if (args.length == 0) {
            return flySelf(sender);
        } else if (args.length == 1) {
            Player target = _server.getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(String.format("Unable to find player with nickname '%s'", args[0]));
                return false;
            }

            return flyOther(sender, target);
        } else {
            return false;
        }
    }

    boolean checkPermission(CommandSender sender, String permission) {
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(NO_PERMISSIONS_MESSAGE);
            return false;
        }

        return true;
    }

    boolean flySelf(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can be run only by players!");
            return true;
        }

        if (!checkPermission(sender, "justfly.fly"))
            return true;

        Player target = (Player)sender;

        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.sendMessage("Flying is now" + ChatColor.RED + " disabled!");
        }
        else {
            target.setAllowFlight(true);
            target.sendMessage("Flying is now" + ChatColor.GREEN + " enabled!");
        }

        return true;
    }

    boolean flyOther(CommandSender sender, Player target) {
        if (sender instanceof Player) {
            Player source = (Player)sender;

            if (source.getName().equals(target.getName())) {
                return flySelf(sender);
            }
        }

        if (!checkPermission(sender, "justfly.flyothers"))
            return true;


        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            sender.sendMessage("Flying is now"
                    + ChatColor.RED
                    + " disabled"
                    + ChatColor.GRAY +
                    " for "
                    + ChatColor.BLUE
                    + target.getName());

            target.sendMessage("Flying is now" + ChatColor.RED + " disabled!");
        }
        else {
            target.setAllowFlight(true);
            sender.sendMessage("Flying is now"
                    + ChatColor.GREEN
                    + " enabled"
                    + ChatColor.GRAY +
                    " for "
                    + ChatColor.BLUE
                    + target.getName());

            target.sendMessage("Flying is now" + ChatColor.GREEN + " enabled!");
        }

        return true;
    }
}
