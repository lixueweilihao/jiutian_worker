package cyclicbarrier.runtest;

import cyclicbarrier.serviceimpl.Service_Run04;
import cyclicbarrier.thread.Thread_Run;

/**
 * CyclicBarrier 类测试
 * getParties()
 * @author bc
 * @data 2018年9月30日
 */
public class RunTest_Run04 {

	public static void main(String[] args) throws InterruptedException {

		Service_Run04 service = new Service_Run04();

		Thread_Run[] threads = new Thread_Run[3];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread_Run(service);
			threads[i].start();
		}
		Thread.sleep(2000);
		//返回旅行这个障碍所需的parties数量。
		System.out.println("屏蔽对象的parties个数为："+service.cbRef.getParties());
		System.out.println("在屏蔽处等待的线程个数为："+service.cbRef.getNumberWaiting());
	}

}
