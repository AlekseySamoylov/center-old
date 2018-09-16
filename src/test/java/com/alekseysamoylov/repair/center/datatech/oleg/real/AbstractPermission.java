package com.alekseysamoylov.repair.center.datatech.oleg.real;

/**
 * Created by alekseysamoylov on 8/10/16.
 */

public abstract class AbstractPermission<T> implements Permission<T> {
    protected long mask;

    /**
     * Создает новое право по заданной битовой маске.
     * @param mask битовая маска
     */
    public AbstractPermission(long mask) {
        this.mask = mask;
    }

    @Override
    public long getMask() {
        return mask;
    }

    @Override
    /**
     * Если пользователь имеет роль Суперпользователь - он имеет права на любые операции. Для всех других - выполняется проверка
     * {@code isGrantedInternal}
     */
    public boolean isGranted(User user, T targetDomainObject) {
        return true;
    }

    /**
     * Внутренняя проверка права заданного пользователю на объект.
     *
     * @param user   пользователь, для которого проверяется право
     * @param targetDomainObject объект, для которого проверяется право
     * @return {@code true}, если у пользователя есть право на объект; {@code false} в противном случае
     */
    protected abstract boolean isGrantedInternal(User user, T targetDomainObject);

}