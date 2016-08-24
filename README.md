# geth

We are "geth-ing" ready this project for easy-to-use interactions between databases and applications.


##What geth is and what geth do?

1. Quick and ready back-end APIs
2. Easy way to build your REST server
3. Less code to write your queries
4. Can be included in any project
5. Scalable
7. Easy

...and...

"less configurations" == "less stress" == "more free time!"


## How to use?
(see /test folder for a complete working example with postgres)

1) Have a nice configuration 

```java
@ConfigurationAdapter
public class Configurations extends MainAdapter
{

    @Override
    public void configureDatabaseAdapter(Descriptor descriptor)
    {
        descriptor.addProperty("hibernate.dialect", "org.hibernate.dialect.databaseDialect");
        descriptor.addProperty("hibernate.connection.url", "jdbc:database://my.database.com:5432/databaseName");
        descriptor.addProperty("hibernate.connection.username", "username");
        descriptor.addProperty("hibernate.connection.password", "password");

        descriptor.addAnnotatedClass(MyModel.class);

        descriptor.setServerUp(true);
        descriptor.setSocket(60000);

        descriptor.addModule(MyModelModule.class.getName());
    }

}
```

2) Build your application context

```java
ApplicationContext.buildContext("it.geth.test");
```

3) Deal with it

```java
MyModel model = new MyModel();
model.setMyProperty("something");
String json = new Operations().loadWhere(model).toJson();
System.out.println(json);
```

Exposing APIs

```java
SingleHttpServer.getCurrentInstance().addModule(new RestHandler(MyModel.class));
```

with where conditions

```java
MyModel model = new MyModel();
model.setMyProperty("something");
SingleHttpServer.getCurrentInstance().addModule(new RestHandler(model));
```

This software is distributed under the <a href="http://www.wtfpl.net/"><img
       src="http://www.wtfpl.net/wp-content/uploads/2012/12/wtfpl-badge-4.png"
       width="80" height="15" alt="WTFPL" /></a> license. <b>Have fun!</b>
