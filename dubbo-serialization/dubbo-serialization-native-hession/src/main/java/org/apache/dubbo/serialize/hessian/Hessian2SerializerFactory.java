package org.apache.dubbo.serialize.hessian;

import org.apache.dubbo.serialize.hessian.serializer.java8.DurationHandle;
import org.apache.dubbo.serialize.hessian.serializer.java8.InstantHandle;
import org.apache.dubbo.serialize.hessian.serializer.java8.LocalDateHandle;
import org.apache.dubbo.serialize.hessian.serializer.java8.LocalDateTimeHandle;
import org.apache.dubbo.serialize.hessian.serializer.java8.LocalTimeHandle;
import org.apache.dubbo.serialize.hessian.serializer.java8.MonthDayHandle;
import org.apache.dubbo.serialize.hessian.serializer.java8.OffsetDateTimeHandle;
import org.apache.dubbo.serialize.hessian.serializer.java8.OffsetTimeHandle;
import org.apache.dubbo.serialize.hessian.serializer.java8.PeriodHandle;
import org.apache.dubbo.serialize.hessian.serializer.java8.YearHandle;
import org.apache.dubbo.serialize.hessian.serializer.java8.YearMonthHandle;
import org.apache.dubbo.serialize.hessian.serializer.java8.ZoneIdSerializer;
import org.apache.dubbo.serialize.hessian.serializer.java8.ZoneOffsetHandle;
import org.apache.dubbo.serialize.hessian.serializer.java8.ZonedDateTimeHandle;

import com.caucho.hessian.io.Deserializer;
import com.caucho.hessian.io.HessianProtocolException;
import com.caucho.hessian.io.Serializer;
import com.caucho.hessian.io.SerializerFactory;

import java.util.HashMap;

import static org.apache.dubbo.serialize.hessian.serializer.java8.Java8TimeSerializer.create;

/**
 * native-hessian序列化工厂
 */
public class Hessian2SerializerFactory extends SerializerFactory {
    private HashMap _serializerMap = new HashMap();
    private HashMap _deserializerMap = new HashMap();

    public Hessian2SerializerFactory() {
        super();
        if (isJava8()) {
            try {
                this.addSerializer(Class.forName("java.time.LocalTime"), create(LocalTimeHandle.class));
                this.addSerializer(Class.forName("java.time.LocalDate"), create(LocalDateHandle.class));
                this.addSerializer(Class.forName("java.time.LocalDateTime"), create(LocalDateTimeHandle.class));

                this.addSerializer(Class.forName("java.time.Instant"), create(InstantHandle.class));
                this.addSerializer(Class.forName("java.time.Duration"), create(DurationHandle.class));
                this.addSerializer(Class.forName("java.time.Period"), create(PeriodHandle.class));

                this.addSerializer(Class.forName("java.time.Year"), create(YearHandle.class));
                this.addSerializer(Class.forName("java.time.YearMonth"), create(YearMonthHandle.class));
                this.addSerializer(Class.forName("java.time.MonthDay"), create(MonthDayHandle.class));

                this.addSerializer(Class.forName("java.time.OffsetDateTime"), create(OffsetDateTimeHandle.class));
                this.addSerializer(Class.forName("java.time.ZoneOffset"), create(ZoneOffsetHandle.class));
                this.addSerializer(Class.forName("java.time.OffsetTime"), create(OffsetTimeHandle.class));
                this.addSerializer(Class.forName("java.time.ZonedDateTime"), create(ZonedDateTimeHandle.class));
            } catch (ClassNotFoundException e) {
                // ignore
            }
        }
    }

    @Override
    public Serializer getSerializer(Class cl) throws HessianProtocolException {
        if (isZoneId(cl)) {
            return ZoneIdSerializer.getInstance();
        }
        Object java8Serializer = this._serializerMap.get(cl);
        if (java8Serializer != null) {
            return (Serializer) java8Serializer;
        }
        return super.getSerializer(cl);
    }

    private static boolean isZoneId(Class cl) {
        try {
            return isJava8() && Class.forName("java.time.ZoneId").isAssignableFrom(cl);
        } catch (ClassNotFoundException e) {
            // ignore
        }
        return false;
    }

    private static boolean isJava8() {
        String javaVersion = System.getProperty("java.specification.version");
        return Double.valueOf(javaVersion) >= 1.8;
    }

    public void addSerializer(Class cl, Serializer serializer) {
        this._serializerMap.put(cl, serializer);
    }

    public void addDeserializer(Class cl, Deserializer deserializer) {
        this._deserializerMap.put(cl, deserializer);
    }

}
