package org.apache.dubbo.serialize.hessian.dubbo;

import org.apache.dubbo.serialize.hessian.Hessian2SerializerFactory;

import com.caucho.hessian.io.SerializerFactory;

public class DefaultHessian2FactoryInitializer extends AbstractHessian2FactoryInitializer {
    @Override
    protected SerializerFactory createSerializerFactory() {
        return new Hessian2SerializerFactory();
    }
}
