import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static spark.Spark.*;


public class Main {
//    private static final List<User> users = Stream.of(new User(1L, "Mikhael", 24, "st.Mahnovicha 49"),
//            new User(2L, "Stas", 27, "Truda 8"))
//            .collect(Collectors.toList());

    public static void main(String[] args) {
        port(8080);
        get("/hello/:name", (req, res) -> {
            return "Hello, " + req.params(":name");
        });
        get("/users/:id", (req, res) -> {
            Long id = Long.valueOf(req.params(":id"));
            User user = UserServise.select(id);
            return new Gson().toJson(user);
        });
        post("/users", ((request, response) -> {
            response.header("content-type", "");
            response.status(200);
            User user = new Gson().fromJson(request.body(), User.class);
            UserServise.create(user);
            return "";
        }));
        delete("/users/:id", ((request, response) -> {
            Long id = Long.valueOf(request.params(":id"));
                UserServise.delete(id);
            return "";
        }));
        put("/users/:id", ((request, response) -> {
            Long id = Long.valueOf(request.params(":id"));
            User newUser = new Gson().fromJson(request.body(), User.class);
            UserServise.change(id, newUser);
            return "";
        }));
    }
}
