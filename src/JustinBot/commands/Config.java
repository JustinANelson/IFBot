package JustinBot.commands;

import JustinBot.bot.ConfigVars;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Config extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s");

        if(args[0].equalsIgnoreCase(ConfigVars.getPrefix() + "config")) {
            if(args.length == 1) {
                event.getChannel().sendMessage("Available commands are: \nprefix\nsetAddJoinRole\njoinRole\njoinLeaveChannel").queue();
                return;
            }

            //CHANGE PREFIX
            if(args[1].equalsIgnoreCase("prefix") ) {
                if(args.length < 2) {
                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessage("Your prefix is " + ConfigVars.getPrefix()).queue();
                }
                else {
                    ConfigVars.setPrefix(args[2]);
                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessage("Your new prefix is " + ConfigVars.getPrefix()).queue();
                }
            }

            //SET ADD JOIN ROLE ON JOIN
            if(args[1].equalsIgnoreCase("setAddJoinRole")) {
                if (args[2].equalsIgnoreCase("true")) {
                    ConfigVars.setAddRoleOnJoin(true);
                    event.getChannel().sendMessage("Set add join role to true.");
                }
                else if (args[2].equalsIgnoreCase("false")) {
                    ConfigVars.setAddRoleOnJoin(false);
                    event.getChannel().sendMessage("Set add join role to false.");
                }
            }

            //SET JOIN ROLE
            if(args[1].equalsIgnoreCase("joinRole")) {
                ConfigVars.setJoinRole(args[2]);
                event.getChannel().sendMessage("Set join-role as " + ConfigVars.getJoinRole()).queue();
            }

            //SET JOIN LEAVE CHANNEL
            if(args[1].equalsIgnoreCase("joinleave")) {
                ConfigVars.setJoinLeaveChannel(args[2]);
                event.getChannel().sendMessage("Set join-leave channel as " + ConfigVars.getJoinLeaveChannel()).queue();
            }
        }
    }
}