package controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("v1")
public class MyApp extends Application {
	/** Empty. The server will then discover all resource classes automatically. */
}