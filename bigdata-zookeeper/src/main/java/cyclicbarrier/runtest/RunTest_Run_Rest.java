package cyclicbarrier.runtest;

import cyclicbarrier.serviceimpl.Service_Run_Test;
import cyclicbarrier.thread.Thread_Run;

/**
 * CyclicBarrier 类测试
 * reset() 将屏障重置为初始状态。 如果任何一方正在等待屏障，他们将返回BrokenBarrierException 。
 * @author bc
 * @data 2018年9月30日
 */
public class RunTest_Run_Rest {

	public static void main(String[] args) throws InterruptedException {

		Service_Run_Test service = new Service_Run_Test();

		Thread_Run[] threads = new Thread_Run[2];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread_Run(service);
			threads[i].start();
		}
		Thread.sleep(2000);
		//将屏障重置为初始状态。 如果任何一方正在等待屏障，他们将返回BrokenBarrierException 。
		service.cbRef.reset();
	}

}
