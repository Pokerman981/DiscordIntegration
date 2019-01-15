package DiscordIntegration.DiscordCommands;

import DiscordIntegration.Main;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.service.user.UserStorageService;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CheckPinCommand extends Command {

    public CheckPinCommand() {
        this.name = "checkpin";
        this.requiredRole = "staff";
    }

    @Override
    protected void execute(CommandEvent event) {
        String[] command = event.getArgs().split(" ");
        if (command.length != 1) {
            event.reply("You must supply a username!");
            return;
        }


        Task.builder()
                .execute(task -> {
                    Optional<UserStorageService> userStorage = Sponge.getServiceManager().provide(UserStorageService.class);
                    if (!userStorage.get().get(command[0]).isPresent()) {
                        event.reply("This play does not exist!");
                        return;
                    }

                    String uuid = userStorage.get().get(command[0]).get().getIdentifier();
                    String pin = Main.rootNode.getNode("linked-info", uuid, "pin").getString();

                    try {
                        event.getChannel().sendMessage(command[0] + "'s pin is " + pin + ". This message will delete in 10 seconds.").submit().get().delete().queueAfter(10,TimeUnit.SECONDS);
                    } catch (InterruptedException | ExecutionException e1) {
                        e1.printStackTrace();
                    }

                })
                .submit(Main.getInstance());
    }
}
