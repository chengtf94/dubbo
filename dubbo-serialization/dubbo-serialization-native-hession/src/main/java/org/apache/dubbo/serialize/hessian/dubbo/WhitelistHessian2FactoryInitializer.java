package org.apache.dubbo.serialize.hessian.dubbo;

import org.apache.dubbo.common.config.ConfigurationUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.serialize.hessian.Hessian2SerializerFactory;

import com.caucho.hessian.io.SerializerFactory;

/**
 * see https://github.com/ebourg/hessian/commit/cf851f5131707891e723f7f6a9718c2461aed826
 */
public class WhitelistHessian2FactoryInitializer extends AbstractHessian2FactoryInitializer {

    @Override
    public SerializerFactory createSerializerFactory() {
        SerializerFactory serializerFactory = new Hessian2SerializerFactory();
        String whiteList = ConfigurationUtils.getProperty(WHITELIST);
        if ("true".equals(whiteList)) {
            serializerFactory.getClassFactory().setWhitelist(true);
            String allowPattern = ConfigurationUtils.getProperty(ALLOW);
            if (StringUtils.isNotEmpty(allowPattern)) {
                serializerFactory.getClassFactory().allow(allowPattern);
            }
        } else {
            serializerFactory.getClassFactory().setWhitelist(false);
            String denyPattern = ConfigurationUtils.getProperty(DENY);
            if (StringUtils.isNotEmpty(denyPattern)) {
                serializerFactory.getClassFactory().deny(denyPattern);
            }
        }
        return serializerFactory;
    }

}
