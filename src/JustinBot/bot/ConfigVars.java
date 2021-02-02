package JustinBot.bot;

import JustinBot.commands.Config;

import java.awt.image.ConvolveOp;

public class ConfigVars {
    public static String prefix = "~";
    public static Boolean addRoleOnJoin = false;
    public static String joinRole = "Member";
    public static String joinLeaveChannel = "";

    public static Boolean tagOnNewJoin;
    public static String newJoinTag;

    public static Boolean getAddRoleOnJoin() { return addRoleOnJoin; }
    public static String getPrefix() { return prefix; }
    public static String getJoinRole() { return joinRole; }
    public static String getJoinLeaveChannel() { return joinLeaveChannel; }
    public static Boolean getTagOnNewJoin() { return tagOnNewJoin; }
    public static String getNewJoinTag() { return newJoinTag; }

    public static void setAddRoleOnJoin(Boolean addRoleOnJoin) { ConfigVars.addRoleOnJoin = addRoleOnJoin; }
    public static void setPrefix(String prefix) { ConfigVars.prefix = prefix; }
    public static void setJoinRole(String joinRole) { ConfigVars.joinRole = joinRole; }
    public static void setJoinLeaveChannel(String joinLeaveChannel) { ConfigVars.joinLeaveChannel = joinLeaveChannel; }
    public static void setTagOnNewJoin(Boolean tagOnNewJoin) { ConfigVars.tagOnNewJoin = tagOnNewJoin; }
    public static void setNewJoinTag(String newJoinTag) { ConfigVars.newJoinTag = newJoinTag; }

}
