package com.elepy.evaluators;

import com.elepy.describers.FieldDescriber;
import com.elepy.exceptions.ElepyException;

import java.lang.reflect.Field;

public class DefaultObjectUpdateEvaluator<T> implements ObjectUpdateEvaluator<T> {
    @Override
    public void evaluate(T before, T updated) throws IllegalAccessException {
        for (Field field : before.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            FieldDescriber describer = new FieldDescriber(field);
            if (!describer.isEditable() && !field.get(before).equals(field.get(updated))) {
                throw new ElepyException("Not allowed to edit: " + describer.getPrettyName());
            }
        }
    }
}
