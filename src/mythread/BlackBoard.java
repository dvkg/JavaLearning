package mythread;

public class BlackBoard {
	private String content;
	private int teacherNum;
	private int StudentNum;
	private boolean hasNewContent;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		
	}

	public int getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(int teacherNum) {
		this.teacherNum = teacherNum;
	}

	public int getStudentNum() {
		return StudentNum;
	}

	public void setStudentNum(int studentNum) {
		StudentNum = studentNum;
	}

	public boolean isHasNewContent() {
		return hasNewContent;
	}

	public void setHasNewContent(boolean hasNewContent) {
		this.hasNewContent = hasNewContent;
	}
	
}
