import java.util.Map;
import java.util.HashMap;

import static java.lang.Float.parseFloat;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class App {
    public static void main(String[] args) {
//        staticFileLocation("/public");

        get("/", (request, response) -> {
            return new ModelAndView( new HashMap<>(), "form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/change.hbs", (request, response) -> {
            Map<String, Object>change = new HashMap();
            String dollarAmount = request.queryParams("dollarAmount");
            change.put("dollarAmount", dollarAmount);
            ChangeMachine changeMachine = new ChangeMachine();
            String myChange = changeMachine.makeChange(parseFloat(dollarAmount));
            change.put("myChangeString", myChange);
            return new ModelAndView(change, "change.hbs");
        }, new HandlebarsTemplateEngine());



    }
}
