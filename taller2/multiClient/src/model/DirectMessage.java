package model;

public class DirectMessage {
	
	public String type="DirectMessage";
	private String id;
	private String body;
	private String clientId;
	
	
	
	public DirectMessage(String id, String body, String clientId) {
		super();
		this.id = id;
		this.body = body;
		this.clientId = clientId;
	}
	
	public DirectMessage() {}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	
}
