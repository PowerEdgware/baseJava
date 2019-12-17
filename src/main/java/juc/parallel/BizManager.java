//package juc.parallel;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.CompletableFuture;
//import java.util.stream.Collectors;
//
//import org.apache.commons.lang3.tuple.Pair;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.google.common.collect.ImmutableSet;
//import com.google.common.collect.Iterables;
//
//import cc.pmtv.rcmd.config.PerPageConfig;
//import cc.pmtv.rcmd.dubbo.PMRPCService;
//import cc.pmtv.rcmd.jpa.entity.UserBase;
//import cc.pmtv.rcmd.util.Constants;
//import cc.pmtv.rcmd.vo.ResultItem;
//import cc.pmtv.rcmd.vo.RpcResult;
//
//@Service
//public class BizManager implements PMRPCService {
//
//	private static Logger log = LoggerFactory.getLogger(BizManager.class);
//	@Autowired
//	private ServiceImpl service;
//
//	@Value("${rcmd.perpage.maxsize}")
//	private int maxPerPage = 10;
//
//	@Value("${rcmd.ttl.viewed}") // unit: s
//	private long viewedTTL = 7 * 24 * 3600;
//
//	@Value("${rcmd.max.get.retry}")
//	private int maxRetry = 5;
//
//	@Override
//	public RpcResult<Long> querySelectedPost(String cid, long uid, final Map<String, Long> offsetMap, boolean newer) {
//		log.info("cid=" + cid + ", uid=" + uid + ", newer=" + newer + ", offsetMap=" + offsetMap);
//		if (!offsetMap.containsKey(Constants.DISH_TAG_INTERACTION)) {
//			offsetMap.put(Constants.DISH_TAG_INTERACTION, offsetMap.get(Constants.DISH_TAG_INTERACTION) % 10000);
//		}
//
//		Set<Long> filterPids = getFilterPIds(uid, cid, true);
//		List<ResultItem<Long>> resultList = loadData(cid, uid, offsetMap, newer, filterPids);
//		Set<Long> allPids = flatDataSetItem(resultList);
//		List<Long> pidList = shuffleAll(allPids);
//		Set<Long> adPidSet = getNoPlayAdPids(filterPids);
//
//		if (!adPidSet.isEmpty() && !pidList.isEmpty()) {
//			int maxAdSize = PerPageConfig.videoadpool;
//			if (pidList.size() <= 2) {
//				maxAdSize = pidList.size();
//			} else if (pidList.size() <= 4) {
//				maxAdSize = 2;
//			} else {
//				maxAdSize = 3;
//			}
//			if (adPidSet.size() > maxAdSize) {
//				adPidSet = ImmutableSet.copyOf(Iterables.limit(adPidSet, maxAdSize));
//			}
//			log.debug("uid=" + uid + ", adPidSet=" + adPidSet);
//			Iterator<Long> it = adPidSet.iterator();
//			int i = 0;
//			while (it.hasNext()) {
//				Long pid = it.next();
//				int idx = ((Double) Math.pow(2, i + 1)).intValue() - 1;
//				log.debug("uid=" + uid + ", idx=" + idx + ", pid=" + pid);
//				pidList.add(idx, pid);
//				i++;
//			}
//		}
//		log.debug("uid=" + uid + ", pidList=" + pidList);
//
//		ResultItem<Long> resultItem = new ResultItem<>();
//		resultItem.setIdx(0);
//		resultItem.setTag("all");
//		resultItem.setDataSet(new LinkedHashSet<>(pidList));
//
//		RpcResult<Long> rpcResult = new RpcResult<>();
//		rpcResult.setResultCode(0);
//		rpcResult.setResultDesc("");
//		rpcResult.addResultItem(resultItem);
//		// 清除数据，然后保留索引
//		clearData(resultList);
//		rpcResult.addResultItemList(resultList);
//
//		log.debug("cid=" + cid + ", uid=" + uid + ", rpcResult=" + rpcResult);
//		return rpcResult;
//	}
//
//	private List<ResultItem<Long>> loadData(String cid, long uid, Map<String, Long> offsetMap, boolean newer,
//			Set<Long> filterPids) {
//
//		log.info("cid=" + cid + ", uid=" + uid + ", newer=" + newer + ", offsetMap=" + offsetMap);
//
//		List<ResultItem<Long>> resultList = new ArrayList<>();
//		if (uid > 0) {
//			UserBase userBase = service.queryUserByUid(uid);
//			log.debug("userBase=" + userBase);
//			if (userBase != null && userBase.isSprouted()) {
//				Set<Long> rcmdVideoPoolSet = service.getRcmdVideoPoolSet();
//				log.debug("before rcmdVideoPoolSet size=" + rcmdVideoPoolSet.size());
//				rcmdVideoPoolSet.removeAll(filterPids);
//				log.debug("after rcmdVideoPoolSet size=" + rcmdVideoPoolSet.size());
//				if (rcmdVideoPoolSet.isEmpty()) {
//					userBase.setSprouted(false);
//					userBase.setLastUpdated(new Date());
//					service.save(userBase);
//				} else {
//					ResultItem<Long> resultItem = new ResultItem<>();
//					resultItem.setIdx(Constants.DISH_INDEX_VPOOL);
//					resultItem.setTag(Constants.DISH_TAG_VPOOL);
//					resultItem.setMaxOffset(0);
//					resultItem.setMinOffset(0);
//					if (rcmdVideoPoolSet.size() <= PerPageConfig.videopool) {
//						resultItem.setDataSet(rcmdVideoPoolSet);
//					} else {
//						Set<Long> subset = ImmutableSet
//								.copyOf(Iterables.limit(rcmdVideoPoolSet, PerPageConfig.videopool));
//						resultItem.setDataSet(subset);
//					}
//					resultList.add(resultItem);
//					return resultList;
//				}
//			}
//		}
//
//		Long offset = getOffset(offsetMap, Constants.DISH_TAG_INTERACTION);
//
//		FutureableDataLoader<Long> hotInteractionDataLoader = new FutureableDataLoader<Long>() {
//			@Override
//			public Pair<Long[], Set<Long>> getresultPair(long uid, long maxOffset, int perPage, boolean newer) {
//				return service.queryHotInteraction(uid, maxOffset, perPage, newer);
//			}
//		};
//		CompletableFuture<ResultItem<Long>> hotInteractionFuture = hotInteractionDataLoader.loadData(
//				Constants.DISH_INDEX_INTERACTION, Constants.DISH_TAG_INTERACTION, uid, offset, newer,
//				PerPageConfig.interaction, maxRetry, filterPids);
//
//		offset =
//
//				getOffset(offsetMap, Constants.DISH_TAG_SELECTED);
//		FutureableDataLoader<Long> selectedDataLoader = new FutureableDataLoader<Long>() {
//			@Override
//			public Pair<Long[], Set<Long>> getresultPair(long uid, long maxOffset, int perPage, boolean newer) {
//				return service.querySelected(maxOffset, perPage, newer);
//			}
//		};
//		CompletableFuture<ResultItem<Long>> selectedFuture = selectedDataLoader.loadData(Constants.DISH_INDEX_SELECTED,
//				Constants.DISH_TAG_SELECTED, uid, offset, newer, PerPageConfig.selected, maxRetry, filterPids);
//
//		List<CompletableFuture<ResultItem<Long>>> futrues = new ArrayList<>();
//		futrues.add(hotInteractionFuture);
//		futrues.add(selectedFuture);
//
//		resultList = CompletableFuture.allOf(futrues.toArray(new CompletableFuture[0]))
//				.thenApply(v -> futrues.stream().map(CompletableFuture::join).collect(Collectors.toList())).join();
//		return resultList;
//	}
//
//	private long getOffset(Map<String, Long> offsetMap, String tag) {
//		long offset = 0L;
//		if (offsetMap != null && offsetMap.containsKey(tag)) {
//			offset = offsetMap.get(tag);
//		}
//		return offset;
//	}
//
//	// private void removeDuplicateId(List<ResultItem<Long>> resultList) {
//	// Comparator<ResultItem<Long>> comparator = new RpcResultComparetor();
//	// Collections.sort(resultList, comparator);
//	//
//	// for (int i = 0; i < resultList.size(); i++) {
//	// ResultItem<Long> srcItem = resultList.get(i);
//	// for (int j = i + 1; j < resultList.size(); j++) {
//	// ResultItem<Long> dstItem = resultList.get(j);
//	// dstItem.getDataSet().removeAll(srcItem.getDataSet());
//	// }
//	// }
//	// }
//
//	private List<Long> shuffleAll(Set<Long> pids) {
//		List<Long> pidList = new ArrayList<>();
//		pidList.addAll(pids);
//		Collections.shuffle(pidList);
//		return pidList;
//	}
//
//	private Set<Long> flatDataSetItem(List<ResultItem<Long>> resultList) {
//		Set<Long> allPids = new HashSet<>();
//		resultList.stream().forEach(item -> {
//			allPids.addAll(item.getDataSet());
//		});
//		return allPids;
//	}
//
//	private void clearData(List<ResultItem<Long>> resultList) {
//		resultList.stream().forEach(item -> {
//			item.setDataSet(new HashSet<>());
//		});
//	}
//
//	private Set<Long> getFilterPIds(long uid, String cid, boolean filterHistory) {
//		Set<Long> filterPids = new LinkedHashSet<>();
//		if (filterHistory) {
//			Set<Long> userViewedPids = service.loadViewedData(uid, viewedTTL);
//			Set<Long> clientViewedPids = service.loadViewedDataByCid(cid, viewedTTL);
//			filterPids.addAll(userViewedPids);
//			filterPids.addAll(clientViewedPids);
//		}
//		Set<Long> blackPids = service.getPostBlacklist();
//		if (blackPids != null && !blackPids.isEmpty()) {
//			filterPids.addAll(blackPids);
//		}
//		// 娓呴櫎闈炴硶PID锛屽寘鍚槻姝㈢紦瀛樼┛閫忚�岃缃殑0
//		filterPids.add(0L);
//		log.info("uid=" + uid + ", filterPids size=" + filterPids.size());
//		return filterPids;
//	}
//
//	private Set<Long> getNoPlayAdPids(Set<Long> filterIds) {
//		Set<Long> adPids = service.getAdVideoPoolSet();
//		adPids.removeAll(filterIds);
//		return adPids;
//	}
//
//}
