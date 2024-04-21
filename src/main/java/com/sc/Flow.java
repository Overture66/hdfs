package com.sc;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
public class Flow implements Writable{

    private String phone;     //手机号
    private long  up;       //上行流量
    private long down;     //下线流量

    private long sum;     //总流量

    //无参构造函数
    public Flow() {
    }

    //有参构造函数
    public Flow(String phone, long up, long down) {
        super();
        this.phone = phone;
        this.up = up;
        this.down = down;
        this.sum=this.up+this.down;
    }

    @Override
    public void write(DataOutput out) throws IOException {

        out.writeUTF(this.phone);
        out.writeLong(this.up);
        out.writeLong(this.down);
        out.writeLong(this.sum);
    }

    @Override
    public void readFields(DataInput in) throws IOException {


        this.phone=in.readUTF();
        this.up=in.readLong();
        this.down=in.readLong();
        this.sum=in.readLong();

    }

    @Override
    public String toString() {

        return   this.up+"\t"+this.down+"\t"+this.sum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getUp() {
        return up;
    }

    public void setUp(long up) {
        this.up = up;
    }

    public long getDown() {
        return down;
    }

    public void setDown(long down) {
        this.down = down;
    }
    public long getSum() {
        return sum;
    }
}

