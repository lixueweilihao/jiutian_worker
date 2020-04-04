package cyclicbarrier.runtest;

import java.util.concurrent.CyclicBarrier;

import cyclicbarrier.serviceimpl.Service_Run01;
import cyclicbarrier.thread.Thread_Run;

/**
 * CyclicBarrier 类测试
 * 
 * getNumberWaiting() 返回目前正在等待障碍的各方的数量。
 * 
 * @author bc
 * @data 2018年9月29日
 */
public class RunTest_Run01 {

	public static void main(String[] args) throws InterruptedException {

		CyclicBarrier cbRef = new CyclicBarrier(2);
		
		Service_Run01 service = new Service_Run01(cbRef);

		Thread_Run[] threads = new Thread_Run[4];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread_Run(service);
			threads[i].start();
		}

	}

}
