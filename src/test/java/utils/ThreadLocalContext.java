package utils;

public class ThreadLocalContext<T> {

    private final ThreadLocal<T> generic = new ThreadLocal<>();
    private Class<T> clazz;


    public ThreadLocal<T> getThreadSafe() {
        return generic;
    }

    public void setThreadSafe(T t) {
        this.generic.set(t); // use the passed object, don't try to instantiate T
    }
}
