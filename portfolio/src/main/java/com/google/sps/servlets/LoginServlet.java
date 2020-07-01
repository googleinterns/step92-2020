package com.google.sps.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");

    boolean isLoggedIn = false;

    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
      isLoggedIn = true;
      String userEmail = userService.getCurrentUser().getEmail();
      String logoutUrl = userService.createLogoutURL("/");

      response.getWriter().println("<p>Hello " + userEmail + "!</p>");
      response.getWriter().println("<p>Logout <a href=\"" + logoutUrl + "\">here</a>.</p>");
    } else {
      String loginUrl = userService.createLoginURL("/");

      response.getWriter().println(isLoggedIn);
      response.getWriter().println("<p>Hello stranger.</p>");
      response.getWriter().println("<p>Login <a href=\"" + loginUrl + "\">here</a>.</p>");
    }


  }
}
