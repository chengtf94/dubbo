package org.apache.dubbo.common.serialize;

/**
 * Interface defines that the object is cleanable.
 */
public interface Cleanable {

    /**
     * Implementations must implement this cleanup method
     */
    void cleanup();

}
