import net.dv8tion.jda.core.entities.VoiceChannel;

public class ChannelList {
	private ChannelNode first;
	public ChannelList(VoiceChannel mother)
	{
		first = new ChannelNode(mother, null,null);
		first.setNext(first);
		first.setPrevious(first);
	}
	public VoiceChannel getFirst()
	{
		return first.getChannel();
	}
	public VoiceChannel getLast()
	{
		return first.getPrevious().getChannel();
	}
	public void add(VoiceChannel set)
	{
		if(first.getNext()==first)
		{
			ChannelNode temp = new ChannelNode(set, first, first);
			first.setPrevious(temp);
			first.setNext(temp);
			first = temp;
		}
		else
		{
			ChannelNode temp = new ChannelNode(set, first,first.getPrevious());
			first.getPrevious().setNext(temp);
			first.setPrevious(temp);
			first = temp;
		}
	}
	public boolean remove(VoiceChannel search)
	{
		if(first.getChannel().equals(search))
		{
			return false;
		}
		ChannelNode current = first.getNext();
		while(!current.getChannel().equals(first.getChannel()))
		{
			if(current.getChannel().equals(search))
			{
				current.getPrevious().setNext(current.getNext());
				current.getNext().setPrevious(current.getPrevious());
				return true;
			}
			current =current.getNext();
		}
		return false;
	}
}
