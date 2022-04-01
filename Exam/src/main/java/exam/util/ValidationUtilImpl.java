package exam.util;

public class ValidationUtilImpl<E> implements ValidationUtil<E> {
    @Override
    public boolean isValid(E entity) {
        return entity == null;
    }
}
