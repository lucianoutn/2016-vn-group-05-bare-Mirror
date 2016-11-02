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
		
		ConsultaDePoisController consultorasDePoisController = new ConsultaDePoisController();
		
		Spark.get("/user/login", consultorasDePoisController::login, engine);
		Spark.get("/user/checkLogin", consultorasDePoisController::checkLogin, engine);
		Spark.get("/terminal/show", consultorasDePoisController::terminalShow, engine);
		Spark.get("/administrador/pois/show", consultorasDePoisController::administradorPoisShow, engine);
		
		
		
	}
}
