package pri.wenbo.pojos;

/**
 * Created by Mofy on 2016/11/8.
 */
public class OrderItemStatus {

    public static Integer CONSIGN_NOT_CARED = 100;
    public static Integer CONSIGN_CARED = 101;

    public static Integer LOGSTIC = 200;
    public static Integer LOGSTIC_ERROR_NOT_CARED = 210;
    public static Integer LOGSTIC_ERROR_CARED = 211;

    public static Integer SIGNED = 300;
    public static Integer SIGNED_RATE_NOT_CARED = 310;
    public static Integer SIGNED_RATE_CARED = 311;
    public static Integer SIGNED_EXPLAIN = 320;

    public static Integer REFUND_NOT_CARED = 400;
    public static Integer REFUND_CARED = 401;
    public static Integer REFUND_CLOSE = 410;

}
