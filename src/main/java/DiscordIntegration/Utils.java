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

        serverAliases.put("zone1", new ArrayList<>());
        serverAliases.get("zone1").add("pz1");
        serverAliases.get("zone1").add("pokezone1");
        serverAliases.get("zone1").add("zone1");
        serverAliases.get("zone1").add("pzb");
        serverAliases.get("zone1").add("pokezoneb");
        serverAliases.get("zone1").add("zoneb");
        serverAliases.get("zone1").add("pzblue");
        serverAliases.get("zone1").add("pokezoneblue");
        serverAliases.get("zone1").add("zoneblue");
        serverAliases.get("zone1").add("zone");
        serverAliases.get("zone1").add("pz");
        serverAliases.get("zone1").add("pokezone");

        serverAliases.put("zone2", new ArrayList<>());
        serverAliases.get("zone2").add("pz2");
        serverAliases.get("zone2").add("pokezone2");
        serverAliases.get("zone2").add("zone2");
        serverAliases.get("zone2").add("pzr");
        serverAliases.get("zone2").add("pokezoner");
        serverAliases.get("zone2").add("zoner");
        serverAliases.get("zone2").add("pzred");
        serverAliases.get("zone2").add("pokezonered");
        serverAliases.get("zone2").add("zonered");
        serverAliases.get("zone2").add("zone");
        serverAliases.get("zone2").add("pz");
        serverAliases.get("zone2").add("pokezone");

        return serverAliases;
    }
}
