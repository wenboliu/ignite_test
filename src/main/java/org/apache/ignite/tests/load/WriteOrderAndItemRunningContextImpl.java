package org.apache.ignite.tests.load;

import com.google.common.collect.Lists;
import pri.wenbo.pojos.CmsOrder;
import pri.wenbo.pojos.OrderItem;
import pri.wenbo.pojos.OrderItemId;

import java.util.List;

public class WriteOrderAndItemRunningContextImpl implements RunningContext {
    private static final String CACHE_ORDER_NAME = "cmsOrder";
    public static final String CACHE_ORDER_ITEM_NAME = "orderItem";
    private final long startOrderId;
    private final long endOrderId;
    private final int startDpId;
    private final int endDpId;
    private final String[] cacheNames = new String[] {CACHE_ORDER_NAME, CACHE_ORDER_ITEM_NAME};

    /**
     */
    public WriteOrderAndItemRunningContextImpl(int startPosition, int endPosition) {
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
        return Lists.newArrayList(createOrder(orderId, dpId),
                createOrderItem(orderId, dpId, 1),
                createOrderItem(orderId, dpId, 2));
    }

    private KeyValuePair createOrder(int orderId, int dpId) {
        return new KeyValuePair(CACHE_ORDER_NAME, generateOrderId(dpId, orderId), generateOrder(dpId, orderId));
    }

    private KeyValuePair createOrderItem(int orderId, int dpId, int item_id) {
        return new KeyValuePair(CACHE_ORDER_ITEM_NAME, generateOrderItemId(dpId, orderId, item_id), generateOrderItem());
    }

    private String generateOrderId(int dp_Id, int order_id) {
        String dpId = LoadDataUtils.toString(dp_Id, 15);
        String orderId = LoadDataUtils.toString(order_id, 15);
        return dpId + "-" + orderId;
    }

    private CmsOrder generateOrder(int dp_Id, int order_id) {
        CmsOrder order = new CmsOrder();
        order.setCreated(LoadDataUtils.randomDate());
        order.setCustomerno("店铺" + LoadDataUtils.toString(dp_Id, 5) + "客户" + LoadDataUtils.toString(order_id, 5));
        order.setStatus(LoadDataUtils.randomOrderStatus());
        return order;
    }

    private OrderItemId generateOrderItemId(int dp_Id, int order_id, int item_id)
    {
        OrderItemId itemId = new OrderItemId();
        String dpId = LoadDataUtils.toString(dp_Id, 15);
        String orderId = LoadDataUtils.toString(order_id, 15);
        itemId.setDpIdOrderId(dpId + "-" + orderId);
        itemId.setOid(LoadDataUtils.toString(item_id, 3));
        return itemId;
    }

    private OrderItem generateOrderItem()
    {
        OrderItem item = new OrderItem();
        item.setTitle(LoadDataUtils.randomItemTitle());
        item.setStatus(LoadDataUtils.randomOrderItemStatus());
        return item;
    }

    private int calculateDpIdFromOrderId(long orderId) {
        long orderIdCursor = this.startOrderId;
        for (int i = this.startDpId; i < this.endDpId; i++) {
            orderIdCursor += OrderCount.orderCountOfDp(i);
            if(orderId < orderIdCursor)
                return i;
        }
        throw new RuntimeException("Caculation Error!");
    }

    private long calculateStartPosition(int startPosition) {
        long orderCount = 0;
        for (int i = 0; i < startPosition; i++) {
            orderCount += OrderCount.orderCountOfDp(i);
        }
        return orderCount;
    }

    private long calculateEndPosition(int startPosition, int endPosition) {
        long orderCount = 0;
        for (int i = startPosition; i < endPosition; i++) {
            orderCount += OrderCount.orderCountOfDp(i);
        }
        return orderCount - 1;
    }
}
