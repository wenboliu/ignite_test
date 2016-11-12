package pri.wenbo.pojos;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;

public class CmsOrder implements Externalizable {
    @QuerySqlField
    private String customerno;
    @QuerySqlField
    private long created;

    private int status;

    private long endtime = System.currentTimeMillis();
    private String trade_from = "TAOBAO";
    private long pay_time = System.currentTimeMillis();
    private long consign_time = System.currentTimeMillis();
    private long modified = System.currentTimeMillis();
    private BigDecimal payment = new BigDecimal(100.00);
    private String receiver_name = "收货人的姓名";
    private String receiver_state = "收货人的所在省份";
    private String receiver_city = "收货人的所在城市";
    private String receiver_district = "收货人的所在地区";
    private String receiver_address = "收货人的详细地址";
    private String receiver_zip = "收货人的邮编";
    private String receiver_mobile = "收货人的手机号码";
    private String receiver_phone = "收货人的电话号码";
    private int num = 10;
    private String buyer_message = "买家留言买家留言买家留言买家留言买家留言买家留言";
    private String seller_memo = "卖家备注卖家备注卖家备注卖家备注卖家备注卖家备注";
    private int seller_flag = 1;
    private BigDecimal post_fee = new BigDecimal(10.0);
    private String shipping_type = "express";

