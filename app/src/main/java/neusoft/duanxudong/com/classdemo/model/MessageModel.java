package neusoft.duanxudong.com.classdemo.model;

import java.util.List;

public class MessageModel {
	
	private List<Message> messages;
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "messages.size()"+messages.size();
	}
}
