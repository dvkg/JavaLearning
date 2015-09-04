package mythread;

import java.lang.Thread;

public class Student extends Thread {
	
	private String name ;
	private Thread teaher;
	private BlackBoard board;
	
	public  Student(String name){
		super(name);
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//this.readFromBoardSynchronized();
		//this.readFromBoard();
		//this.readFromBoardPV();
		this.readFromBoardPV1();
	}
	
	private void readFromBoardPV1(){
		
		for(int i = 0; i < 3; i++){
			synchronized(this.board){
				//System.out.println("[" + this.name + "] :teacherNum=" + this.board.getTeacherNum() + " hasNew=" + this.board.isHasNewContent());
				while(this.board.isHasNewContent() == false){
					//System.out.println("[" + this.name + "] :teacherNum=" + this.board.getTeacherNum() + " hasNew=" + this.board.isHasNewContent());
					System.out.println("[" + this.name + "] 等待先生中。。。" );
					try {
						this.board.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("[" + this.name + "] :teacherNum=" + this.board.getTeacherNum() + " hasNew=" + this.board.isHasNewContent());
				}
				
				System.out.println("[" + this.name + "] 大声读：" + this.board.getContent());
				System.out.println("[" + this.name + "] 完成了：" + this.board.getContent() + "学习");
				
				this.board.setHasNewContent(false);
				this.board.notify();
			}
		}
	}
	
	/*
	 * PV是操作系统中的操作，感觉wait与notify就是PV操作，因此函数名带有PV
	 */
	private void readFromBoardPV(){
		synchronized(this)
		{	
			for(int i = 0 ; i < 5; i++){
				System.out.println("[" + this.name + "] 大声读：" + this.board.getContent());
				
				System.out.println("[" + this.name + "] 完成了：" + this.board.getContent() + "学习");
			}
		
			this.notify();
		}
	}
	
	private void readFromBoard(){
		String s ;
		for(int i = 0 ; i < 5; i++){
				//System.out.println("[" + this.name + "] 黑板上写着:" + this.board.getContent() )
				s = "[" + this.name + "] 黑板上写着:" + this.board.getContent();
				//Thread.yield();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("[" + this.name + "] 大声读:" + this.board.getContent() );
				s += ";大声读:" + this.board.getContent();
				System.out.println(s);
		}
	}
	
	/*
	 * 失败的例子
	 */
	private void readFromBoardSynchronized(){
		String s;
		for(int i = 0 ; i < 5; i++){
			synchronized (this.board){
				//System.out.println("[" + this.name + "] 黑板上写着:" + this.board.getContent() )
				s = "[" + this.name + "] 黑板上写着:" + this.board.getContent();
				//Thread.yield();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("[" + this.name + "] 大声读:" + this.board.getContent() );
				s += ";大声读:" + this.board.getContent();
				System.out.println(s);
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	private void say(){
		if(this.teaher != null){
			try {
				this.teaher.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i = 0; i < 5; i++){
			for(int k = 0; k < 10000000; k++){
				
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(this.name + " says:" + i);
		}
	}

	public Thread getTeacher() {
		return this.teaher;
	}

	public void setTeacher(Thread t) {
		this.teaher = t;
	}

	public BlackBoard getBoard() {
		return board;
	}

	public void setBoard(BlackBoard board) {
		this.board = board;
	}
	
	
	
}
