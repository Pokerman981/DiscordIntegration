package DiscordIntegration.DiscordCommands;

import DiscordIntegration.Utils;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.awt.*;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class OnlineStaffCommand extends Command {

    public OnlineStaffCommand() {
        this.name = "sstatus";
        this.requiredRole = "staff";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        Queue<Player> staffOnline = new LinkedList<>();

        String[] s = commandEvent.getArgs().split(" ");

        if (!s[0].isEmpty()) {
            if (s[0].equals(Sponge.getServer().getMotd().toPlain())) {
                for (Player player : Sponge.getServer().getOnlinePlayers()) {
                    for (String sRank : Utils.getStaffRanks()) {
                        if (player.hasPermission("group." + sRank) && !staffOnline.contains(player)) {staffOnline.add(player);}
                    }
                }

                commandEvent.reply(staffOnline(staffOnline).build());
            }
        } else {
            for (Player player : Sponge.getServer().getOnlinePlayers()) {
                for (String sRank : Utils.getStaffRanks()) {
                    if (player.hasPermission("group." + sRank) && !staffOnline.contains(player)) {staffOnline.add(player);}
                }
            }

            commandEvent.reply(staffOnline(staffOnline).build());

        }


    }

    public static EmbedBuilder staffOnline(Queue<Player> staffOnline) {
        EmbedBuilder local = new EmbedBuilder();
        local.setColor(Color.green);
        int onlineNum = staffOnline.size();

        if (onlineNum == 0) {
            local.setColor(Color.red).getDescriptionBuilder().append("No Staff Are Online!");
        }  else {
            StringBuilder stringBuilder = new StringBuilder("`" + staffOnline.poll().getName() + "`");
            while (!staffOnline.isEmpty()) {
                stringBuilder.append(", `" + staffOnline.poll().getName() + "`");
            }

            local.getDescriptionBuilder().append("Staff Online: " + onlineNum + "\t\n\t\t" + stringBuilder.toString());
        }
        return local;
    }

}
