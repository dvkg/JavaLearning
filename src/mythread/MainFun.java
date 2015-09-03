package mythread;


public class MainFun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * 一、多线程创建
		 * 		有两种方法创建线程:
		 * 		1、扩展Thread类，重写run方法；
		 * 		2、实现Runnable接口，实现run方法。
		 * 
		 * 二、线程的启动
		 * 		线程的启动由Thread对象的stsart()方法启动，run()方法不能启动；若线程是实现Runnable接口创建的，Runnable对象
		 * 		也需由Thread对象包裹后，通过start()方法启动。
		 * 		
		 */
		Thread t1 = new Thread(new Teacher("王五"));
		
		Student s1 = new Student("张三");
		Student s2 = new Student("李四");
		
		t1.start();
		s1.start();
		s2.start();
	}

}
