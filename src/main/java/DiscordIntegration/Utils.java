package DiscordIntegration;

import org.spongepowered.api.command.CommandSource;

import java.util.ArrayList;
import java.util.HashMap;
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

    public static HashMap<String,ArrayList<String>> getAliases() {
        HashMap<String,ArrayList<String>> serverAliases = new HashMap<String,ArrayList<String>>();
        serverAliases.put("pokebrawl", new ArrayList<String>());
        serverAliases.put("pokelegends", new ArrayList<String>());
        serverAliases.put("pokeverse", new ArrayList<String>());
        serverAliases.put("pokeclub", new ArrayList<String>());
        serverAliases.put("pokedash", new ArrayList<String>());
        serverAliases.get("pokebrawl").add("pb");
        serverAliases.get("pokebrawl").add("pokebrawl");
        serverAliases.get("pokelegends").add("pl");
        serverAliases.get("pokelegends").add("pokelegends");
        serverAliases.get("pokeverse").add("pv");
        serverAliases.get("pokeverse").add("pokeverse");
        serverAliases.get("pokeclub").add("pc");
        serverAliases.get("pokeclub").add("pokeclub");
        serverAliases.get("pokedash").add("pd");
        serverAliases.get("pokedash").add("pokedash");

        return serverAliases;
    }
}
