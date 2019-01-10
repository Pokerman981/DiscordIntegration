package DiscordIntegration.DiscordCommands;

import DiscordIntegration.Main;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;

public class ExecuteServerCommand extends Command {

    public ExecuteServerCommand(){
        this.name = "command";
        this.guildOnly = true;
        this.userPermissions = new Permission[]{Permission.ADMINISTRATOR};
        this.hidden = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        String command = event.getArgs();

        Task.builder()
                .execute(task -> Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command))
                .submit(Main.getInstance());
        event.reactSuccess();
    }
}
