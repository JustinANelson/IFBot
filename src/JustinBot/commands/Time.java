package JustinBot.commands;

import JustinBot.bot.ConfigVars;
import JustinBot.bot.IFBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

public class Time extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s");

        if (args[0].equalsIgnoreCase(ConfigVars.getPrefix() + "time")) {
            LocalDateTime ldt = LocalDateTime.now();

            EmbedBuilder time = new EmbedBuilder();
            time.setColor(0x66d8ff);
            time.setTitle("Times across the world");
            time.setImage("https://image.freepik.com/free-vector/polygonal-map-digital-globe-map-blue-polygons-earth-maps-world-internet-connection-3d-grid-illustration_102902-902.jpg");
            time.addField("New York:", tzf("America/New_York"), true);
            time.addField("Chicago:", tzf("America/Chicago"), true);
            time.addField("Denver:", tzf("America/Denver"), true);
            time.addField("Phoenix:", tzf("America/Phoenix"), true);
            time.addField("Los Angeles:", tzf("America/Los_Angeles"), true);
            time.addField("Anchorage:", tzf("America/Anchorage"), true);
            time.addField("Honolulu:", tzf("Pacific/Honolulu"), true);
            time.addField("London:", tzf("Europe/London"), true);
            time.addField("Stockholm:", tzf("Europe/Stockholm"), true);
            time.addField("Perth:", tzf("Australia/Perth"), true);
            time.addField("Adelaide:", tzf("Australia/Adelaide"), true);
            time.addField("Sydney:", tzf("Australia/Sydney"), true);
            time.addField("South Korea:", tzf("Asia/Seoul"), true);
            time.addField("Berlin:", tzf("Europe/Berlin"), true);
            time.addField("Hong Kong:", tzf("Asia/Hong_Kong"), true);

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage((time.build())).queue();
        }
    }

    public String tzf(String timeZone) {
        Date today = new Date();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy - HH:mm:ss z");
        df.setTimeZone(TimeZone.getTimeZone(timeZone));

        return df.format(today);
    };
}
