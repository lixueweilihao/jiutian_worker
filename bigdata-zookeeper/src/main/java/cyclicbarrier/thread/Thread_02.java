package cyclicbarrier.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 类TEST
 * 
 * @author bc
 * @data 2018年9月29日
 */
public class Thread_02 extends Thread {

	private CyclicBarrier cbRef;

	public Thread_02(CyclicBarrier cbRef) {
		super();
		this.cbRef = cbRef;
	}

	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + " begin:" + System.currentTimeMillis()+"等待凑齐2个继续进行");
			// 等待所有 parties已经在这个障碍上调用了 await 。
			cbRef.await();
			System.out.println(Thread.currentThread().getName() + " end:" + System.currentTimeMillis()+"已经凑齐2个继续进行");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
