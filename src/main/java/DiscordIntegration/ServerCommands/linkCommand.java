package DiscordIntegration.ServerCommands;

import DiscordIntegration.Main;
import DiscordIntegration.Utils;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import java.io.IOException;
import java.util.Random;

public class linkCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        boolean linked = !Main.config().getNode("linked-info", src.getIdentifier()).isVirtual();

        if (!src.hasPermission("discord.donator")) {
            Utils.sendMessage(src, "&cYou must be a donator to run this command!");
            return CommandResult.empty();
        }
        if (linked) {
            Utils.sendMessage(src, "&cYou've already requested a pin!");
            return CommandResult.empty();
        }

        Random random = new Random();
        String pin = String.format("%06d", random.nextInt(100000));

        Utils.sendMessage(src, "&aTo finish linking your profile message the server chat bot and type and '!link (minecraft name) (pin)' in any channel on the poke-brawl discord");
        Utils.sendMessage(src, "&aYour custom pin is " + pin + ". Write it down.");

        Main.config().getNode("linked-info", src.getIdentifier(), "pin").setValue(pin);
        Main.config().getNode("linked-info", src.getIdentifier(), "name").setValue(src.getName());

        try {Main.getInstance().save();} catch (IOException e) {e.printStackTrace();}

        Main.jda.getGuildById("291940579875618816").getTextChannelById("532785983960121354").sendMessage("Name: %player%\nPin: %pin%".replaceAll("%player%", src.getName()).replaceAll("%pin%", pin)).queue();

        return CommandResult.success();
    }
}
