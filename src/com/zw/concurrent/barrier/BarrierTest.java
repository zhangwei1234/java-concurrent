/**
 * 在实际应用中，有时候需要多个线程同时工作以完成同一件事情，而且在完成过程中，
	往往会等所有线程都到达某一个阶段后再统一执行。
	比如有几个旅行团需要途经深圳、广州、最后到达重庆。旅行团中有自驾游的、有徒步
	的、有乘坐旅游大巴的；这些旅行团同时出发，并且每到一个目的地，都要等待其他旅行团
	到达此地后再同时出发，直到都到达终点站重庆。
	这时候 java.util.concurrent.CyclicBarrier 就可以派上用场。一个同步辅助类，它允许
	 (common barrier point)。在涉及一组固定大小
	的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
	因为该 barrier 在释放等待线程后可以重用，所以称它为循环的 barrier。 CyclicBarrier 最
	重要的属性就是参与者个数，另外最要方法是 await()。当所有线程都调用了 await()后，就
	从程序的运行结
	后面的线程调用
	注意，调用 acq
	将信号量初始化为
	3.4.2 Barrier
	一组线程互相等待，直到到达某个公共屏障点
	表示
	运行一次。若在继续所有参与线程之前
	更新
	这些线程都可以继续执行，否则就会等待。
	CyclicBarrier 支持一个可选的 Runnable 命令，在一组线程中的最后一个线程到达之后
	（但在释放所有线程之前），该命令只在每个屏障点
	共享状态，此屏障操作有用。
	上面提到的旅行团问题，可以用下面的程序实现，在程序中，某一个旅行团先到达某一
	个中转站后，调用 await()方法等待其他旅行团，都到齐后，执行 Runnable
 */
package com.zw.concurrent.barrier;

import java.util.concurrent.CyclicBarrier;

/**
* 项目名称：java-concurrent   
* 类名称：BarrierTest   
* 类描述：   利用barrier 多线程同步复制功能
* 创建人：zhangwei
* 邮箱:  zhangwei_2943@163.com
* 创建时间：2015-1-26 下午2:43:18   
* 修改人：zhangwei
* 修改时间：2015-1-26 下午2:43:18   
* 修改备注：   
* @version    
*
 */
public class BarrierTest {

	public static void main(String[] args) {
		
		//主线程
		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println("==================>所有节点复制完成");
			}
		};
		
		CyclicBarrier barrier = new CyclicBarrier(10, runnable);
		
		for(int i=0; i<10; i++){
			Thread t = new Thread(new CopyToNode(barrier, "node-"+i));
			t.start();
		}
	}
}

//负责复制内容到其他节点
class CopyToNode implements Runnable{
	private CyclicBarrier barrier;
	private String node;
	
	public CopyToNode(CyclicBarrier barrier, String node){
		this.barrier = barrier;
		this.node = node;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println("========>节点["+this.node+"] 数据已经复制成功");
			this.barrier.await();//标示已经完成
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}