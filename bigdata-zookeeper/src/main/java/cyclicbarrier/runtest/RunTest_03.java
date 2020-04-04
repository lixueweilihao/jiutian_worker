package cyclicbarrier.runtest;

import cyclicbarrier.thread.Thread_01;

import java.util.concurrent.CyclicBarrier;


/**
 * CyclicBarrier 类测试
 * 
 * getNumberWaiting() 返回目前正在等待障碍的各方的数量。
 * 
 * @author bc
 * @data 2018年9月29日
 */
public class RunTest_03 {

	public static void main(String[] args) throws InterruptedException {

		CyclicBarrier cbRef = new CyclicBarrier(3);

		Thread_01[] threads = new Thread_01[4];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread_01(cbRef);
			threads[i].start();
			Thread.sleep(2000);
			//目前受阻于各方的数量 await()
			System.out.println("目前正在等待障碍的各方的数量:"+cbRef.getNumberWaiting());
		}

	}

}