    private int order_status = 1;
    private long order_created = System.currentTimeMillis();
    private long order_updated = System.currentTimeMillis();
    private String type = "guarantee_trade";
    private String step_trade_status = "FRONT_NOPAID_FINAL_NOPAID";
    private String cod_status = "ACCEPTED_BY_COMPANY";
    private BigDecimal step_paid_fee = new BigDecimal(0.0);
    private long timeout_action_time = 1;
    private int is_part_consign = 1;
    private BigDecimal discount_fee = new BigDecimal(0.0);
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(customerno);
        out.writeLong(created);
        out.writeInt(status);
        out.writeLong(endtime);
        out.writeObject(trade_from);
        out.writeLong(pay_time);
        out.writeLong(consign_time);
        out.writeLong(modified);
        out.writeObject(payment);
        out.writeObject(receiver_name);
        out.writeObject(receiver_state);
        out.writeObject(receiver_city);
        out.writeObject(receiver_district);
        out.writeObject(receiver_address);
        out.writeObject(receiver_zip);
        out.writeObject(receiver_mobile);
        out.writeObject(receiver_phone);
        out.writeInt(num);
        out.writeObject(buyer_message);
        out.writeObject(seller_memo);
        out.writeInt(seller_flag);
        out.writeObject(post_fee);
        out.writeObject(shipping_type);
        out.writeInt(order_status);
        out.writeLong(order_created);
        out.writeLong(order_updated);
        out.writeObject(type);
        out.writeObject(step_trade_status);
        out.writeObject(cod_status);
        out.writeObject(step_paid_fee);
        out.writeLong(timeout_action_time);
        out.writeInt(is_part_consign);
        out.writeObject(discount_fee);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        customerno = (String) in.readObject();
        created = in.readLong();
        status = in.readInt();
        endtime = in.readLong();
        trade_from = (String) in.readObject();
        pay_time = in.readLong();
        consign_time = in.readLong();
        modified = in.readLong();
        payment = (BigDecimal) in.readObject();
        receiver_name = (String) in.readObject();
        receiver_state = (String) in.readObject();
        receiver_city = (String) in.readObject();
        receiver_district = (String) in.readObject();
        receiver_address = (String) in.readObject();
        receiver_zip = (String) in.readObject();
        receiver_mobile = (String) in.readObject();
        receiver_phone = (String) in.readObject();
        num = in.readInt();
        buyer_message = (String) in.readObject();
        seller_memo = (String) in.readObject();
        seller_flag = in.readInt();
        post_fee = (BigDecimal) in.readObject();
        shipping_type = (String) in.readObject();
        order_status = in.readInt();
        order_created = in.readLong();
        order_updated = in.readLong();
        type = (String) in.readObject();
        step_trade_status = (String) in.readObject();
        cod_status = (String) in.readObject();
        step_paid_fee = (BigDecimal) in.readObject();
        timeout_action_time = in.readLong();
        is_part_consign = in.readInt();
        discount_fee = (BigDecimal) in.readObject();
    }

    public String getCustomerno() {
        return customerno;
    }


    public void setCustomerno(String customerno) {
        this.customerno = customerno;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    public String getTrade_from() {
        return trade_from;
    }

    public void setTrade_from(String trade_from) {
        this.trade_from = trade_from;
    }

    public long getPay_time() {
        return pay_time;
    }

    public void setPay_time(long pay_time) {
        this.pay_time = pay_time;
    }

    public long getConsign_time() {
        return consign_time;
    }

    public void setConsign_time(long consign_time) {
        this.consign_time = consign_time;
    }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) {
        this.modified = modified;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_state() {
        return receiver_state;
    }

    public void setReceiver_state(String receiver_state) {
        this.receiver_state = receiver_state;
    }

    public String getReceiver_city() {
        return receiver_city;
    }

    public void setReceiver_city(String receiver_city) {
        this.receiver_city = receiver_city;
    }

    public String getReceiver_district() {
        return receiver_district;
    }

    public void setReceiver_district(String receiver_district) {
        this.receiver_district = receiver_district;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public String getReceiver_zip() {
        return receiver_zip;
    }

    public void setReceiver_zip(String receiver_zip) {
        this.receiver_zip = receiver_zip;
    }

    public String getReceiver_mobile() {
        return receiver_mobile;
    }

    public void setReceiver_mobile(String receiver_mobile) {
        this.receiver_mobile = receiver_mobile;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getBuyer_message() {
        return buyer_message;
    }

    public void setBuyer_message(String buyer_message) {
        this.buyer_message = buyer_message;
    }

    public String getSeller_memo() {
        return seller_memo;
    }

    public void setSeller_memo(String seller_memo) {
        this.seller_memo = seller_memo;
    }

    public int getSeller_flag() {
        return seller_flag;
    }

    public void setSeller_flag(int seller_flag) {
        this.seller_flag = seller_flag;
    }

    public BigDecimal getPost_fee() {
        return post_fee;
    }

    public void setPost_fee(BigDecimal post_fee) {
        this.post_fee = post_fee;
    }

    public String getShipping_type() {
        return shipping_type;
    }

    public void setShipping_type(String shipping_type) {
        this.shipping_type = shipping_type;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public long getOrder_created() {
        return order_created;
    }

    public void setOrder_created(long order_created) {
        this.order_created = order_created;
    }

    public long getOrder_updated() {
        return order_updated;
    }

    public void setOrder_updated(long order_updated) {
        this.order_updated = order_updated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStep_trade_status() {
        return step_trade_status;
    }

    public void setStep_trade_status(String step_trade_status) {
        this.step_trade_status = step_trade_status;
    }

    public String getCod_status() {
        return cod_status;
    }

    public void setCod_status(String cod_status) {
        this.cod_status = cod_status;
    }

    public BigDecimal getStep_paid_fee() {
        return step_paid_fee;
    }

    public void setStep_paid_fee(BigDecimal step_paid_fee) {
        this.step_paid_fee = step_paid_fee;
    }

    public long getTimeout_action_time() {
        return timeout_action_time;
    }

    public void setTimeout_action_time(long timeout_action_time) {
        this.timeout_action_time = timeout_action_time;
    }

    public int getIs_part_consign() {
        return is_part_consign;
    }

    public void setIs_part_consign(int is_part_consign) {
        this.is_part_consign = is_part_consign;
    }

    public BigDecimal getDiscount_fee() {
        return discount_fee;
    }

    public void setDiscount_fee(BigDecimal discount_fee) {
        this.discount_fee = discount_fee;
    }


}
