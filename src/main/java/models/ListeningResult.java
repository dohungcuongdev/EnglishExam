package models;

public class ListeningResult {
	private String studentAnswer;
	private String correctAnswser;
	private String result;

	public String getStudentAnswer() {
		return studentAnswer;
	}

	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}

	public String getCorrectAnswser() {
		return correctAnswser;
	}

	public void setCorrectAnswser(String correctAnswser) {
		this.correctAnswser = correctAnswser;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ListeningResult(String studentAnswer, String correctAnswser, String result) {
		super();
		this.studentAnswer = studentAnswer;
		this.correctAnswser = correctAnswser;
		this.result = result;
	}

	@Override
	public String toString() {
		return "ListeningResult [studentAnswer=" + studentAnswer + ", correctAnswser=" + correctAnswser + ", result="
				+ result + "]";
	}

}
