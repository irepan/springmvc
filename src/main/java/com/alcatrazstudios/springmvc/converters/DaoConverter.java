package com.alcatrazstudios.springmvc.converters;

import com.alcatrazstudios.springmvc.domain.DomainObject;

import java.lang.reflect.ParameterizedType;

public abstract class DaoConverter<D extends DomainObject, W> {
    private Class<D> daoClazz;
    private Class<W> webClazz;

    public DaoConverter(){
        daoClazz =(Class)((ParameterizedType)this.getClass().
                getGenericSuperclass()).getActualTypeArguments()[0];
        webClazz = (Class)((ParameterizedType)this.getClass().
                getGenericSuperclass()).getActualTypeArguments()[1];
    }
    public final D convertToDao(W webObject){
        try {
            D newObject = daoClazz.newInstance();
            return convertToDao(newObject, webObject);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract D convertToDao(D original, W destiny);

    public final W convertFromDao(D daoObject){
        try {
            W newObject = webClazz.newInstance();
            return convertFromDao(newObject, daoObject);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract W convertFromDao(W destiny, D origin);
}
