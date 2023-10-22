package org.apache.dubbo.serialize.hessian.dubbo;

import org.apache.dubbo.common.config.ConfigurationUtils;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.extension.SPI;
import org.apache.dubbo.common.utils.StringUtils;

import com.caucho.hessian.io.SerializerFactory;

@SPI("default")
public interface Hessian2FactoryInitializer {
    String WHITELIST = "dubbo.application.hessian2.whitelist";
    String ALLOW = "dubbo.application.hessian2.allow";
    String DENY = "dubbo.application.hessian2.deny";
    ExtensionLoader<Hessian2FactoryInitializer> loader = ExtensionLoader.getExtensionLoader(Hessian2FactoryInitializer.class);

    SerializerFactory getSerializerFactory();

    static Hessian2FactoryInitializer getInstance() {
        String whitelist = ConfigurationUtils.getProperty(WHITELIST);
        if (StringUtils.isNotEmpty(whitelist)) {
            return loader.getExtension("whitelist");
        }
        return loader.getDefaultExtension();
    }

}
