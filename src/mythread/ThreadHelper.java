package mythread;

public class ThreadHelper {

	public static void printThreadInfo(Thread t){
		System.out.println("线程名称:" + t.getName());
		System.out.println("线程ID:" + t.getId());
	}
}
