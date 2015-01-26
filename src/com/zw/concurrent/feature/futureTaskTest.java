/**
 * 表示异步计算的结果。它提供了检查计算是否完成的方
	法，以等待计算的完成，并获取计算的结果。计算完成后只能使用 get() 方法来获取结果，
	如有 则由 cancel 方法来执行。还提供了其他方法，
	以确 算完成，就不能再取消计算。如果为了可取消
	性而 、并返回 null 作
	为底层任务的结果
 * 1) boolean cancel(boolean mayInterruptIfRunning) ： 试图取消对此任务
	的执行。如果任务已完成、或已取消，或者由于某些其他原因而无法取消，则此尝试将失败。
	当调用 cancel 时，如果调用成功，而此任务尚未启动，则此任务将永不运行。如果任务已
	经启动，则 mayInterruptIfRunning 参数确定是否应该以试图停止任务的方式来中断执行此
	任务的线程。
	2) boolean isCancelled() ： 如果在任务正常完成前将其取消，则返回 true。
	3) boolean isDone() ：如果任务已完成，则返回 true。 可能由于正常终止、异常
	或取消而完成，在所有这些情况中，此方法都将返回 true。
	：如有
	必要，等待计算完成，然后获取其结果。
	5) V get(long timeout, TimeUnit unit) throws InterruptedException,
	ExecutionException, TimeoutException： 如有必要，最多等待为使计算完成所给
	定的时间之后，获取其结果（如果结果可用）
 * FutureTask 类 是 Future 的 一 个 实 现 ， 并 实 现 了 Runnable ， 所 以 可 通 过
	Exe
	塞主线程时，可以把这些作业
	交给
	，最后需要计算总额的时候再尝试去获得
	Priva
	cutor(线程池) 来执行。也可传递给Thread对象执行。
	如果在主线程中需要执行比较耗时的操作时，但又不想阻
	Future 对象在后台完成，当主线程将来需要时，就可以通过 Future 对象获得后台作业
	的计算结果或者执行状态。
	下面的例子模拟一个会计算账的过程，主线程已经获得其他帐户的总额了，为了不让主
	线程等待 PrivateAccount 类的计算结果的返回而启用新的线程去处理，并使用  FutureTask
	对象来监控，这样，主线程还可以继续做其他事情
	teAccount 的信息
 */
package com.zw.concurrent.feature;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


/**
*    
* 项目名称：java-concurrent   
* 类名称：FeatureTaskTest   
* 类描述：   使用featureTask 模拟异步执行的任务,
* 创建人：zhangwei
* 邮箱:  zhangwei_2943@163.com
* 创建时间：2015-1-26 下午3:42:05   
* 修改人：zhangwei
* 修改时间：2015-1-26 下午3:42:05   
* 修改备注：   
* @version    
*
 */
@SuppressWarnings("all")
public class futureTaskTest {

	public static void main(String[] args) {
		try {
			FutureTask futureTask = new FutureTask(new Task());
			//启动异步执行
			new Thread(futureTask).start();
			System.out.println("------------> 主线程继续干其他事情");
			
			//判断异步任务是否执行完成
//			Object result = futureTask.get();//一直等待无超时限制的
			Object result = futureTask.get(1, TimeUnit.SECONDS);//有超时的
			
			System.out.println("------------->异步执行结果："+ result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

@SuppressWarnings("all")
class Task implements Callable {
	
	@Override
	public Object call() throws Exception {
		System.out.println("------------>call 开始执行");
		Thread.sleep(3000);
		return 1000;
	}
	
}
