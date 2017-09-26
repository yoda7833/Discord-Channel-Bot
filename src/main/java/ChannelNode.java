import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.voice.*;

public class ChannelNode {
	private VoiceChannel channel;
	private ChannelNode next;
	private ChannelNode prev;
	public ChannelNode(VoiceChannel myChannel, ChannelNode myNext, ChannelNode myPrev)
	{
		channel = myChannel;
		next = myNext;
		prev = myPrev;
	}
	public ChannelNode getPrevious()
	{
		
		return prev;
	}
	public ChannelNode setPrevious(ChannelNode set)
	{
		ChannelNode temp = prev;
		prev = set;
		return temp;
	}
	public  ChannelNode getNext()
	{
		return next;
	}
	public ChannelNode setNext(ChannelNode set)
	{
		ChannelNode temp = next;
		next = set;
		return temp;
	}
	public VoiceChannel getChannel()
	{
		return channel;
	}
}
