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
	public ChannelNode getFirstNode()
	{
		return first;
	}
	public VoiceChannel getLast()
	{
		return first.getPrevious().getChannel();
	}
	public ChannelNode getLastNode()
	{
		return first.getPrevious();
	}
	public boolean contains(VoiceChannel search)
	{
		ChannelNode current = first;
		if(current.getChannel().equals(search))
			return true;
		current = current.getNext();
		while(current!=first)
		{
			if(current.getChannel().equals(search))
				return true;
		}
		return false;
	}
	public void add(VoiceChannel set)
	{
		if(first.getNext()==first)
		{
			ChannelNode temp = new ChannelNode(set, first, first);
			//System.out.println(first.getChannel().getPosition());
			//set.getGuild().getController().modifyVoiceChannelPositions().moveTo(1).queue();//out of bounds error here
			int firstPos = first.getChannel().getPosition();
			set.getGuild().getController().modifyVoiceChannelPositions().selectPosition(set).moveTo(firstPos).queue();
			first.getChannel().getManager().setName(first.getChannel().getName()+" 1").queue();
			first.setPrevious(temp);
			first.setNext(temp);
			first = temp;
		}
		else
		{
			ChannelNode temp = new ChannelNode(set, first,first.getPrevious());
			set.getGuild().getController().modifyVoiceChannelPositions().selectPosition(set).moveTo(first.getChannel().getPosition()).queue();
			first.getChannel().getManager().setName(first.getChannel().getName()+" 1").queue();
			ChannelNode current = first.getNext();
			first.getPrevious().setNext(temp);
			first.setPrevious(temp);
			first = temp;
			int nameNumber =2;
			while(current!=first)
			{
				first.getChannel().getManager().setName(first.getChannel().getName().substring(first.getChannel().getName().length()-2)+nameNumber).queue();
				nameNumber++;
				current = current.getNext();
			}
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
				renameAfter(current);
				current.getChannel().delete().queue();
				current.getPrevious().setNext(current.getNext());
				current.getNext().setPrevious(current.getPrevious());
				return true;
			}
			current =current.getNext();
		}
		return false;
	}
	private void renameAfter(ChannelNode after)
	{
		ChannelNode current = after.getNext();
		int nameNumber = -1;
		if(after.getChannel().equals(first.getChannel()))
			nameNumber = 1;
		else
		{
			char temp = after.getChannel().getName().charAt(after.getChannel().getName().length()-1);
			nameNumber = temp-1;
		}
		while(current!=first)
		{
			first.getChannel().getManager().setName(first.getChannel().getName().substring(first.getChannel().getName().length()-2)+nameNumber).queue();
		}
	}
	public void clear()
	{
		ChannelNode current = first.getNext();
		while(!current.getChannel().equals(first.getChannel()))
		{
			remove(current.getChannel());
		}
	}
}
