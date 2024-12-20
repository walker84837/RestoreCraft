package org.winlogon.restorecraft;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

import java.util.List;
import java.util.Optional;

/**
 * Handles the /heal command. 
 */
public class HealCommand implements CommandExecutor {
    public boolean removeEffects;
    public boolean showWhoHealed;

    private final List<String> errorMessages = List.of(
        "§cYou do not have permission to use this command.",
        "§cOnly players can heal themselves.",
        "§cPlayer not found",
        "§cPlayer not online"
    );

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(errorMessages.get(1));
            return true;
        }

        if (!sender.hasPermission("heal.admin")) {
            sender.sendMessage(errorMessages.get(0));
            return true;
        }

        Optional<Player> target;

        if (args.length <= 0) {
            target = Optional.of((Player) sender);
        } else {
            target = Optional.ofNullable(Bukkit.getPlayer(args[0]));
            if (target.isEmpty()) {
                sender.sendMessage(errorMessages.get(2));
                return true;
            }
        }

        target.ifPresent(player -> {
            healPlayer(player);
            if (player.equals(sender)) {
                sender.sendMessage("§3You have been healed.");
            } else {
                sender.sendMessage("§7" + player.getName() + "§3 has been healed.");
                player.sendMessage((showWhoHealed) ? "§3You have been healed by §7" 
                    + sender.getName() + "§3." : "§3You have been healed.");
            }
        });

        return true;
    }

    /**
     * Heals a player.
     *
     * @param player The player to heal.

     * @see HealCommand#healPlayer(Player)
     * @return void
     */
    private void healPlayer(Player player) {
        AttributeInstance maxHealth = player.getAttribute(Attribute.MAX_HEALTH);
        player.setHealth(maxHealth.getValue());
        player.setFoodLevel(20);
        player.setSaturation(20f);

        if (removeEffects) {
            player.getActivePotionEffects().forEach(effect -> {
                player.removePotionEffect(effect.getType());
            });
        }
    }
}
