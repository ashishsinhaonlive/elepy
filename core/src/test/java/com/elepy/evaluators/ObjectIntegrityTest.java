package com.elepy.evaluators;

import com.elepy.BaseFongo;
import com.elepy.dao.jongo.DefaultMongoDao;
import com.elepy.exceptions.ElepyException;
import com.elepy.models.Resource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ObjectIntegrityTest extends BaseFongo {

    @Test
    public void testIntegrityUnique() throws Exception {
        super.setUp();
        DefaultMongoDao<Resource> defaultMongoDao = new DefaultMongoDao<>(getDb(), "resources", Resource.class);
        final DefaultIntegrityEvaluator<Resource> evaluator = new DefaultIntegrityEvaluator<>();
        defaultMongoDao.create(validObject());


        defaultMongoDao.create(validObject());
        try {
            evaluator.evaluate(validObject(), defaultMongoDao);
            fail("Was supposed to throw a rest error message");
        } catch (ElepyException ignored) {

        }
    }
}
