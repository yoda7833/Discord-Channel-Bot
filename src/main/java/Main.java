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
      jda = new JDABuilder(net.dv8tion.jda.core.AccountType.BOT).addEventListener(new EventManager()).addEventListener(new MessageListener()).setToken("MzM4OTQyNzk2NTIwMDk1NzQ0.DFcwng.Ep83M3dNW-8BqsW6140KwbsBeNA").buildBlocking();
      jda.setAutoReconnect(true);
      Guild hivemind = (Guild)jda.getGuilds().get(1);
      jda.getPresence().setGame(net.dv8tion.jda.core.entities.Game.of("!help"));
      base = hivemind.getVoiceChannels();
      nonTemp.add(hivemind.getVoiceChannelById(329178290583764992L));//mods
      nonTemp.add(hivemind.getVoiceChannelById(329178290562531328L));//AFK
      nonTemp.add(hivemind.getVoiceChannelById(364546837102723072L));//ULol
      nonTemp.add(hivemind.getVoiceChannelById(364546414535114755L));//lobby
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
