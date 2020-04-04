package cyclicbarrier.serviceimpl;

import cyclicbarrier.service.Service_Run;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * @author bc
 * @data 2018年9月29日
 */
public class Service_Run01 implements Service_Run {

	private CyclicBarrier cbRef;

	public Service_Run01(CyclicBarrier cbRef) {
		this.cbRef = cbRef;
	}

	public void beginRun() {
		try {
			Thread.sleep((int)(Math.random()*1000));
			System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " begin跑第1阶段 "
					+ (cbRef.getNumberWaiting() + 1));
			cbRef.await();
			System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " end跑第1阶段 "
					+ cbRef.getNumberWaiting() );
			//---
			Thread.sleep((int)(Math.random()*1000));
			System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " begin跑第2阶段 "
					+ (cbRef.getNumberWaiting() + 1));
			cbRef.await();
			System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " end跑第2阶段 "
					+ cbRef.getNumberWaiting() );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
