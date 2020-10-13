package DiscordIntegration.DiscordCommands;

import DiscordIntegration.Utils;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.shmeeb.miscec.Main;
import net.shmeeb.miscec.misc.TextUtils;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OnlineStaffCommand extends Command {

    public OnlineStaffCommand() {
        this.name = "sstatus";
        this.userPermissions = new Permission[] {Permission.VOICE_MUTE_OTHERS, Permission.VOICE_DEAF_OTHERS};
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        String[] s = commandEvent.getArgs().split(" ");
        String suppliedServer = s[0];

        if (suppliedServer.isEmpty()) {
            return;
        }

        List<Player> onlinePlayers = (List<Player>) Sponge.getServer().getOnlinePlayers();
        Queue<Player> staffOnline = new LinkedList<>();
        String lasthostedTime = TextUtils.timeDiffFormat((System.currentTimeMillis() - Main.last_hosted_1) / 1000, false);
        String lasthostedUser = Main.last_hosted_by_1;
        double tpsCount = Sponge.getServer().getTicksPerSecond();
        String tps = String.format("%.2f", tpsCount);
        int totalNum = onlinePlayers.size();
        String server = Main.serverName; //MiscEC Main


        if (!Utils.getAliases().get(server).contains(suppliedServer.toLowerCase())) {
            return;
        }


        for (Player player : onlinePlayers)
                for (String sRank : Utils.getStaffRanks())
                    if (player.hasPermission("group." + sRank) && !staffOnline.contains(player)) staffOnline.add(player);

      commandEvent.reply(staffOnline(staffOnline, tps, totalNum, lasthostedTime, lasthostedUser).build());
    }

    public static EmbedBuilder staffOnline(Queue<Player> staffOnline, String tps, int totalNum, String lasthostedTime, String lasthostedUser) {
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
        local.getDescriptionBuilder().append(String.format("\nTPS: "+ tps +"\n"));
        if (totalNum == 0) {
            local.getDescriptionBuilder().append("No Players Are Online!" + "\n");
        }   else {
            local.getDescriptionBuilder().append("Players Online: " + totalNum + "\n");
        }
            local.getDescriptionBuilder().append("Lasthosted " + lasthostedTime + " ago by " + lasthostedUser);
        return local;
    }

}