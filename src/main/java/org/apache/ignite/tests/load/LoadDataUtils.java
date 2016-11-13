package org.apache.ignite.tests.load;

import pri.wenbo.pojos.OrderItemStatus;
import pri.wenbo.pojos.OrderStatus;

import java.util.*;

/**
 * Created by Mofy on 2016/11/2.
 */
public class LoadDataUtils {

    static Random random = new Random();

    public static String randomText(int size)
    {
        StringBuffer text = new StringBuffer();
        text.append(random.nextDouble());
        while(text.length() < size)
        {
            text.append(random.nextDouble());
        }
        return text.toString();
    }

    public static long randomDate()
    {
        return getEndDate() - (random.nextInt(30*24*3600) * 1000);
    }

    public static long getEndDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(2016, 0, 1);
        return instance.getTimeInMillis();
    }

    public static String toString(int id, int length)
    {
        return String.format("%0"+ length +"d", id);
    }

    public static int  randomOrderStatus() {
        Map<Integer, Integer> statusRanges = new HashMap<>();
        statusRanges.put(OrderStatus.CREATE, 10);
        statusRanges.put(OrderStatus.PAYED, 40);
        statusRanges.put(OrderStatus.CLOSED, 50);
        return (int) randomStatus(statusRanges, OrderStatus.CLOSED);
    }

    public static int randomOrderItemStatus()
    {
        Map<Integer, Integer> statusRanges = new HashMap<Integer, Integer>();
        statusRanges.put(OrderItemStatus.CONSIGN_CARED, 5);
        statusRanges.put(OrderItemStatus.CONSIGN_NOT_CARED, 20);
        statusRanges.put(OrderItemStatus.LOGSTIC, 30);
        statusRanges.put(OrderItemStatus.SIGNED, 20);
        statusRanges.put(OrderItemStatus.REFUND_CLOSE, 25);
        return (int)randomStatus(statusRanges, OrderItemStatus.SIGNED);
    }

    private static Object randomStatus(Map statusRanges, Object defaultStatus)
    {
        int status = random.nextInt(100);
        int range = 0;
        Iterator<Map.Entry> entries = statusRanges.entrySet().iterator();
        while(entries.hasNext())
        {
            Map.Entry entry = entries.next();
            range += (Integer) entry.getValue();
            if(status <= range)
            {
                return entry.getKey();
            }
        }
        return defaultStatus;
    }

    public static String randomItemTitle()
    {
        return ItemTitles.titles.get(random.nextInt(ItemTitles.titles.size()-1));
    }

}
