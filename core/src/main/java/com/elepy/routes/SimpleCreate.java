package com.elepy.routes;

import com.elepy.concepts.ObjectEvaluator;
import com.elepy.dao.Crud;
import com.elepy.di.ElepyContext;
import com.elepy.exceptions.ElepyException;
import com.elepy.http.HttpContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * A helper class for developers to easily authenticate the creation of objects.
 *
 * @param <T> the model you're updating
 * @see com.elepy.annotations.Create
 * @see DefaultCreate
 * @see CreateHandler
 */
public abstract class SimpleCreate<T> extends DefaultCreate<T> {

    @Override
    public void handleCreate(HttpContext context, Crud<T> dao, ElepyContext elepy, List<ObjectEvaluator<T>> objectEvaluators, Class<T> clazz) throws Exception {

        try {

            String body = context.request().body();

            ObjectMapper objectMapper = elepy.getObjectMapper();
            T item = objectMapper.readValue(body, dao.getType());

            beforeCreate(item, dao, elepy);

            super.handleCreate(context, dao, elepy, objectEvaluators, clazz);

            afterCreate(item, dao, elepy);
            context.response().status(200);
            context.response().result("OK");

        } catch (JsonMappingException e) {
            throw new ElepyException("Error mapping SimpleCreate: " + e.getMessage());
        }
    }

    /**
     * What happens before you create a model. Throw an exception to cancel the creation.
     *
     * @param objectForCreation The object before you create it
     * @param crud              the crud implementation
     * @param elepy             the context where you can GET context objects
     * @throws Exception you can throw any exception and Elepy handles them nicely.
     * @see ElepyException
     * @see com.elepy.exceptions.ElepyErrorMessage
     */
    public abstract void beforeCreate(T objectForCreation, Crud<T> crud, ElepyContext elepy) throws Exception;

    /**
     * What happens after you create a model.
     *
     * @param createdObject The object after you created it
     * @param crud          the crud implementation
     * @param elepy         the context where you can GET context objects
     */
    public abstract void afterCreate(T createdObject, Crud<T> crud, ElepyContext elepy);
}
