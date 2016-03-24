package it.geth.test;

import it.geth.test.model.User;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.geth.core.db.Operations;
import it.grimi.modularserver.core.ModuleUtilities;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AwesomeThings extends ModuleUtilities implements HttpHandler
{

    /**
     *
     * @param exch
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exch) throws IOException
    {
        try {
            User user = new User();
            user.setUsername("ffrosky");
            String json = new Operations().loadWhere(user).toJson();

            exch.sendResponseHeaders(200, json.length());
            OutputStream os = exch.getResponseBody();
            os.write(json.getBytes());
            os.close();
        } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException ex) {
            Logger.getLogger(AwesomeThings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
