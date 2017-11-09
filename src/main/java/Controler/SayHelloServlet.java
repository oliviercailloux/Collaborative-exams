package Controler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

@WebServlet("/sayHelloServlet")
public class SayHelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setContentType(MediaType.TEXT_PLAIN);
		resp.setLocale(Locale.ENGLISH);
		@SuppressWarnings("resource")
		final ServletOutputStream out = resp.getOutputStream();
		out.println("Hello, world.");
	}
}
