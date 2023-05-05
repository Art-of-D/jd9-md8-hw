import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

//http://localhost:8080/jd9-md8-hw/time?timezone=18

@WebServlet(value = "/time")
public class Time extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        if (TimezoneValidateFilter(req)){
            resp.getWriter().write(timeChecker(req));
            resp.getWriter().close();
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid timezone");
        }
    }

    private boolean TimezoneValidateFilter(HttpServletRequest req) throws ServletException {
        try {
            timeChecker(req);
            return true;
        } catch (DateTimeException e){
            return  false;
        }
    }

    private String timeChecker(HttpServletRequest req){
        String timeFormate = "дата: dd.MM.yyyy, час: HH:mm:ss, зона: z";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormate);
        String currentTime;
        if (!req.getParameterMap().containsKey("timezone")) {
            currentTime = formatter.format(ZonedDateTime.now(ZoneId.of("UTC")));
        } else {
            currentTime = formatter.format(
                    ZonedDateTime.now(ZoneId.of("UTC+" + req.getParameter("timezone"))));
        }
        return currentTime;
    }
}