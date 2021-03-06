package com.elepy;


import com.elepy.http.HttpService;

public interface ElepyModule {

    /**
     * This method is used by Elepy to let a module add extra configuration.
     *
     * @param http  The SparkJava Service
     * @param elepy A link to the Elepy object where extra configuration can happen
     */
    void beforeElepyConstruction(HttpService http, ElepyPreConfiguration elepy);

    /**
     * This method is used by Elepy to let a module execute it's functionality after Elepy is completely setup.
     *
     * @param http  The SparkJava Service
     * @param elepy a link to Elepy after it has been constructed and all the dependencies have been set and Elepy specific routes have been generated
     */
    void afterElepyConstruction(HttpService http, ElepyPostConfiguration elepy);


}
