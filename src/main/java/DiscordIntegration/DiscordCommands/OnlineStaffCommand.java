package DiscordIntegration.DiscordCommands;

import DiscordIntegration.Utils;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import net.shmeeb.miscec.Main;
import net.shmeeb.miscec.misc.TextUtils;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OnlineStaffCommand extends Command {

    public OnlineStaffCommand() {
        this.name = "sstatus";
        this.requiredRole = "staff";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        List<Player> onlinePlayers = new ArrayList<>();
        Queue<Player> staffOnline = new LinkedList<>();
        String lasthostedTime = TextUtils.timeDiffFormat((System.currentTimeMillis() - Main.last_hosted_1) / 1000, false);
        String lasthostedUser = Main.last_hosted_by_1;

        onlinePlayers.addAll(Sponge.getServer().getOnlinePlayers());
        int totalNum = onlinePlayers.size();

        String[] s = commandEvent.getArgs().split(" ");

        if (!s[0].isEmpty()) {
            if (s[0].equals(Sponge.getServer().getMotd().toPlain())) {
                for (Player player : Sponge.getServer().getOnlinePlayers()) {
                    for (String sRank : Utils.getStaffRanks()) {
                        if (player.hasPermission("group." + sRank) && !staffOnline.contains(player)) {staffOnline.add(player);}
                    }
                }

                commandEvent.reply(staffOnline(staffOnline, totalNum, lasthostedTime, lasthostedUser).build());
            }
        } else {
            for (Player player : Sponge.getServer().getOnlinePlayers()) {
                for (String sRank : Utils.getStaffRanks()) {
                    if (player.hasPermission("group." + sRank) && !staffOnline.contains(player)) {staffOnline.add(player);}
                }
            }

            commandEvent.reply(staffOnline(staffOnline, totalNum, lasthostedTime, lasthostedUser).build());

        }


    }

    public static EmbedBuilder staffOnline(Queue<Player> staffOnline, int totalNum, String lasthostedTime, String lasthostedUser) {
        EmbedBuilder local = new EmbedBuilder();
        local.setColor(Color.green);
        int onlineNum = staffOnline.size();

        if (onlineNum == 0) {
            local.setColor(Color.red).getDescriptionBuilder().append("No Staff Are Online!" + "\n");
        }  else {
            StringBuilder stringBuilder = new StringBuilder("`" + staffOnline.poll().getName() + "`");
            while (!staffOnline.isEmpty()) {
                stringBuilder.append(", `" + staffOnline.poll().getName() + "`");
            }

            local.getDescriptionBuilder().append("Staff Online: " + onlineNum + "\t\n\t\t" + stringBuilder.toString() + "\n");
        }
        if (totalNum == 0) {
            local.getDescriptionBuilder().append("\n" + "No Players Are Online!" + "\n");
        }   else {
            local.getDescriptionBuilder().append("\n" + "Players Online: " + totalNum + "\n");
        }
            local.getDescriptionBuilder().append("Lasthosted " + lasthostedTime + " ago by " + lasthostedUser);
        return local;
    }

}