package models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "listening")
public class Listening {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name = "topic", nullable = false)
	private String topic;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Listening(String id, String topic) {
		super();
		this.id = id;
		this.topic = topic;
	}
	
	public Listening() {
		super();
	}
}
