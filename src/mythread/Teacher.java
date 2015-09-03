package mythread;

public class Teacher implements Runnable {

	private String name ;
	public Teacher(String name ){
		this.name = name;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 5; i++){
			for(int k = 0; k < 10000000; k++){
				
			}
			System.out.println(this.name + " teaches:" + i);
		}
	}

}
