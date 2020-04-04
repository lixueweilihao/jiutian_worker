package cyclicbarrier.runtest;

import java.util.concurrent.CyclicBarrier;

import cyclicbarrier.serviceimpl.Service_Run02;
import cyclicbarrier.thread.Thread_Run02;

/**
 * CyclicBarrier 类测试
 * isBroken 查询这个障碍是否处于破碎状态。
 * true如果一个或多个参与方因施工或最后一次重置而导致中断或超时，或由于异常而导致屏障动作失败，则从此出现障碍; false否则。
 * @author bc
 * @data 2018年9月30日
 */
public class RunTest_Run02 {

	public static void main(String[] args) throws InterruptedException {

		CyclicBarrier cbRef = new CyclicBarrier(4, new Runnable() {
			public void run() {
				System.out.println("都到了");
			}
		});
		
		Service_Run02 service = new Service_Run02(cbRef);

		Thread_Run02[] threads = new Thread_Run02[4];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread_Run02(service);
			threads[i].start();
		}

	}

}
