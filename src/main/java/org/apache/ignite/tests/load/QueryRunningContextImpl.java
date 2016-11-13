package org.apache.ignite.tests.load;

import com.google.common.collect.Lists;
import pri.wenbo.pojos.CmsOrderId;

import java.util.List;

public class QueryRunningContextImpl implements RunningContext {
    private static final String CACHE_ORDER_NAME = "cmsOrder";
    public static final String CACHE_ORDER_ITEM_NAME = "orderItem";
    private final long startOrderId;
    private final long endOrderId;
    private final int startDpId;
    private final int endDpId;
    private final String[] cacheNames = new String[] {CACHE_ORDER_NAME, CACHE_ORDER_ITEM_NAME};

    /**
     */
    public QueryRunningContextImpl(int startPosition, int endPosition) {
        this.startDpId = startPosition;
        this.endDpId = endPosition;
        this.startOrderId = calculateStartPosition(startPosition);
        this.endOrderId = this.startOrderId + calculateEndPosition(startPosition, endPosition);
    }

    @Override
    public long getStartPosition() {
        return startOrderId;
    }

    @Override
    public long getEndPosition() {
        return endOrderId;
    }

    @Override
    public String[] getCacheNames() {
        return cacheNames;
    }

    @Override
    public List<KeyValuePair> generateLoadTestsObject(long i) {
        int orderId = (int) i;
        int dpId = calculateDpIdFromOrderId(orderId);
        return Lists.newArrayList(createOrderInfo(dpId, orderId));
    }

    private KeyValuePair createOrderInfo(int dpId, int orderId) {
        return new KeyValuePair(CACHE_ORDER_NAME, generateOrderId(dpId, orderId), CACHE_ORDER_ITEM_NAME);
    }


    private CmsOrderId generateOrderId(int dp_Id, int order_id) {
        CmsOrderId cmsOrderId = new CmsOrderId();
        cmsOrderId.setDpId(LoadDataUtils.toString(dp_Id, 15));
        cmsOrderId.setOrderId(LoadDataUtils.toString(order_id, 15));
        return cmsOrderId;
    }


    private int calculateDpIdFromOrderId(long orderId) {
        long orderIdCursor = this.startOrderId;
        for (int i = this.startDpId; i < this.endDpId; i++) {
            orderIdCursor += OrderCount.orderCountOfDp(i);
            if(orderId < orderIdCursor)
                return i;
        }
        return 0;
    }

    private long calculateStartPosition(int startPosition) {
        long orderCount = 0;
        for (int i = 0; i < startPosition; i++) {
            orderCount += OrderCount.orderCountOfDp(i);
        }
        return orderCount;
    }

    private long calculateEndPosition(int startPosition, int endPosition) {
        if(startPosition==0 && endPosition == 0) endPosition = 1;
        long orderCount = 0;
        for (int i = startPosition; i < endPosition; i++) {
            orderCount += OrderCount.orderCountOfDp(i);
        }
        return orderCount - 1;
    }
}
