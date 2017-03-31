import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          model.put("template", "templates/content.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    get("/admin", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          model.put("add-stylist", "templates/add-stylist.vtl");
          model.put("template", "templates/admin.vtl");
          model.put("all-stylist", Stylists.all());
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    post("/create-stylist", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          String name = request.queryParams("name");
          String description = request.queryParams("description");
          Stylists newStylist = new Stylists(name, description);
          newStylist.save();
          model.put("all-stylist", Stylists.all());
          model.put("add-stylist", "templates/add-stylist.vtl");
          model.put("template", "templates/admin.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    get("/stylist/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          int stylists_id = Integer.parseInt(request.params("id"));
          Stylists foundStylist = Stylists.find(stylists_id);
          model.put("all-clients", Clients.stylistAll(stylists_id));
          model.put("update-stylist", "templates/update-stylist.vtl");
          model.put("found-stylist", foundStylist);
          model.put("create-client", "templates/create-client.vtl");
          model.put("template", "templates/admin.vtl");
          model.put("all-stylist", Stylists.all());
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    post("/stylist/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          int stylists_id = Integer.parseInt(request.params("id"));
          Stylists foundStylist = Stylists.find(stylists_id);
          String name = request.queryParams("name");
          String description = request.queryParams("description");
          Clients newClient = new Clients(name, description, stylists_id);
          newClient.save();
          model.put("all-clients", Clients.stylistAll(stylists_id));
          model.put("found-stylist", foundStylist);
          model.put("all-stylist", Stylists.all());
          model.put("update-stylist", "templates/update-stylist.vtl");
          model.put("add-stylist", "templates/add-stylist.vtl");
          model.put("template", "templates/admin.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    post("/update-stylist/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          int stylists_id = Integer.parseInt(request.params("id"));
          Stylists foundStylist = Stylists.find(stylists_id);
          String newDescription = request.queryParams("newDescription");
          foundStylist.update(newDescription);
          int id = foundStylist.getId();
          response.redirect("/stylist/"+id);
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    get("/delete-client/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Clients foundClient = Clients.find(Integer.parseInt(request.params("id")));
          int stylistId = foundClient.getStylistId();
          foundClient.delete();
          response.redirect("/stylist/"+stylistId);
          model.put("all-stylist", Stylists.all());
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


    get("/delete-stylist/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          int stylists_id = Integer.parseInt(request.params("id"));
          Stylists foundStylist = Stylists.find(stylists_id);
          foundStylist.delete();
          response.redirect("/admin");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    get("/edit-client/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          int client_id = Integer.parseInt(request.params("id"));
          Clients foundClient = Clients.find(client_id);
          model.put("client", foundClient);
          model.put("template", "templates/edit-client.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    post("/edit-client/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          int client_id = Integer.parseInt(request.params("id"));
          Clients foundClient = Clients.find(client_id);
          String editName = request.queryParams("editName");
          String editInfo = request.queryParams("editInfo");
          if(!editName.equals("")){
            foundClient.updateName(editName);
          }
          if(!editInfo.equals("")){
            foundClient.updateInfo(editInfo);
          }
          model.put("client", foundClient);
          model.put("template", "templates/edit-client.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


  }
}