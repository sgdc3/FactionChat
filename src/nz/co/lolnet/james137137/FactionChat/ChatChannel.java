package nz.co.lolnet.james137137.FactionChat;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.struct.Rel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
public class ChatChannel {

    private FactionChat factionChat;
    private static Player[] onlinePlayerList;

    ChatChannel(FactionChat aThis) {
        factionChat = aThis;
    }
    
    private static String FormatString (String message, String[] args)
    {
        if (args!=null)
        {
          for (int i = 0; i < args.length; i++) {
            message = message.replace(("{"+i+"}"), args[i]);
            }  
        }
        
            message = message.replaceAll("&",""+(char)167);
        
        return message;
    }

    /**
     *
     * Returns player's Factions Name.
     *
     */
    protected static String getFactionName(Player player) {
        return ((FPlayer) FPlayers.i.get(player)).getFaction().getTag();
    }

    /**
     *
     * Returns player's Factions Name.
     *
     */
    protected static String getFactionName(FPlayer fPlayer) {
        return fPlayer.getFaction().getTag();
    }

    /**
     *
     * Returns player's Factions Title.
     *
     */
    protected static String getPlayerTitle(Player player) {
        String title = ((FPlayer) FPlayers.i.get(player)).getTitle();
        if (title.length() > 0) {
            title += "-";
        }
        return title;
    }

    /*
     * Sends a message to the player's Faction only.
     */
    protected void fchat(Player player, String message) {


        String senderFaction = getFactionName(player); //obtains player's faction name

        
        if (senderFaction.contains("Wilderness")) { //checks if player is in a faction
            player.sendMessage(ChatColor.RED + FactionChat.messageNotInFaction);
            ChatMode.fixPlayerNotInFaction(player);
            return;

        }
        String[] intput1 = {senderFaction,player.getName(),message};
        String[] input2 = {FormatString(FactionChat.FactionChatMessage, intput1)};
        String normalMessage = FormatString(FactionChat.FactionChatMessage, intput1);
        String spyMessage = FormatString(FactionChat.SpyChat,input2);
        for (Player myPlayer : Bukkit.getServer().getOnlinePlayers()) {


            if (getFactionName(myPlayer).equalsIgnoreCase(senderFaction) && myPlayer.hasPermission("FactionChat.FactionChat")) {
                myPlayer.sendMessage(normalMessage);
            } else if (ChatMode.isSpyOn(myPlayer)) {
                
                myPlayer.sendMessage(spyMessage);
            }
        }






    }

    /*
     * Sends a message to the player's Faction 
     * and everyone that is in a Faction that is ally or truce with the player's Faction.
     */
    protected void fchata(Player player, String message) {

        String sSenderFaction = getFactionName(player); //obtains player's faction name

        if (sSenderFaction.contains("Wilderness")) { //checks if player is in a faction
            player.sendMessage(ChatColor.RED + FactionChat.messageNotInFaction);
            ChatMode.fixPlayerNotInFaction(player);
            return;

        }
        
        String[] intput1 = {sSenderFaction,player.getName(),message};
        String[] input2 = {FormatString(FactionChat.AllyChat, intput1)};
        String normalMessage = FormatString(FactionChat.AllyChat, intput1);
        String spyMessage = FormatString(FactionChat.SpyChat,input2);

        FPlayer fSenderPlayer = (FPlayer) FPlayers.i.get(player);
        Faction SenderFaction = fSenderPlayer.getFaction();

        for (Player myPlayer : Bukkit.getServer().getOnlinePlayers()) {


            FPlayer fplayer = (FPlayer) FPlayers.i.get(myPlayer);


            if ((SenderFaction.getRelationTo(fplayer) == Rel.ALLY || SenderFaction.getRelationTo(fplayer) == Rel.TRUCE
                    || sSenderFaction.equalsIgnoreCase(getFactionName(fplayer)))
                    && myPlayer.hasPermission("FactionChat.AllyChat")) {
                fplayer.sendMessage(normalMessage);
            } else if (ChatMode.isSpyOn(myPlayer)) {
                fplayer.sendMessage(spyMessage);
            }
        }
    }
    
    public void fchatao(Player player, String message) {
        String sSenderFaction = getFactionName(player); //obtains player's faction name

        if (sSenderFaction.contains("Wilderness")) { //checks if player is in a faction
            player.sendMessage(ChatColor.RED + FactionChat.messageNotInFaction);
            ChatMode.fixPlayerNotInFaction(player);
            return;

        }
        
        String[] intput1 = {sSenderFaction,player.getName(),message};
        String[] input2 = {FormatString(FactionChat.AllyOnlyChat, intput1)};
        String normalMessage = FormatString(FactionChat.AllyOnlyChat, intput1);
        String spyMessage = FormatString(FactionChat.SpyChat,input2);

        FPlayer fSenderPlayer = (FPlayer) FPlayers.i.get(player);
        Faction SenderFaction = fSenderPlayer.getFaction();

        for (Player myPlayer : Bukkit.getServer().getOnlinePlayers()) {


            FPlayer fplayer = (FPlayer) FPlayers.i.get(myPlayer);


            if ((SenderFaction.getRelationTo(fplayer) == Rel.ALLY
                    || sSenderFaction.equalsIgnoreCase(getFactionName(fplayer)))
                    && myPlayer.hasPermission("FactionChat.AllyChat")) {
                fplayer.sendMessage(normalMessage);
            } else if (ChatMode.isSpyOn(myPlayer)) {
                fplayer.sendMessage(spyMessage);
            }
        }
    }
    
