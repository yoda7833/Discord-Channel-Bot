import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.voice.*;

public class ChannelMain {
	private VoiceChannel channel;
	private VoiceChannel next;
	private VoiceChannel prev;
	public ChannelMain(VoiceChannel myChannel)
	{
		channel = myChannel;
	}
	public VoiceChannel getPrevious()
	{
		
		return prev;
	}
	public VoiceChannel setPrevious(VoiceChannel set)
	{
		VoiceChannel temp = prev;
		prev = set;
		return temp;
	}
	public VoiceChannel getNext()
	{
		return next;
	}
	public VoiceChannel setNext(VoiceChannel set)
	{
		VoiceChannel temp = next;
		next = set;
		return temp;
	}
	public VoiceChannel getChannel()
	{
		return channel;
	}
}
