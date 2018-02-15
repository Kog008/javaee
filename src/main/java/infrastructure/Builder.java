package infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * This Builder class uses a generics, which is meant for the target class we are
 * creating objects for.
 *
 * @param <E>       The entity object, which we are creating with the builder for.
 */
public abstract class Builder<E> {

    // my target entity and hence the output of my builder
    private E instance;

    protected Builder() {
        instance = newInstance();
    }

    private E newInstance() {

        E newInstance = null;
        Class<?> builderSubclass = getClass();
        Type genericSuperclass = builderSubclass.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type valueType = parameterizedType.getActualTypeArguments()[0];
        Class<E> targetType = (Class<E>) valueType;

        try {
            Constructor<E> c = targetType.getDeclaredConstructor();
            c.setAccessible(true);
            newInstance = c.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return newInstance;
    }

    private void validate() {
        // Hibernate is almost a certified implementor of Bean Validation 2.0, but you
        // may override this method, if you want to validate your entity objects
        // in respect to every kind of possible validation.
    }

    public E build() {
        validate();
        return instance;
    }

    protected E getInstance() {
        return instance;
    }
}
