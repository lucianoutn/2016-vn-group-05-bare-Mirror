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
		
		LoginController loginController = new LoginController();
		AdministradorController administradorController = new AdministradorController();
		TerminalController terminalController = new TerminalController();
		
		Spark.get("/user/login", loginController::login, engine);
		Spark.get("/user/checkLogin", loginController::checkLogin, engine);
		Spark.get("/terminal/show", terminalController::terminalShow, engine);
		Spark.get("/administrador/pois/show", administradorController::administradorPoisShow, engine);
		
		
		
	}
}
