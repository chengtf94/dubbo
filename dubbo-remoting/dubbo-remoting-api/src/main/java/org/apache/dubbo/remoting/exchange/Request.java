package org.apache.dubbo.remoting.exchange;

import org.apache.dubbo.common.utils.StringUtils;

import java.util.concurrent.atomic.AtomicLong;

import static org.apache.dubbo.common.constants.CommonConstants.HEARTBEAT_EVENT;

/**
 * 请求
 */
public class Request {

    private static final AtomicLong INVOKE_ID = new AtomicLong(0);

    /**
     * ID
     */
    private final long mId;

    /**
     * 版本
     */
    private String mVersion;

    private boolean mTwoWay = true;

    private boolean mEvent = false;

    private boolean mBroken = false;

    /**
     * 数据
     */
    private Object mData;

    public Request() {
        mId = newId();
    }

    public Request(long id) {
        mId = id;
    }

    private static long newId() {
        // getAndIncrement() When it grows to MAX_VALUE, it will grow to MIN_VALUE, and the negative can be used as ID
        return INVOKE_ID.getAndIncrement();
    }

    private static String safeToString(Object data) {
        if (data == null) {
            return null;
        }
        String dataStr;
        try {
            dataStr = data.toString();
        } catch (Throwable e) {
            dataStr = "<Fail toString of " + data.getClass() + ", cause: " +
                    StringUtils.toString(e) + ">";
        }
        return dataStr;
    }

    public long getId() {
        return mId;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

    public boolean isTwoWay() {
        return mTwoWay;
    }

    public void setTwoWay(boolean twoWay) {
        mTwoWay = twoWay;
    }

    public boolean isEvent() {
        return mEvent;
    }

    public void setEvent(String event) {
        this.mEvent = true;
        this.mData = event;
    }

    public void setEvent(boolean mEvent) {
        this.mEvent = mEvent;
    }

    public boolean isBroken() {
        return mBroken;
    }

    public void setBroken(boolean mBroken) {
        this.mBroken = mBroken;
    }

    public Object getData() {
        return mData;
    }

    public void setData(Object msg) {
        mData = msg;
    }

    public boolean isHeartbeat() {
        return mEvent && HEARTBEAT_EVENT == mData;
    }

    public void setHeartbeat(boolean isHeartbeat) {
        if (isHeartbeat) {
            setEvent(HEARTBEAT_EVENT);
        }
    }

    public Request copy() {
        Request copy = new Request(mId);
        copy.mVersion = this.mVersion;
        copy.mTwoWay = this.mTwoWay;
        copy.mEvent = this.mEvent;
        copy.mBroken = this.mBroken;
        copy.mData = this.mData;
        return copy;
    }

    public Request copyWithoutData() {
        Request copy = new Request(mId);
        copy.mVersion = this.mVersion;
        copy.mTwoWay = this.mTwoWay;
        copy.mEvent = this.mEvent;
        copy.mBroken = this.mBroken;
        return copy;
    }

    @Override
    public String toString() {
        return "Request [id=" + mId + ", version=" + mVersion + ", twoway=" + mTwoWay + ", event=" + mEvent
                + ", broken=" + mBroken + ", data=" + (mData == this ? "this" : safeToString(mData)) + "]";
    }
}
