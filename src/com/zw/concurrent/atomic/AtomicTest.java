/**
 * java.util.concurrent.atomic 包中添加原子变量类。所有原子变量类都公开“ 比较并设置”
	原语 语都是使用平台上可用的最快本机结构（比较并交换、
	供了原子变量的 9 种风格（ AtomicInteger、 AtomicLong、 olean、
	原子整型、长型、 及原子标记引用和戳记引用类的数组形式， 。
	volatile volatile
	条件的比较并设置更新。读取和写入原子变量与读取和写入对 tile 变量的访问具有相同
	“ 无等待” 的。 “无锁定算法” 要求某个线程总是执行操作。（无等待的另一种定义是
	或速度。这一限制可以是系统中线程数的函数；例如，如果有 10 个线程，每个线程都执行
	一次 操作，最坏的情况下，每个线程将必须重试最多九次，才能完
	） 
	再过去的
	级别，进行诸如线程和进程调度等任务。虽然它们的实现比较复杂，但相对于基于锁的备选
	算法，它们有许多优点：可以避免优先级倒置和死锁等危险，竞争比较便宜，协调发生在更
	（与比较并交换类似），这些原
	加载链接/条件存储，最坏的情况下是旋转锁）来实现的。  java.util.concurrent.atomic 包中提
	AtomicReference、 AtomicBo
	引用、 其原子地更新一对值）
	原子变量类可以认为是 变量的泛化，它扩展了 变量的概念，来支持原子
	vola
	的存取语义。 
	虽然原子变量类表面看起来与 SynchronizedCounter 例子一样，但相似仅是表面的。在
	表面之下，原子变量的操作会变为平台提供的用于并发访问的硬件原语，比如比较并交换。
	更多
	调整具有竞争的并发应用程序的可伸缩性的通用技术是降低使用的锁对象的粒度，希望
	的锁请求从竞争变为不竞争。从锁转换为原子变量可以获得相同的结果，通过切换为更
	细粒度的协调机制，竞争的操作就更少，从而提高了吞吐量
 */
package com.zw.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
* 项目名称：java-concurrent   
* 类名称：AtomicTest   
* 类描述：   利用原子变量实现多线程计数器
* 创建人：zhangwei
* 邮箱:  zhangwei_2943@163.com
* 创建时间：2015-1-26 上午11:26:40   
* 修改人：zhangwei
* 修改时间：2015-1-26 上午11:26:40   
* @version    
*
 */
public class AtomicTest {

	private AtomicInteger value = new AtomicInteger();
	
	public void add(int i) {
		value.addAndGet(i);
	}
	
	public int value(){
		return value.incrementAndGet();
	}
	
	public static void main(String[] args) {
		
		AtomicTest test = new AtomicTest();
		
		for (int i=0;i<20 ; i++){
			new Thread(new AtomicRun(test)).start();
		}
	}
	
}

class AtomicRun implements Runnable{
	
	AtomicTest test ;
	public AtomicRun (AtomicTest t) {
		this.test = t;
	}
	
	@Override
	public void run() {
		int i = test.value();
		System.out.println("-------->"+i);
	}
}
