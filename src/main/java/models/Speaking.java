/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "speaking")
public class Speaking {

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

	@Override
	public String toString() {
		return "Speaking [id=" + id + ", topic=" + topic + "]";
	}

	public Speaking(String id, String topic) {
		super();
		this.id = id;
		this.topic = topic;
	}

	public Speaking() {
		super();
	}

}
