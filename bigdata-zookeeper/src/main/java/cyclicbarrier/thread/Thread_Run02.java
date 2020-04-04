package cyclicbarrier.thread;

import cyclicbarrier.serviceimpl.Service_Run02;

/**
 * CyclicBarrier 类TEST 模拟赛跑
 * 
 * @author bc
 * @data 2018年9月30日
 */
public class Thread_Run02 extends Thread {

	private Service_Run02 service;

	public Thread_Run02(Service_Run02 service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.beginRun(4);
	}
}
