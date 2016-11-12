package pri.wenbo.pojos;


import java.io.*;
import java.math.BigDecimal;

/**
 * Created by Mofy on 2016/11/8.
 */
public class OrderItem implements Externalizable {

    private String title; //商品标题
    private int status = OrderItemStatus.SIGNED;

    private BigDecimal total_fee = new BigDecimal(10.0);
    private BigDecimal discount_fee = new BigDecimal(10.0);
    private BigDecimal adjust_fee = new BigDecimal(10.0);
    private BigDecimal payment = new BigDecimal(10.0);
    private BigDecimal price = new BigDecimal(10.0);


    private int num = 10;
    private String num_iid = "商品数字ID ";
    private String refund_status = "WAIT_SELLER_AGREE";
    private String pic_path = "商品图片URl";
    private String outer_iid = "商家外部编码";
    private String sku_properties_name = "sku_properties_name";
    private String sku_id = "sku_id";
    private long trade_modified = System.currentTimeMillis();
    private long timeout_action_time = System.currentTimeMillis();

    private boolean buyer_rate;
    private boolean seller_rate;
    private long item_order_created = System.currentTimeMillis();
    private long item_order_updated = System.currentTimeMillis();
    private String logistics_company = "子订单发货的快递公司名称";
    private String invoice_no = "子订单所在包裹的运单号";

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeInt(status);
        out.writeObject(total_fee);
        out.writeObject(discount_fee);
        out.writeObject(adjust_fee);
        out.writeObject(payment);
        out.writeObject(price);
        out.writeInt(num);
        out.writeObject(num_iid);
        out.writeObject(refund_status);
        out.writeObject(pic_path);
        out.writeObject(outer_iid);
        out.writeObject(sku_properties_name);
        out.writeObject(sku_id);
        out.writeLong(trade_modified);
        out.writeLong(timeout_action_time);
        out.writeBoolean(buyer_rate);
        out.writeBoolean(seller_rate);
        out.writeLong(item_order_created);
        out.writeLong(item_order_updated);
        out.writeObject(logistics_company);
        out.writeObject(invoice_no);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();
        status = in.readInt();
        total_fee = (BigDecimal) in.readObject();
        discount_fee = (BigDecimal) in.readObject();
        adjust_fee = (BigDecimal) in.readObject();
        payment = (BigDecimal) in.readObject();
        price = (BigDecimal) in.readObject();
        num = in.readInt();
        num_iid = (String) in.readObject();
        refund_status = (String) in.readObject();
        pic_path = (String) in.readObject();
        outer_iid = (String) in.readObject();
        sku_properties_name = (String) in.readObject();
        sku_id = (String) in.readObject();
        trade_modified = in.readLong();
        timeout_action_time = in.readLong();
        buyer_rate = in.readBoolean();
        seller_rate = in.readBoolean();
        item_order_created = in.readLong();
        item_order_updated = in.readLong();
        logistics_company = (String) in.readObject();
        invoice_no = (String) in.readObject();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(BigDecimal total_fee) {
        this.total_fee = total_fee;
    }

    public BigDecimal getDiscount_fee() {
        return discount_fee;
    }

    public void setDiscount_fee(BigDecimal discount_fee) {
        this.discount_fee = discount_fee;
    }

    public BigDecimal getAdjust_fee() {
        return adjust_fee;
    }

    public void setAdjust_fee(BigDecimal adjust_fee) {
        this.adjust_fee = adjust_fee;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNum_iid() {
        return num_iid;
    }

    public void setNum_iid(String num_iid) {
        this.num_iid = num_iid;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public String getOuter_iid() {
        return outer_iid;
    }

    public void setOuter_iid(String outer_iid) {
        this.outer_iid = outer_iid;
    }

    public String getSku_properties_name() {
        return sku_properties_name;
    }

    public void setSku_properties_name(String sku_properties_name) {
        this.sku_properties_name = sku_properties_name;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public long getTrade_modified() {
        return trade_modified;
    }

    public void setTrade_modified(long trade_modified) {
        this.trade_modified = trade_modified;
    }

    public long getTimeout_action_time() {
        return timeout_action_time;
    }

    public void setTimeout_action_time(long timeout_action_time) {
        this.timeout_action_time = timeout_action_time;
    }

    public boolean isBuyer_rate() {
        return buyer_rate;
    }

    public void setBuyer_rate(boolean buyer_rate) {
        this.buyer_rate = buyer_rate;
    }

    public boolean isSeller_rate() {
        return seller_rate;
    }

    public void setSeller_rate(boolean seller_rate) {
        this.seller_rate = seller_rate;
    }

    public long getItem_order_created() {
        return item_order_created;
    }

    public void setItem_order_created(long item_order_created) {
        this.item_order_created = item_order_created;
    }

    public long getItem_order_updated() {
        return item_order_updated;
    }

    public void setItem_order_updated(long item_order_updated) {
        this.item_order_updated = item_order_updated;
    }

    public String getLogistics_company() {
        return logistics_company;
    }

    public void setLogistics_company(String logistics_company) {
        this.logistics_company = logistics_company;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }


}
