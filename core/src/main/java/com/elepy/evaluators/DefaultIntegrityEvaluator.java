package com.elepy.evaluators;

import com.elepy.dao.Crud;
import com.elepy.exceptions.ElepyException;
import com.elepy.utils.ClassUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public class DefaultIntegrityEvaluator<T> implements IntegrityEvaluator<T> {
    @Override
    public void evaluate(T item, Crud<T> dao, boolean insert) {
        try {
            checkUniqueness(item, dao, insert);
        } catch (IllegalAccessException e) {
            throw new ElepyException("Can't reflectively checkUniqueness()", 500);
        }
    }

    private void checkUniqueness(T item, Crud<T> dao, boolean insert) throws IllegalAccessException {

        List<Field> uniqueFields = ClassUtils.getUniqueFields(item.getClass());


        Optional<Object> id = ClassUtils.getId(item);

        if (insert && id.isPresent() && dao.getById(id.get()).isPresent()) {
            throw new ElepyException("Duplicate ID's", 400);
        }

        for (Field field : uniqueFields) {
            field.setAccessible(true);
            Object prop = field.get(item);

            final List<T> foundItems = dao.searchInField(field, prop == null ? "" : prop.toString());
            if (foundItems.size() > 0) {

                if (foundItems.size() > 1) {
                    throw new ElepyException(String.format("An item with the %s: '%s' already exists in the system!", ClassUtils.getPrettyName(field), String.valueOf(prop)));
                }

                T foundRecord = foundItems.get(0);
                final Optional<Object> foundId = ClassUtils.getId(foundRecord);
                if ((id.isPresent() || foundId.isPresent()) && !id.equals(foundId)) {
                    throw new ElepyException(String.format("An item with the %s: '%s' already exists in the system!", ClassUtils.getPrettyName(field), String.valueOf(prop)));
                }
            }
        }

    }


}
