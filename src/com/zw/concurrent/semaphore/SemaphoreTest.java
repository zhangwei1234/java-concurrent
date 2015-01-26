
/**
 * 类 java.util.concurrent.Semaphore 提供了一个计数信号量，从概念上讲，信号量维护了一
	个许可集。如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。每个 release()
	添加一个许可，从而可能释放一个正在阻塞的获取者。但是，不使用实际的许可对象，
	这个示例程序创建
	Iterator 后 ， 分 别 在 其 中 加 入 一 个 元 素 。
	urre
	组事件监听器中的所有元素）是您需要的，那么最好使用 copy-on-write 集合。如果不
	使用的话，就还用原来的，并保证在出现异常时对它进行处理。 
	3.4 同步器
	3.4.1 Semaphore
	Semaphore 只对可用许可的号码进行计数，并采取相应的行动。 
	Semaphore 通常用于限制可以访问某些资源（物理或逻辑的）的线程数目。一般操作系
	原语，需要设置一个信号量表示可用资源的数量， P 原语就相当
	于 acquire()， V 原语就相当于 release()。
	统的进程调度中使用了 PV
	例如，下面的类使用信号量控制对内容池的访问，内容池的大小作为 Semaphore 的构造
	参数传递初始化许可的数目，每个线程获取数据之前必须获得许可，这样就限制了访问内容
	池的线程数目
 */
package com.zw.concurrent.semaphore;

/**
* 项目名称：java-concurrent   
* 类名称：SemaphoreTest   
* 类描述：   java并发包 “信号量” 测试
* 创建人：zhangwei
* 邮箱:  zhangwei_2943@163.com
* 创建时间：2015-1-26 上午11:49:36   
* 修改人：zhangwei
* 修改时间：2015-1-26 上午11:49:36   
* 修改备注：   
* @version    
*
 */
public class SemaphoreTest {

}
