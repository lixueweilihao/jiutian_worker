package cyclicbarrier.runtest;

import java.util.concurrent.CyclicBarrier;

import cyclicbarrier.thread.Thread_02;

/**
 * CyclicBarrier 类测试
 * 
 * @author bc
 * @data 2018年9月29日
 */
public class RunTest_02 {

	public static void main(String[] args) throws InterruptedException {
		/**
		 * 创建一个新的 CyclicBarrier ，当给定数量的线程（线程）等待时，它将跳闸， 当屏障跳闸时执行给定的屏障动作，由最后一个进入屏障的线程执行。
		 */
		CyclicBarrier cbRef = new CyclicBarrier(2, new Runnable() {
			public void run() {
				System.out.println("都到了");
			}
		});

		Thread_02[] threads = new Thread_02[4];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread_02(cbRef);
			threads[i].start();
			Thread.sleep(2000);
		}

	}

}
