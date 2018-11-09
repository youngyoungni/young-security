/**
 * 
 * @author Youngni
 *
 */
public class ManyThreadTest {

	/**
	 * 实现Runnable接口比继承Thread类所具有的优势：

		1）：适合多个相同的程序代码的线程去处理同一个资源
		
		2）：可以避免java中的单继承的限制
		
		3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立
		
		4）：线程池只能放入实现Runable或callable类线程，不能直接放入继承Thread的类
	 * @throws InterruptedException 

	 */
	
	public static void main(String[] args) throws InterruptedException {

	
		System.out.println(Thread.currentThread().getName()+"主线程序运行中*******************");
		Thread1 t1 = new Thread1("A");
		t1.start();
		Thread1 t2 = new Thread1("B");
		t2.start();
//		t1.join();	//主线程需要等到子线程处理完毕后在结束
//		new Thread(new Runnable1("A")).start();
//		new Thread(new Runnable1("B")).start();
		System.out.println(Thread.currentThread().getName()+"主线程序运行结束---------------------");
	}
	  
}

class Runnable1 implements Runnable{
	
	private String name;

	public Runnable1(String name){
		this.name = name;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "线程运行中**********************");
		for(int i = 1 ; i < 5 ; i++) {
			System.out.println("子线程："+this.name+""+i+"运行中");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + "线程运行结束-----------------------");
	}
	
}

class Thread1 extends Thread{
	private String name;

	public Thread1(String name){
		this.name = name;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"线程运行中");
		for(int i = 1 ; i < 5 ; i++) {
			System.out.println(this.name+":"+i+"运行中");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"线程运行结束");

	}
}
