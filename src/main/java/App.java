import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.text.DateFormat;
import java.util.NoSuchElementException;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("user", request.session().attribute("user"));
      model.put("animals", Animal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("sightings", Sighting.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/civilians/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Civilian civilian =  Civilian.find(Integer.parseInt(request.params(":id")));
      model.put("user", request.session().attribute("user"));
      model.put("animals", Animal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("sightings", Sighting.all());
      model.put("civilians", Civilian.all());
      model.put("template", "templates/viewer.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/rangers/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Ranger ranger = Ranger.find(Integer.parseInt(request.params(":id")));
      model.put("user", request.session().attribute("user"));
      model.put("animals", Animal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("sightings", Sighting.all());
      model.put("rangers", Ranger.all());
      model.put("template", "templates/viewer.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/login/civilian", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      boolean ranger = Boolean.parseBoolean(request.queryParams("ranger"));
      String name = request.queryParams("name");
      String phone = request.queryParams("phone");
      Civilian civilian = new Civilian(ranger, name, phone);
      civilian.save();
      request.session().attribute("user", civilian);
      response.redirect("/civilians/$civilian.getId()");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/login/ranger", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      boolean rangerStat = Boolean.parseBoolean(request.queryParams("ranger"));
      String name = request.queryParams("name");
      String phone = request.queryParams("phone");
      int rangerNumber = Integer.parseInt(request.queryParams("rangerNumber"));
      Ranger ranger = new Ranger(rangerStat, name, phone, rangerNumber);
      ranger.save();
      request.session().attribute("user", ranger);
      response.redirect("/rangers/"+ranger.getId());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/logout", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      request.session().removeAttribute("user");
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangered_sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int rangerStat = Integer.parseInt(request.queryParams("$ranger.getId()"));
      int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
      String latLong = request.queryParams("latLong");
      Sighting sighting = new Sighting(animalIdSelected, rangerStat, latLong);
      sighting.save();
      model.put("sighting", sighting);
      model.put("animals", EndangeredAnimal.all());
      String animal = EndangeredAnimal.find(animalIdSelected).getName();
      model.put("animal", animal);
      response.redirect("/");
      // response.redirect(request.headers("Referer"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sighting", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Ranger ranger = Ranger.find(Integer.parseInt(request.params(":id")));
      Integer rangerStat = ranger.getId();
      int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));
      String latLong = request.queryParams("latLong");
      Sighting sighting = new Sighting(animalIdSelected, rangerStat, latLong);
      sighting.save();
      model.put("sighting", sighting);
      model.put("animals", Animal.all());
      String animal = Animal.find(animalIdSelected).getName();
      model.put("animal", animal);
      response.redirect("/");
      // response.redirect(request.headers("Referer"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("user", request.session().attribute("user"));
      model.put("animals", Animal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("template", "templates/animal-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animal/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("user", request.session().attribute("user"));
      boolean endangered = request.queryParamsValues("endangered")!=null;
      if (endangered) {
        String name = request.queryParams("name");
        String health = request.queryParams("health");
        String age = request.queryParams("age");
        String color = request.queryParams("color");
        String description = request.queryParams("description");
        String gender = request.queryParams("gender");
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age, color, description, gender, endangered);
        endangeredAnimal.save();
        model.put("animals", Animal.all());
        model.put("endangeredAnimals", EndangeredAnimal.all());
      } else {
        String name = request.queryParams("name");
        String health = request.queryParams("health");
        String age = request.queryParams("age");
        String color = request.queryParams("color");
        String description = request.queryParams("description");
        String gender = request.queryParams("gender");
        Animal animal = new Animal(name, health, age, color, description, gender, endangered);
        animal.save();
        model.put("animals", Animal.all());
        model.put("endangeredAnimals", EndangeredAnimal.all());
      }
      response.redirect("/");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    get("/animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal animal = Animal.find(Integer.parseInt(request.params("id")));
      model.put("animal", animal);
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered_animal/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(request.params("id")));
      model.put("endangeredAnimal", endangeredAnimal);
      model.put("template", "templates/endangered_animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/error", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/error.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
