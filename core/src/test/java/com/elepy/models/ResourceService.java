package com.elepy.models;

import com.elepy.annotations.Inject;
import com.elepy.annotations.Route;
import com.elepy.dao.Crud;
import com.elepy.dao.ResourceDao;
import com.elepy.routes.DefaultService;
import spark.Request;
import spark.Response;
import spark.route.HttpMethod;

import java.util.Optional;

public class ResourceService extends DefaultService<Resource> {

    @Inject(tag = "/resources", classType = Crud.class)
    private ResourceDao crud;

    @Route(path = "/resources/:id/extra", requestMethod = HttpMethod.get)
    public void extraRoute(Request request, Response response) {

        Optional<Resource> id = crud.getById(request.params("id"));

        if (id.isPresent()) {
            response.status(200);
            response.body(id.get().getTextField());
        } else {
            response.status(400);
            response.body("I am not here");
        }
    }
}
