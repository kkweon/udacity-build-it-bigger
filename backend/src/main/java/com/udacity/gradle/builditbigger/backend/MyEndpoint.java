package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import dev.kkweon.joke.JokeFactory;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.logging.Logger;
import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
    name = "myApi",
    version = "v1",
    namespace =
            @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
            )
)
public class MyEndpoint {

    private static Logger mGlobal = Logger.getGlobal();

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name = "jokes", path = "jokes", httpMethod = ApiMethod.HttpMethod.GET)
    public JokeResponse getJoke() {
        try {
            FutureTask<String> joke = JokeFactory.getJoke();
            Executors.newCachedThreadPool().submit(joke);
            return new JokeResponse(joke.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            mGlobal.warning("JokeResponse returns InterruptedException");
            return new JokeResponse("ERROR");
        }
    }
}
