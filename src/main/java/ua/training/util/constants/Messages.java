package ua.training.util.constants;

public interface Messages {
    String BADGE_OPEN_TAG = "<span class=\"badge progress-bar-danger\">";
    String BADGE_CLOSE_TAG = "</span>";
    String WRONG_PRICE_FORMAT_MESSAGE = "<div class=\"alert alert-danger\">Please, input price in format X.XX</div>";
    String SHOW_LOGIN_MODAL = "$(\"#login_modal\").modal(\"show\");";
    String NO_SUCH_USER = "No such user";
    String SHOW_REGISTER_MODAL = "$(\"#register_modal\").modal(\"show\");";
    String INVALID_EMAIL = "Invalid email";
    String INVALID_PASSWORD = "Password must be longer than 8 characters";
    String NOT_UNIQUE_EMAIL = "Email already registered";
    String REGISTER_SUCCESFUL = "<div class=\"alert alert-success\">You are registered! Now you can <a href=\"#\" class=\"alert-link\" data-toggle=\"modal\" data-target=\"#login_modal\">login</a></div>";
    String NOT_AUTHORIZED = "<div class=\"alert alert-danger\">You must be <a href=\"#\" class=\"alert-link\" data-toggle=\"modal\" data-target=\"#login_modal\">logged in</a> to subscribe</div>";
    String NOT_ENOUGH_MONEY = "<div class=\"alert alert-danger\">Not enough money on your balance</div>";
    String ALREADY_SUBSCRIBED = "<div class=\"alert alert-danger\">You are already subscribed to this periodical!</div>";
}
