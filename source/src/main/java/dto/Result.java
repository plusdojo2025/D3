package dto;

import java.io.Serializable;

public class Result implements Serializable {
	private String message; // メッセージ
	private String backTo; // 戻り先

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBackTo() {
		return backTo;
	}

	public void setBackTo(String backTo) {
		this.backTo = backTo;
	}

	public Result() {
		this(null, null);
	}

	public Result(String message, String backTo) {
		this.message = message;
		this.backTo = backTo;
	}
}
