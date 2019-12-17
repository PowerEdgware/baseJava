package juc.future;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FutureDemo1 {

	static Executor executor = Executors.newFixedThreadPool(16);

	static Executor common = ForkJoinPool.commonPool();

	public static void main(String[] args) {

		// 1.异步执行，等待结果
		// supplyAsync(common);

		// 2.执行完成，传递参数，去异步执行，转换结果
		// thenApply(common);

		// 3.组合执行结果 ：不算并行
		// compose_combile(common);

		// 4.并行处理:并行执行多个异步任务，组合结果
		parallelApply(common);

		try {
			System.in.read();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void parallelApply(Executor executor) {
		Supplier<String> supplier = () -> {
			System.out.println("future1 execute by thread=" + Thread.currentThread().getName());
			LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(500));
			return String.valueOf(System.currentTimeMillis());
		};
		CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier, executor);

		CompletableFuture<String> another = CompletableFuture.supplyAsync(() -> {
			LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(700));
			System.out.println(" another by thread=" + Thread.currentThread().getName());
			return "abc";
		});
		//
		long start = System.currentTimeMillis();
		CompletableFuture<Void> paralleled = CompletableFuture.allOf(future, another);
		try {
			// 这个方法不会合并结果，可以看到他的返回值是 Void 类型
			Void nullValue = paralleled.get();
			System.out.println(nullValue);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		// 手动处理异步执行的结果
		String compbiled_result = Stream.of(future, another).map(CompletableFuture::join)
				.collect(Collectors.joining(" "));

		long end = System.currentTimeMillis();
		System.out.println("compbiled_result=" + compbiled_result + " costs=" + (end - start) + " ms");
	}

	private static void compose_combile(Executor executor) {
		Supplier<String> supplier = () -> {
			System.out.println(" compose execute by thread=" + Thread.currentThread().getName());
			LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(500));
			return String.valueOf(System.currentTimeMillis());
		};
		CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier, executor);

		CompletableFuture<String> another = CompletableFuture.supplyAsync(() -> {
			return "abc";
		});
		CompletableFuture<String> combiledFuture = future.thenComposeAsync(t -> CompletableFuture.supplyAsync(() -> {
			System.out.println(" another execute by thread=" + Thread.currentThread().getName());
			LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(300));
			return t + "\t" + 1;
		}));
		// future.thenComposeAsync(new Function<String,
		// CompletionStage<String>>() {
		// @Override
		// public CompletionStage<String> apply(String t) {
		// return another;
		// }
		// });
		// combiledFuture = future.thenCombine(another, (v1, v2) -> {
		// System.out.println(v1 + " and " + v2);
		// return v1 + "\t" + v2;
		// });
		try {
			long start = System.currentTimeMillis();
			String result = combiledFuture.get();
			System.out.println("result=" + result + " costs=" + (System.currentTimeMillis() - start) + " ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static void thenApply(Executor executor) {
		Supplier<String> supplier = () -> {
			System.out.println(" supply execute by thread=" + Thread.currentThread().getName());
			LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(500));
			// int x = 1 / 0;
			return String.valueOf(System.currentTimeMillis());
		};
		CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier, executor);

		Function<String, Long> fn = (value) -> {
			System.out.println(" fn execute by thread=" + Thread.currentThread().getName());
			LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200));

			return Long.parseLong(value);
		};
		CompletableFuture<Long> convertFuture = future.thenApplyAsync(fn);

		// 获取结果(正常获取结果)
		Long result;
		try {
			result = convertFuture.get();
			System.out.println(result + " rrr");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static void supplyAsync(Executor executor) {
		Supplier<String> supplier = () -> {
			LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(500));
			return String.valueOf(System.currentTimeMillis());
		};
		CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier, executor);

		String result = future.join();// 同步获取结果
		System.out.println("result=" + result);
	}

}
