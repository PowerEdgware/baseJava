package juc.future;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CompletableFutureDemo {
	static Object blocker = new Object();

	void init() {
		// CompletableFuture
	}

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		Supplier<String> supplier_01 = () -> {
			LockSupport.parkNanos(blocker, TimeUnit.SECONDS.toNanos(2));
			return "supplier_01";
		};
		CompletableFuture<String> future01 = CompletableFuture.supplyAsync(supplier_01);

		Supplier<String> supplier_02 = () -> {
			LockSupport.parkNanos(blocker, TimeUnit.SECONDS.toNanos(1));
			return "supplier_02";
		};
		CompletableFuture<String> future02 = CompletableFuture.supplyAsync(supplier_02);

		List<CompletableFuture<String>> futrues = new ArrayList<>();
		futrues.add(future01);
		futrues.add(future02);

		Function<Void, List<String>> function = (v -> {
			return futrues.stream().map(CompletableFuture::join).collect(Collectors.toList());
		});
		List<String> result = CompletableFuture.allOf(futrues.toArray(new CompletableFuture[futrues.size()]))
				.thenApply(function).join();
		long cost = System.currentTimeMillis() - start;

		System.out.println(result + " cost=" + cost + " ms");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Object o= CompletableFuture.allOf(futrues.toArray(new
		// CompletableFuture[0]))
		// .thenApply(v ->
		// futrues.stream().map(CompletableFuture::join).collect(Collectors.toList())).join();
	}
}
