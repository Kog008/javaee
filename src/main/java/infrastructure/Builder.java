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

    @SuppressWarnings("unchecked")
    private E newInstance() {

        // return value for newInstance()
        E newInstance = null;
        // returns the calling class of the Builder<E> → not E itself

        Class<?> builderSubclass = getClass();
        // returns the given input generic class from the Builder<E> → returns E
        Type genericSuperclass = builderSubclass.getGenericSuperclass();

        // If we have several generics in superclass, I can go ahead and cast the Type E to ParameterizedType E
        // to get access via getActualTypeArguments to all calling generics in superclass.
        // Having the Type E I get fetch its class and so the constructor of this class.
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        // Select the desired generic.
        Type valueType = parameterizedType.getActualTypeArguments()[0];
        Class<E> targetType = (Class<E>) valueType;

        try {
            Constructor<E> constructor = targetType.getDeclaredConstructor();
            constructor.setAccessible(true);
            newInstance = constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return newInstance;
    }

    protected void validate() {
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
