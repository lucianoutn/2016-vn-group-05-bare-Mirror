package ServerSide;

import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.*;
import spark.Spark;

import static spark.Spark.post;

import com.sun.mail.iap.Response;

import Spark.*;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue).build();

		
		Spark.staticFileLocation("/public");

		LoginController loginController = new LoginController();
		AdministradorController administradorController = new AdministradorController();
		TerminalController terminalController = new TerminalController();

		Spark.get("/", (request, response) -> {
			response.redirect("/user/login");
			return null;
		});
		Spark.get("/user/login", loginController::login, engine);
		Spark.get("/user/login", loginController::login, engine);
		Spark.get("/user/checkLogin", loginController::checkLogin);
		Spark.get("/terminal/show", terminalController::terminalShow, engine);
		Spark.get("/terminal/new", terminalController::terminalNew, engine);
		Spark.post("/terminal/new", terminalController::crear);
		Spark.get("/administrador/pois/show", administradorController::administradorPoisShow, engine);

	}
}
