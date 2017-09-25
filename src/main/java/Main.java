
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.JDABuilder;

public class Main {
	private static JDA jda;
	public static List<VoiceChannel> channels; 
	public static ArrayList<VoiceChannel> base;
	public static void main(String[] args) {
		String gender = "Female"; //the bot is a she and you should respect that
		//test-bot token: MzM4NTg1MDc3Mzk3MTkyNzA0.DFXjgQ.i5jyKTdpTeQ6AS8ssDuVCr3gqxE
		//Blake Bot token: MzM4OTQyNzk2NTIwMDk1NzQ0.DFcwng.Ep83M3dNW-8BqsW6140KwbsBeNA
		String testBot = "MzM4NTg1MDc3Mzk3MTkyNzA0.DFXjgQ.i5jyKTdpTeQ6AS8ssDuVCr3gqxE";
		String BlakeBot = "MzM4OTQyNzk2NTIwMDk1NzQ0.DFcwng.Ep83M3dNW-8BqsW6140KwbsBeNA";
		
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(testBot).buildBlocking();
			//jda = new JDABuilder(AccountType.BOT).addEventListener(new EventManager()).addEventListener(new MessageListener()).setToken(testBot).buildBlocking();
			jda.setAutoReconnect(true);
			/*Guild hivemind =jda.getGuilds().get(1);
			jda.getPresence().setGame(Game.of("!help"));
			channels  = hivemind.getVoiceChannels();
			base.add(hivemind.getVoiceChannelsByName("lobby", true).get(0));
			base.add(hivemind.getVoiceChannelsByName("afk", true).get(0));
			base.add(hivemind.getVoiceChannelsByName("mods", true).get(0));*/
			
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RateLimitedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
