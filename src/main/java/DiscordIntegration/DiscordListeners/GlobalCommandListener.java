package DiscordIntegration.DiscordListeners;

import DiscordIntegration.Main;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;

public class GlobalCommandListener implements EventListener {
    /**
     * This method is called whenever an event occurs of the type for which
     * the <code> EventListener</code> interface was registered.
     *
     * @param evt The <code>Event</code> contains contextual information
     *            about the event. It also contains the <code>stopPropagation</code>
     *            and <code>preventDefault</code> methods which are used in
     *            determining the event's flow and default action.
     */
    @Override
    public void onEvent(Event evt) {

        if (evt instanceof GuildMessageReceivedEvent) {
            GuildMessageReceivedEvent event = (GuildMessageReceivedEvent) evt;
            String[] command = event.getMessage().getContentRaw().split(" ");
            if (!command[0].equals("!gcmd")) return;
            if (!event.getAuthor().isBot()) return;

            Task.builder()
                    .execute(task -> Sponge.getCommandManager().process(Sponge.getServer().getConsole(), event.getMessage().getContentRaw().replaceAll("!gcmd ", "")))
                    .submit(Main.getInstance());


        }

    }
}