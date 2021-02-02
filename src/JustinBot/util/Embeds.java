package JustinBot.util;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.Arrays;

public class Embeds {


    public static EmbedBuilder embeds(String string) {
        EmbedBuilder eb = new EmbedBuilder();
        String[] args = string.split("\\s+");
        System.out.println(args[0]); //Debug
        System.out.println(args[1]); //Debug
        eb.setTitle(args[0]);
        eb.setColor(Color.RED);
        eb.addField(args[1], args[1], true);

        return eb;
    }

}
