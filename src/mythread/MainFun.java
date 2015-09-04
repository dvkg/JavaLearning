package mythread;


public class MainFun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * 一、多线程创建
		 * 	有两种方法创建线程:
		 * 	1、扩展Thread类，重写run方法；
		 * 	2、实现Runnable接口，实现run方法。
		 * 
		 * 二、线程的启动
		 * 	线程的启动由Thread对象的stsart()方法启动，run()方法不能启动；若线程是实现Runnable接口创建的，Runnable对象
		 * 	也需由Thread对象包裹后，通过start()方法启动。
		 * 		
		 */
/*		Thread t1 = new Thread(new Teacher("王五"));
		Student s1 = new Student("张三");
		Student s2 = new Student("李四");
		

		t1.start();
		s1.start();
		s1.setName("学生线程张三");
		s2.start();*/
		
		/*
		 * Thread Info
		 * 1、每个线程都有名称有ID,线程名称可构造时传入或创建后设置
		 * 2、获取当前线程的方法 Thread.currentThread();
		 */
/*		ThreadHelper.printThreadInfo(Thread.currentThread());
		ThreadHelper.printThreadInfo(t1);
		ThreadHelper.printThreadInfo(s1);
		ThreadHelper.printThreadInfo(s2);*/
		
		/*
		 * Thread 状态
		 * 1、有5个状态：生（新状态或就绪）、死、可运行、运行、等待或阻塞或睡眠
		 * 2、一个线程不能重复启动，不能重复调用start()方法，否则java.lang.IllegalThreadStateException
		 * 3、start()调用后，进入可运行态
		 * 4、等待或阻塞状态在某些条件满足后，进行可运行状态
		 * 5、yield()方法将线程从运行态转换到可运行状态，尝试使其他线程获得运行的机会，但可能无效
		 * 6、t.join()将当前线程加入到t的尾部，t执行完后，当前线程方可执行
		 */
		Teacher GuiGuZi = new Teacher("鬼谷子");
		Student SunBin = new Student("孙膑");
		Student PangJuan  = new Student("庞涓");
		Thread GuiGuZiThread = new Thread(GuiGuZi);
		BlackBoard bd = new BlackBoard();
		GuiGuZi.setBoard(bd);
		SunBin.setBoard(bd);
		PangJuan.setBoard(bd);
/*		SunBin.setTeacher(GuiGuZiThread);
		PangJuan.setTeacher(GuiGuZiThread);*/
		
		
		/*
		 * 线程的同步与锁
		 * synchronized 加锁实现
		 * 所有两种：对象锁与类锁  对象锁为对象成员方法互斥访问使用，类锁为静态成员方法互斥访问使用
		 * 互斥访问关键区，保障一致读写
		 */
/*		BlackBoard bd = new BlackBoard();
		GuiGuZi.setBoard(bd);
		SunBin.setBoard(bd);
		PangJuan.setBoard(bd);*/
		
		/*
		 * 线程交互
		 * s.wait() 使调用者处于等待s线程状态，但需调用者拥有s对象的锁
		 * s.notify(),s.notifyAll() 唤醒s对象的等待对象，但需拥有s对象本身的锁
		 * s.wait() 调用后，调用者立即释放s对象的锁
		 * s.notify(),s.notifyAll()执行后，s并不一定立即释放锁
		 * 
		 * 切记  这些函数只能在同步的环境中调用，若两个线程本身就不同步，那不能使用，否者死锁
		 * 另外，由于notify并不能保证锁的释放，因此，wait与notify不能用于循环中的交互控制，只能用于一次性的交互控制
		 * wait只能保证当前时刻暂停线程，去等待另一个线程，若此刻，另一线程已启动或完成，此刻wait是没有意义的
		 * 等待时需加上是否满足条件的判断
		 * 
		 * 可以配合线程休眠来控制
		 * 
		 * 不管程序员怎么编写调度，只能最大限度的影响线程执行的次序，而不能做到精准控制
		 * 
		 * 例子：老师鬼谷子给孙膑庞涓教授课程，老师与学生相互等待并唤醒
		 */	
		/*BlackBoard bd = new BlackBoard();
		GuiGuZi.setBoard(bd);
		SunBin.setBoard(bd);
		//PangJuan.setBoard(bd);
		
		//SunBin.start();
		//PangJuan.start();
		GuiGuZiThread.start();
		
			synchronized(GuiGuZiThread){
				try {
					Thread.sleep(9000);
					System.out.println("--------------等待老师教授课程----------------");
					GuiGuZiThread.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			System.out.println("--------------老师教授完成，可以学习----------------");
			synchronized(SunBin){
				try {
					SunBin.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
		
		/*
		 * 重新生产者-消费者-仓存 问题
		 * 
		 * notifyAll() 方法，起到的是一个通知作用，不释放锁，也不获取锁。只是告诉该对象上等待的
		 * 线程“可以竞争执行了，都醒来去执行吧”。
		 */
		SunBin.start();
		GuiGuZiThread.start();
		
		
		
/*		SunBin.start();
		PangJuan.start();
		GuiGuZiThread.start();*/
		
		
		
		
	}

	
}
