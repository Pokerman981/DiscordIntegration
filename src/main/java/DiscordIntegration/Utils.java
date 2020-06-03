package DiscordIntegration;

import org.spongepowered.api.command.CommandSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {
    // TODO Possibly use the methods already in MiscEC

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
        HashMap<String,ArrayList<String>> serverAliases = new HashMap<>();
        serverAliases.put("brawl", new ArrayList<>());
        serverAliases.get("brawl").add("pb");
        serverAliases.get("brawl").add("pokebrawl");
        serverAliases.get("brawl").add("brawl");

        serverAliases.put("legends", new ArrayList<>());
        serverAliases.get("legends").add("pl");
        serverAliases.get("legends").add("pokelegends");
        serverAliases.get("legends").add("legends");

        serverAliases.put("verse", new ArrayList<>());
        serverAliases.get("verse").add("pv");
        serverAliases.get("verse").add("pokeverse");
        serverAliases.get("verse").add("verse");

        serverAliases.put("club", new ArrayList<>());
        serverAliases.get("club").add("pc");
        serverAliases.get("club").add("pokeclub");
        serverAliases.get("club").add("club");

        serverAliases.put("dash", new ArrayList<>());
        serverAliases.get("dash").add("pd");
        serverAliases.get("dash").add("pokedash");
        serverAliases.get("dash").add("dash");

        serverAliases.put("pokezone", new ArrayList<>());
        serverAliases.get("pokezone").add("pz1");
        serverAliases.get("pokezone").add("pokezone1");
        serverAliases.get("pokezone").add("zone1");
        serverAliases.get("pokezone").add("pzb");
        serverAliases.get("pokezone").add("pokezoneb");
        serverAliases.get("pokezone").add("zoneb");
        serverAliases.get("pokezone").add("pzblue");
        serverAliases.get("pokezone").add("pokezoneblue");
        serverAliases.get("pokezone").add("zoneblue");
        serverAliases.get("pokezone").add("zone");
        serverAliases.get("pokezone").add("pz");
        serverAliases.get("pokezone").add("pokezone");

        serverAliases.put("redzone", new ArrayList<>());
        serverAliases.get("redzone").add("pz2");
        serverAliases.get("redzone").add("pokezone2");
        serverAliases.get("redzone").add("zone2");
        serverAliases.get("redzone").add("pzr");
        serverAliases.get("redzone").add("pokezoner");
        serverAliases.get("redzone").add("zoner");
        serverAliases.get("redzone").add("pzred");
        serverAliases.get("redzone").add("pokezonered");
        serverAliases.get("redzone").add("zonered");
        serverAliases.get("redzone").add("zone");
        serverAliases.get("redzone").add("pz");
        serverAliases.get("redzone").add("pokezone");

        return serverAliases;
    }
}
