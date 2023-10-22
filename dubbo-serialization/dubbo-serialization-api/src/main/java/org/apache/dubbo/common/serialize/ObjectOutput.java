package org.apache.dubbo.common.serialize;

import java.io.IOException;
import java.util.Map;

/**
 * 序列化接口：将Java对象转换为字节数组
 */
public interface ObjectOutput extends DataOutput {

    void writeObject(Object obj) throws IOException;

    default void writeThrowable(Object obj) throws IOException {
        writeObject(obj);
    }

    default void writeEvent(Object data) throws IOException {
        writeObject(data);
    }

    default void writeAttachments(Map<String, Object> attachments) throws IOException {
        writeObject(attachments);
    }

}