//package juc.parallel;
//
//import java.util.Set;
//import java.util.concurrent.CompletableFuture;
//
//import org.apache.commons.lang3.tuple.Pair;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import cc.pmtv.rcmd.vo.ResultItem;
//
//public abstract class FutureableDataLoader<T> {
//
//	private static Logger log = LoggerFactory.getLogger(FutureableDataLoader.class);
//
//	public CompletableFuture<ResultItem<T>> loadData(int idx, String tag, long uid, long curOffset, boolean newer,
//			int maxPerPage, int maxRetry, Set<Long> filterPids) {
//		CompletableFuture<ResultItem<T>> future = CompletableFuture.supplyAsync(() -> {
//
//			ResultItem<T> resultItem = new ResultItem<>();
//			resultItem.setIdx(idx);
//			resultItem.setTag(tag);
//
//			long maxOffset = curOffset;
//
//			long minOffset = Long.MAX_VALUE;
//
//			for (int i = 0; i < maxRetry; i++) {
//				int perPage = getPerPage(maxPerPage, i);
//				boolean newData = newer;
//				if (i != 0) {
//					newData = false;
//				}
//				Pair<Long[], Set<T>> resultPair = getresultPair(uid, maxOffset, perPage, newData);
//
//				if (resultPair == null) {
//					log.error("uid=" + uid + " resultPair is null");
//					break;
//				}
//
//				log.debug("tag=" + tag + ", uid=" + uid + ", minOffset=" + resultPair.getLeft()[0] + ", maxOffset="
//						+ resultPair.getLeft()[1] + " resultSet size=" + resultPair.getRight().size());
//
//				if (resultPair.getLeft()[0] < minOffset) {
//					minOffset = resultPair.getLeft()[0];
//				}
//
//				if (resultPair.getLeft()[1] > maxOffset) {
//					maxOffset = resultPair.getLeft()[1];
//				}
//				Set<T> pids = resultPair.getRight();
//				if (pids.isEmpty()) {
//					break;
//				}
//
//				pids.removeAll(filterPids);
//				if (pids.size() == 0) {
//					log.debug("tag=" + tag + ", uid=" + uid + ", minOffset=" + resultPair.getLeft()[0] + ", maxOffset="
//							+ resultPair.getLeft()[1] + " filtered resultSet size is zero");
//					continue;
//				}
//
//				resultItem.setDataSet(pids);
//				break;
//			}
//			// 出错了。。。。
//			if (minOffset == Long.MAX_VALUE) {
//				minOffset = 0;
//			}
//			resultItem.setMinOffset(minOffset);
//			resultItem.setMaxOffset(maxOffset);
//			log.debug("uid=" + uid + ", resultItem=" + resultItem);
//
//			return resultItem;
//
//		});
//		return future;
//	}
//
//	private int getPerPage(int maxPerPage, int idx) {
//		if (idx <= 2) {
//			return ((Double) (maxPerPage * (Math.pow(idx + 1, 2) + 1))).intValue();
//		}
//		return maxPerPage * 10 * (idx - 2);
//	}
//
//	public abstract Pair<Long[], Set<T>> getresultPair(long uid, long maxOffset, int perPage, boolean newer);
//}
