/**
 * synchronized修饰方法和synchronized修饰代码块
 * @author Youngni
 *
 */
public class SynchronizedTest {

	//对象锁
	public void test() throws InterruptedException {
		//传入 this 表示当前对象
		synchronized (this) {
			int i = 5;
			while( i-- > 0) {
				System.out.println("test"+Thread.currentThread().getName()+"："+i);
				Thread.sleep(2000);
			}
		}
	}
	
	public synchronized void test1() throws InterruptedException {
			int i = 5;
			while( i-- > 0) {
				System.out.println("test1"+Thread.currentThread().getName()+"："+i);
				Thread.sleep(2000);
			}
	}
	
	public static void main(String[] args) throws InterruptedException {
		final SynchronizedTest test = new SynchronizedTest();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					test.test();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					test.test1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}
