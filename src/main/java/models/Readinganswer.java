package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "readinganswer")
public class Readinganswer {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "no", nullable = false)
	private int no;

	@Column(name = "question", nullable = false)
	private int question;

	@Column(name = "answer")
	private String answer;

	public Readinganswer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getQuestion() {
		return question;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Readinganswer(String id, int no, int question, String answer) {
		super();
		this.id = id;
		this.no = no;
		this.question = question;
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Readinganswer [id=" + id + ", no=" + no + ", question=" + question + ", answer=" + answer + "]";
	}
}
