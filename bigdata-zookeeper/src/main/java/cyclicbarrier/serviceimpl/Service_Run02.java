package cyclicbarrier.serviceimpl;

import cyclicbarrier.service.Service_Run;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * @author bc
 * @data 2018年9月30日
 */
public class Service_Run02 implements Service_Run {

	private CyclicBarrier cbRef;

	public Service_Run02(CyclicBarrier cbRef) {
		this.cbRef = cbRef;
	}

	public void beginRun(int count) {
		try {
			System.out.println(Thread.currentThread().getName() + " 到了，等待其他人到了开始起跑");
			if (Thread.currentThread().getName().equals("Thread-2")) {
				System.out.println("thread-2进来了");
				Thread.sleep(5000);
//				Integer.parseInt("a");// 报错
				Thread.currentThread().interrupt();//中断
			}
			cbRef.await();
			System.out.println("都到了，开始跑");
			Thread.sleep((int) (Math.random() * 1000));
			System.out.println(Thread.currentThread().getName() + " 到达终点，并结束第" + count + "赛段");
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() +"进入InterruptedException e:" + cbRef.isBroken());
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			System.out.println(Thread.currentThread().getName() +"进入BrokenBarrierException e:" + cbRef.isBroken());
			e.printStackTrace();
		}
	}
	
	public void beginRun() {
		// TODO Auto-generated method stub
		
	}

}
