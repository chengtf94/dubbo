package org.apache.dubbo.common.serialize;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 序列化接口：将字节数组转换为Java对象
 */
public interface ObjectInput extends DataInput {

    @Deprecated
    Object readObject() throws IOException, ClassNotFoundException;

    <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException;

    <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException;

    default Throwable readThrowable() throws IOException, ClassNotFoundException {
        Object obj = readObject();
        if (!(obj instanceof Throwable)) {
            throw new IOException("Response data error, expect Throwable, but get " + obj);
        }
        return (Throwable) obj;
    }

    default Object readEvent() throws IOException, ClassNotFoundException {
        return readObject();
    }

    default Map<String, Object> readAttachments() throws IOException, ClassNotFoundException {
        return readObject(Map.class);
    }
}