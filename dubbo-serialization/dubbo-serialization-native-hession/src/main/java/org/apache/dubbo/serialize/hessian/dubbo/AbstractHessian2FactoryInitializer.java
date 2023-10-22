package org.apache.dubbo.serialize.hessian.dubbo;

import com.caucho.hessian.io.SerializerFactory;

public abstract class AbstractHessian2FactoryInitializer implements Hessian2FactoryInitializer {
    private static SerializerFactory SERIALIZER_FACTORY;

    @Override
    public SerializerFactory getSerializerFactory() {
        if (SERIALIZER_FACTORY != null) {
            return SERIALIZER_FACTORY;
        }
        synchronized (this) {
            SERIALIZER_FACTORY = createSerializerFactory();
        }
        return SERIALIZER_FACTORY;
    }

    protected abstract SerializerFactory createSerializerFactory();
}