    public void fchatTruce(Player player, String message) {
        String sSenderFaction = getFactionName(player); //obtains player's faction name

        if (sSenderFaction.contains("Wilderness")) { //checks if player is in a faction
            player.sendMessage(ChatColor.RED + FactionChat.messageNotInFaction);
            ChatMode.fixPlayerNotInFaction(player);
            return;

        }
        
        String[] intput1 = {sSenderFaction,player.getName(),message};
        String[] input2 = {FormatString(FactionChat.TruceChat, intput1)};
        String normalMessage = FormatString(FactionChat.TruceChat, intput1);
        String spyMessage = FormatString(FactionChat.SpyChat,input2);

        FPlayer fSenderPlayer = (FPlayer) FPlayers.i.get(player);
        Faction SenderFaction = fSenderPlayer.getFaction();

        for (Player myPlayer : Bukkit.getServer().getOnlinePlayers()) {


            FPlayer fplayer = (FPlayer) FPlayers.i.get(myPlayer);


            if ((SenderFaction.getRelationTo(fplayer) == Rel.TRUCE
                    || sSenderFaction.equalsIgnoreCase(getFactionName(fplayer)))
                    && myPlayer.hasPermission("FactionChat.AllyChat")) {
                fplayer.sendMessage(normalMessage);
            } else if (ChatMode.isSpyOn(myPlayer)) {
                fplayer.sendMessage(spyMessage);
            }
        }
    }

    /*
     * Sends a message to the player's Faction 
     * and everyone that is in a Faction that enermies with the player's Faction.
     */
    protected void fchatE(Player player, String message) {

        String sSenderFaction = getFactionName(player); //obtains player's faction name

        if (sSenderFaction.contains("Wilderness")) { //checks if player is in a faction
            player.sendMessage(ChatColor.RED + FactionChat.messageNotInFaction);
            ChatMode.fixPlayerNotInFaction(player);
            return;

        }

        FPlayer fSenderPlayer = (FPlayer) FPlayers.i.get(player);
        Faction SenderFaction = fSenderPlayer.getFaction();
        String[] intput1 = {sSenderFaction,player.getName(),message};
        String[] input2 = {FormatString(FactionChat.EnemyChat, intput1)};
        String normalMessage = FormatString(FactionChat.EnemyChat, intput1);
        String spyMessage = FormatString(FactionChat.SpyChat,input2);

        for (Player myPlayer : Bukkit.getServer().getOnlinePlayers()) {


            FPlayer fplayer = (FPlayer) FPlayers.i.get(myPlayer);


            if (SenderFaction.getRelationTo(fplayer) == Rel.ENEMY || sSenderFaction.equalsIgnoreCase(getFactionName(fplayer))) {
                fplayer.sendMessage(normalMessage);
            } else if (ChatMode.isSpyOn(myPlayer)) {
                fplayer.sendMessage(spyMessage);
            }
        }

    }

    
    
    
    protected void fchato(CommandSender sender, String[] args) {

        Player player = (Player) sender;//get player


        if (args.length <= 1) { //checks if there is a message in command eg "hello world" in /fchat hello world
            player.sendMessage(FactionChat.messageFchatoMisstype);
        } else {


            String senderFaction = getFactionName(player);


            if (senderFaction.contains("Wilderness")) { //checks if player is in a faction
                player.sendMessage(ChatColor.RED + FactionChat.messageNotInFaction);
                //start of sending the message
                /*
                 * checks every player online to see if they belong in the senders faction if so it receives the message
                 * 
                 * added a admin chatspy with permision faction.spy
                 */
            } else {
                String message = "";
                for (int i = 1; i < args.length; i++) {
                    message += args[i] + " ";
                }

                //this could be used in replaced of below (5 lines below)
                /*
                 FPlayer fSenderPlayer = (FPlayer)FPlayers.i.get(player);
                 Faction SenderFaction = fSenderPlayer.getFaction();
                 SenderFaction.sendMessage(ChatColor.DARK_GREEN + "[" + sSenderFaction + ChatColor.DARK_GREEN + "] " + ChatColor.RESET + player.getName() + ": " + message);
                 SenderFaction.getRelationTo(fSenderPlayer);
                 * */


                onlinePlayerList = Bukkit.getServer().getOnlinePlayers(); //get list of every online player
                String playersFaction; //creates string outside loop
                String targetFaction = args[0] + senderFaction.charAt(senderFaction.length() - 2) + senderFaction.charAt(senderFaction.length() - 1);
                
                int count = 0;
                String[] intput1 = {senderFaction,player.getName(),message};
                String[] input2 = {FormatString(FactionChat.OtherFactionChatSpy, intput1)};
                String toMessage = FormatString(FactionChat.OtherFactionChatTo, intput1);
                String FromMessage = FormatString(FactionChat.OtherFactionChatFrom, intput1);
                String spyMessage = FormatString(FactionChat.SpyChat,input2);
                //start of loop
                for (int i = 0; i < onlinePlayerList.length; i++) {

                    playersFaction = getFactionName(onlinePlayerList[i]);


                    if (playersFaction.equalsIgnoreCase(senderFaction)) {
                        onlinePlayerList[i].sendMessage(toMessage);
                    } else if (playersFaction.equalsIgnoreCase(targetFaction)) {
                        onlinePlayerList[i].sendMessage(FromMessage);
                        count++;
                    } else if (ChatMode.isSpyOn(onlinePlayerList[i])) {
                        onlinePlayerList[i].sendMessage(spyMessage);
                    }

                }

                if (count == 0) {
                    player.sendMessage(ChatColor.RED + FactionChat.messageFchatoNoOneOnline);
                }
            }
        }



    }
}