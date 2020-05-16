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

        serverAliases.put("zone", new ArrayList<>());
        serverAliases.get("zone").add("pz");
        serverAliases.get("zone").add("pokezone");
        serverAliases.get("zone").add("zone");


        return serverAliases;
    }
}
