package DiscordIntegration;

import org.spongepowered.api.command.CommandSource;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String color(String string) {
        return org.spongepowered.api.text.serializer.TextSerializers.FORMATTING_CODE
                .serialize(org.spongepowered.api.text.Text.of(string));
    }

    public static void sendMessage(CommandSource sender, String message) {
        if (sender == null)
            return;
        sender.sendMessage(
                org.spongepowered.api.text.serializer.TextSerializers.FORMATTING_CODE.deserialize(color(message)));
    }

    public static List<String> getStaffRanks() {
        List<String> staff = new ArrayList<>();

        staff.add("Owner");
        staff.add("SeniorAdmin");
        staff.add("Admin");
        staff.add("SeniorMod");
        staff.add("Mod");
        staff.add("Helper");
        staff.add("ChatHelper");

        return staff;
    }

}
