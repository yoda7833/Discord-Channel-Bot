
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
	public static ArrayList<Long> base = new ArrayList<Long>();
	public static void main(String[] args) {
		String gender = "Female"; //the bot is a she and you should respect that
		//test-bot token: MzM4NTg1MDc3Mzk3MTkyNzA0.DFXjgQ.i5jyKTdpTeQ6AS8ssDuVCr3gqxE
		//Blake Bot token: MzM4OTQyNzk2NTIwMDk1NzQ0.DFcwng.Ep83M3dNW-8BqsW6140KwbsBeNA
		String testBot = "MzM4NTg1MDc3Mzk3MTkyNzA0.DFXjgQ.i5jyKTdpTeQ6AS8ssDuVCr3gqxE";
		String BlakeBot = "MzM4OTQyNzk2NTIwMDk1NzQ0.DFcwng.Ep83M3dNW-8BqsW6140KwbsBeNA";
		
		try {
			//jda = new JDABuilder(AccountType.BOT).setToken(testBot).buildBlocking();
			jda = new JDABuilder(AccountType.BOT).addEventListener(new MessageListener()).setToken(testBot).buildBlocking();
			jda.setAutoReconnect(true);
			Guild hivemind =jda.getGuildById("329171118063747072");
			jda.addEventListener(new EventManager(hivemind));
			jda.getPresence().setGame(Game.of("!help"));
			base.add(329176499871809537L);//Lobby
			base.add(329178290562531328l);//afk
			base.add(329178290583764992l);//mods
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
