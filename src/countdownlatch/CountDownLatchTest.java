
/**
 *  countdownlatch 是一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
	tDownLatch。一个线程调用 await()方法后，在当
	前计数到达 调用 countDown() 方法，会使计数器递减，
	所以，计数器的值为 0 后，会释放所有等待的线程。其他后续的 await 调用都将立即返回。
	这种现象只出现一次，因为计数无法被重置。如果需要重置计数，请考虑使用 CyclicBarrier。 
	用 同 步 工 具 ， 有 很 多 用 途 。 使 用 “ 1 ” 初 始 化 的
	CountDownLatch 用作一个简单的开/关锁存器，或入口：在通过调用 countDown() 的线程
	打 开
	操作之前一直等待，或者使其在某
	项操
	.3 CountDownLatch
	类 java.util.concurrent.CountDownLat
	用给定的数字作为计数器初始化 Coun
	零之前，会一直受阻塞。其他线程
	CountDownLatch 作 为 一 个 通
	入 口 前 ， 所 有 调 用  await 的 线 程 都 一 直 在 入 口 处 等 待 。 用  N 初 始 化 的
	CountDownLatch 可以使一个线程在 N 个线程完成某项
	作完成 N 次之前一直等待。 
	下面给出了两个类，其中一组 worker 线程使用了两个倒计数 CountDownLatch：
	第一个类是一个启动信号，在 driver 为继续执行 worker 做好准备之前，它会阻止所
	有的 worker 继续执行。 
	第二个类是一个完成信号，它允许 driver 在完成所有 worker 之前一直等待
 */
package countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
* 项目名称：java-concurrent   
* 类名称：CountDownLatchTest   
* 类描述：   利用 CountDownLatch 模拟 由主线程通知所有子线程开始工作,等所有子线程完成工作后主线程再继续执行
* 创建人：zhangwei
* 邮箱:  zhangwei_2943@163.com
* 创建时间：2015-1-26 下午3:06:43   
* 修改人：zhangwei
* 修改时间：2015-1-26 下午3:06:43   
* 修改备注：   
* @version    
*
 */
public class CountDownLatchTest {

	public static void main(String[] args) {
		try {
			int N =20;
			CountDownLatch startLatch = new CountDownLatch(1);
			CountDownLatch doneLatch  = new CountDownLatch(N);
			
			for(int i=0 ; i<N ; i++){
				
				Thread t = new Thread(new Work(startLatch, doneLatch, "thread-"+i));
				t.start();
			}
			System.out.println("----------------->所有子线程已经准备完毕");
			//等待5秒后通知所有线程开始执行
			Thread.sleep(5000);
			System.out.println("----------------->通知子线程开始执行");
			startLatch.countDown();
			
			//等待所有线程执行完成在执行主线程
			doneLatch.await();
			System.out.println("----------------->所有都执行完了");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class Work implements Runnable{
	
	private CountDownLatch startLatch;//开始执行通知
	private CountDownLatch doneLatch;//执行完成通知
	private String name;
	
	public Work(CountDownLatch start, CountDownLatch done, String name){
		this.startLatch = start;
		this.doneLatch = done;
		this.name = name;
		
	}
	@Override
	public void run() {
		try {
			//等待开始执行
			this.startLatch.await();
			this.work();//执行
			this.doneLatch.countDown();//标记执行完成
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 开始执行
	 */
	private void work(){
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("==============>线程["+this.name+"] 执行完成" );
	}
}
