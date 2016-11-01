package ServerSide;

import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.*;
import spark.Spark;
import Spark.*;


public class Router {
	
	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();
	
		Spark.staticFiles.location("/public");
		
		UserController userController = new UserController();
		
		Spark.get("/user/login", userController::login, engine);
		
		
	}
}
