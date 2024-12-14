package org.winlogon.restorecraft;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

public class RestoreCraft extends JavaPlugin {

    private boolean removeEffects;

    @Override
    public void onEnable() {
        saveDefaultConfig(); // Create config.yml if it doesn't exist
        removeEffects = getConfig().getBoolean("remove-effects", true); // Default to true if not set

        getCommand("heal").setExecutor(new HealCommand());
        getLogger().info("RestoreCraft enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RestoreCraft disabled!");
    }

    public class HealCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!sender.hasPermission("heal.admin")) {
                sender.sendMessage("You do not have permission to use this command.");
                return true;
            }

            if (!(sender instanceof Player) && args.length == 0) {
                sender.sendMessage("Only players can heal themselves.");
                return true;
            }

            Player target;

            if (args.length > 0) {
                target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage("Player not found.");
                    return true;
                }
            } else {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Only players can heal themselves.");
                    return true;
                }
                target = (Player) sender;
            }

            if (target.isOnline()) {
                healPlayer(target);
                sender.sendMessage(target.equals(sender) ? "You have been healed." : target.getName() + " has been healed.");
            } else {
                sender.sendMessage("Player is not online.");
            }

            return true;
        }

        private void healPlayer(Player player) {
            player.setHealth(player.getAttribute(org.bukkit.attribute.Attribute.MAX_HEALTH).getDefaultValue());
            player.setFoodLevel(20);
            player.setSaturation(20f);
            
            if (removeEffects) {
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
            }
        }
    }
}
