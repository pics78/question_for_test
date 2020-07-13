package route;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import models.QuestionsModel;
import services.QuestionDataService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class MainRouting {

	public static void main(String[] args) {

		port(8080);
		staticFiles.location("/");

		get("/", (req, res) -> {
			res.redirect("/home");
			return null;
		});

		get("/home", new TemplateViewRoute() {
			@Override
			public ModelAndView handle(Request req, Response res) throws Exception {
				Map<String, Object> model = new HashMap<>();
				model.put("title", "ホーム");

				return new ModelAndView(model, "home");
			}
		}, new ThymeleafTemplateEngine());

		get("/question", new TemplateViewRoute() {
			@Override
			public ModelAndView handle(Request req, Response res) throws Exception {
				Map<String, Object> model = new HashMap<>();
				model.put("title", "問題");

				String subjectName = req.queryParams("subject");
				if (subjectName == null) {
					res.redirect("/home");
					return null;
				}
				QuestionsModel targetQuestionsModel = QuestionDataService.getQuestionsModelFor(subjectName);
				model.put("questionsModel", targetQuestionsModel);

				return new ModelAndView(model, "question");
			}
		}, new ThymeleafTemplateEngine());
	}
}
