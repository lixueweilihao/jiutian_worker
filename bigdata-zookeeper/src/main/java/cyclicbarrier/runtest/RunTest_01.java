package cyclicbarrier.runtest;


import cyclicbarrier.thread.Thread_01;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 类测试
 * 允许一组线程全部等待彼此达到共同屏障点的同步辅助。 循环阻塞在涉及固定大小的线程方的程序中很有用，这些线程必须偶尔等待彼此。 屏障被称为循环
 * ，因为它可以在等待的线程被释放之后重新使用。
 * 
 * @author bc
 * @data 2018年9月29日
 */
public class RunTest_01 {

	public static void main(String[] args) {
		/**
		 * 创建一个新的 CyclicBarrier ，当给定数量的线程（线程）等待时，它将跳闸， 当屏障跳闸时执行给定的屏障动作，由最后一个进入屏障的线程执行。
		 */
		CyclicBarrier cbRef = new CyclicBarrier(5, new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("都到了");
			}
		});

		Thread_01[] threads = new Thread_01[5];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread_01(cbRef);
			threads[i].start();
		}

	}

}
