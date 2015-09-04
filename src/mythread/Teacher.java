package mythread;

import java.util.ArrayList;
import java.util.List;

public class Teacher implements Runnable {

	private String name ;
	private BlackBoard board;
	private List<Student> students;
	public Teacher(String name ){
		this.name = name;
		this.students = new ArrayList<Student> ();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//this.teachOnBoard();
		//this.teachOnBoardSynchronized();
		//this.teachOnBoardPV();
		this.teachOnBoardPV1();
	}
	
	private void teachOnBoardPV1(){
		String [] ct = new String[]{
				"兵者，国之大事。",
				"实则虚之，虚则实之。",
				"三十六计走为上。"
		};
		
		//老师就绪
		synchronized(this.board){
			this.board.setTeacherNum(1);
		}
		//准备内容
		for(int i = 0; i < ct.length ; i++){
			//老师就绪
			synchronized(this.board){
				//首先判断学生是否学习过
				while(this.board.isHasNewContent() == true){
					try {
						this.board.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				this.setBoardContent(ct[i]);
				this.board.setHasNewContent(true);
				
				System.out.println("[" + this.name + "] 教授:" + this.board.getContent());
				System.out.println("[" + this.name + "] :teacherNum=" + this.board.getTeacherNum() + " hasNew=" + this.board.isHasNewContent());
				this.board.notify();
			}
			
		}
		
	}
	
	private void teachOnBoardPV(){
		String [] ct = new String[]{
				"兵者，国之大事。",
				"实则虚之，虚则实之。",
				"三十六计走为上。"
		};
		for(int i = 0; i < ct.length ; i++){
			//老师就绪
			this.setBoardContent(ct[i]);
			
			//通知学生 内容准备好，可以进行学习
				System.out.println("[" + this.name + "] 教授:" + this.board.getContent());
		}
		
		synchronized(this){
			this.notifyAll();
		}
	}
	
	private void teachOnBoard(){
		String [] ct = new String[]{
				"兵者，国之大事。",
				"实则虚之，虚则实之。",
				"三十六计走为上。"
		};
		for(int i = 0; i < ct.length ; i++){
			this.setBoardContent(ct[i]);
			System.out.println("[" + this.name + "] 教授:" + this.board.getContent());
			//Thread.yield();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void teachOnBoardSynchronized(){
		String [] ct = new String[]{
				"兵者，国之大事。",
				"实则虚之，虚则实之。",
				"三十六计走为上。"
		};
		for(int i = 0; i < ct.length ; i++){
			synchronized(this.board){
				this.setBoardContent(ct[i]);
				System.out.println("[" + this.name + "] 教授:" + this.board.getContent());
				//Thread.yield();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setBoardContent(String ct){
		this.board.setContent(ct);
	}
	
	public void addStudent(Student s){
		this.students.add(s);
	}
	
	private void teaches(){
		for(int i = 0; i < 5; i++){
			for(int k = 0; k < 10000000; k++){
				
			}
			System.out.println(this.name + " teaches:" + i);
		}
	}
	public BlackBoard getBoard() {
		return board;
	}
	public void setBoard(BlackBoard board) {
		this.board = board;
	}

}
