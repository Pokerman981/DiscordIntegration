package DiscordIntegration.ServerCommands;

import DiscordIntegration.Main;
import net.dv8tion.jda.core.JDA;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

public class GlobalCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        String cmd = args.getOne(Text.of("command")).get().toString();
        JDA jda = Main.jda;
        jda.getGuildsByName("Pixelmon+", true).get(0).getTextChannelsByName("debug-logs", true).get(0).sendMessage("!gcmd " + cmd).submit();
        jda.getGuildsByName("Pixelmon+", true).get(0).getTextChannelsByName("debug-logs", true).get(0).sendMessage("The previous command was run by " + src.getName()).submit();

        return CommandResult.success();
    }
}
