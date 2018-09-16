package com.alekseysamoylov.repair.center.datatech.oleg.real;

/**
 * Created by alekseysamoylov on 8/10/16.
 */
public interface Permission<T> {
    /**
     * Возвращает битовую маску, представляющую право.
     */
    public long getMask();

    /**
     * Определяет, дано ли право заданному пользователю на заданный объект.
     *
     * @param user               пользователь, для которого проверяется право
     * @param targetDomainObject объект, для которого проверяется право
     * @return {@code true}, если у пользователя есть данное право на объект; {@code false} в противном случае
     */
    public boolean isGranted(User user, T targetDomainObject);
}
