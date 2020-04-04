package cyclicbarrier.thread;

import cyclicbarrier.service.Service_Run;

/**
 * CyclicBarrier 类TEST 模拟赛跑
 * 
 * @author bc
 * @data 2018年9月29日
 */
public class Thread_Run extends Thread {

	private Service_Run service;

	public Thread_Run(Service_Run service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.beginRun();
	}
}
