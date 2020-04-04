package cyclicbarrier.serviceimpl;

import cyclicbarrier.service.Service_Run;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * @author bc
 * @data 2018年9月30日
 */
public class Service_Run03 implements Service_Run {

	private CyclicBarrier cbRef = new CyclicBarrier(3, new Runnable() {

		public void run() {
			System.out.println("彻底结束了" + System.currentTimeMillis());

		}
	});

	public void beginRun() {
		try {
			System.out.println(Thread.currentThread().getName() + " 准备" + System.currentTimeMillis());
			if (Thread.currentThread().getName().equals("Thread-0")) {
				System.out.println("thread-0执行了:cbRef.await(5, TimeUnit.SECONDS)");
				cbRef.await(5, TimeUnit.SECONDS);
			}
			if (Thread.currentThread().getName().equals("Thread-1")) {
				System.out.println("thread-1执行了:cbRef.await()");
				cbRef.await();
			}
			System.out.println(Thread.currentThread().getName() + " 开始！ " + System.currentTimeMillis());
		} catch (InterruptedException e) {// 如果当前线程在等待时中断
			System.out.println(Thread.currentThread().getName() + "进入InterruptedException");
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// 如果当前线程正在等待或 另一个线程被重置，或者 await被调用时屏障被打破，或者屏蔽动作（如果存在）由于异常而失败，则线程被中断或超时
			System.out.println(Thread.currentThread().getName() + "进入BrokenBarrierException");
			e.printStackTrace();
		} catch (TimeoutException e) {// 如果超过指定的超时。 在这种情况下，障碍将被破坏。
			System.out.println(Thread.currentThread().getName() + "进入TimeoutException");
			e.printStackTrace();
		}
	}

}
