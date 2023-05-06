package com.restapi;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

class Employee {
	private Long id;
	private String name;
	private double salary;

	public Employee(Long id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}

}

public class FutureDemo {
	public static void main(String[] args) throws Exception {
		Employee e1 = new Employee(1L, "harry potter", 125000);
		Employee e2 = new Employee(2L, "hermione granger", 215000);
		Employee e3 = new Employee(3L, "ronald weasley", 185000);

		List<Employee> list = Arrays.asList(e1, e2, e3);

		/*
		 * I want to update these objects using threads independently Future<Employee>
		 * future = new EmployeeService().updateEmployeeSalary(e1); Future<Employee>
		 * future2 = new EmployeeService().updateEmployeeSalary(e2); Future<Employee>
		 * future3 = new EmployeeService().updateEmployeeSalary(e3);
		 * while(!(future.isDone() && future2.isDone() && future3.isDone())) {
		 * System.out.println("Futures updating..."); Thread.sleep(200); }
		 * System.out.println(future.get()); System.out.println(future2.get());
		 * System.out.println(future3.get()); System.out.println("Futures  completed");
		 */
		ForkJoinPool forkPool = new ForkJoinPool();
		CompletableFuture<List<Employee>> future= CompletableFuture.supplyAsync(()->{
			//do the task : 300000 objects update
			try {
				forkPool.submit(()->{
					list.parallelStream().forEach(e->e.setSalary(e.getSalary()*1.2));
				});
			}
			catch(RuntimeException e) {
				
			}
			finally {
				forkPool.shutdown();
			}
			return list; 
		});  
		
		System.out.println(future.get());
	}
}
//ForkJoinPool 

class EmployeeService {
	private static ExecutorService executor = Executors.newFixedThreadPool(4);
	// stick to newCachedThreadPool() : let kvm maintain the pools and threads.
	// if thread is inactive for 60 secs, there is an entry in docs about
	// terminating it..

	Future<Employee> updateEmployeeSalary(Employee employee) {
		CompletableFuture<Employee> cf = new CompletableFuture();
		executor.submit(() -> {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			employee.setSalary(employee.getSalary() * 1.1);
			cf.complete(employee);
		});
		return cf;
	}
}

/*
 * In CompletableFuture, we can handle exceptions, we can check the completion
 * status, and there are lots of other operations that can be performed which
 * are not available in Future. (refer the docs)
 */
