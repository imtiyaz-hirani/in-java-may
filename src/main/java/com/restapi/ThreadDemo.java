package com.restapi;

public class ThreadDemo {
	public static void main(String[] args) throws Exception{
		MyService service = new MyService(); //100X
		Thread myThread1 = new Thread(service,"MyThread-ABC");
		myThread1.start(); //result: 0
		Thread myThread2 = new Thread(service,"MyThread-LMN");
		myThread2.start(); //result: 100
		myThread1.join();//delaying main thread
		myThread2.join();//delaying main thread
		System.out.println(service.getResult());//? 39000000
		
	}
}

class MyService implements Runnable{
	long result = 1; 
	public long doSomething() throws Exception{
		
		synchronized("") {
			System.out.println(Thread.currentThread().getName());
		for(int i=0;i<10000;i++) {
			result = result + i; 
			Thread.sleep(1);
		}
		}
		return result; 
	}

	@Override
	public void run() {
		try {
			doSomething();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long getResult() {
		return result;
	}
	
	
}

//main : synchronization