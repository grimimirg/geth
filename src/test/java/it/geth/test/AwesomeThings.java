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
        String json = "<h1>AND HIS NAME IS JOHN CENA!</h1>";

        try
        {
            User user = new User();
            user.setUsername("pciffoli");
            json = new Operations().loadWhere(user).toJson();

        } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException ex)
        {
            Logger.getLogger(AwesomeThings.class.getName()).log(Level.SEVERE, null, ex);
        }

        exch.sendResponseHeaders(200, json.length());
        OutputStream os = exch.getResponseBody();
        os.write(json.getBytes());
        os.close();
    }

}
