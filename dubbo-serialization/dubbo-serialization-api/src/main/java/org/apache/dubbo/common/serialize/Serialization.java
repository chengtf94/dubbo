package org.apache.dubbo.common.serialize;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 序列化算法接口：默认使用hessian2序列化算法
 */
@SPI("hessian2")
public interface Serialization {

    /**
     * 获取ContentTypeId、ContentType，每一种序列化算法都对应一个获取ContentType
     */
    byte getContentTypeId();

    String getContentType();

    /**
     * 序列化：ObjectOutput负责序列化的功能
     */
    @Adaptive
    ObjectOutput serialize(URL url, OutputStream output) throws IOException;

    /**
     * 反序列化：ObjectInput负责反序列化的功能
     */
    @Adaptive
    ObjectInput deserialize(URL url, InputStream input) throws IOException;

}
