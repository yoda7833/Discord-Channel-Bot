import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;

public class Main
{
  private static JDA jda;
  public static List<VoiceChannel> base;
  
  public Main() {}
  
  public static ArrayList<VoiceChannel> nonTemp = new ArrayList();
  
  public static void main(String[] args) { String gender = "Female";
    


    try
    {
      jda = new JDABuilder(net.dv8tion.jda.core.AccountType.BOT).addEventListener(new Object[] { new EventManager() }).addEventListener(new Object[] { new MessageListener() }).setToken("MzM4OTQyNzk2NTIwMDk1NzQ0.DFcwng.Ep83M3dNW-8BqsW6140KwbsBeNA").buildBlocking();
      jda.setAutoReconnect(true);
      Guild hivemind = (Guild)jda.getGuilds().get(1);
      jda.getPresence().setGame(net.dv8tion.jda.core.entities.Game.of("!help"));
      base = hivemind.getVoiceChannels();
      nonTemp.add((VoiceChannel)hivemind.getVoiceChannelsByName("Lobby", true).get(0));
      nonTemp.add((VoiceChannel)hivemind.getVoiceChannelsByName("AFK", true).get(0));
      nonTemp.add((VoiceChannel)hivemind.getVoiceChannelsByName("Mods", true).get(0));
      nonTemp.add((VoiceChannel)hivemind.getVoiceChannelsByName("Coaches", true).get(0));
    }
    catch (javax.security.auth.login.LoginException e) {
      e.printStackTrace();
    }
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    catch (net.dv8tion.jda.core.exceptions.RateLimitedException e) {
      e.printStackTrace();
    }
  }
}
