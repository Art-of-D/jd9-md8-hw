import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;

@WebServlet
public class TimezoneValidateFilter extends HttpServlet{

    protected boolean checkV(HttpServletRequest req) {
        try {
            new TimeServlet().timeChecker(req);
            return true;
        } catch (DateTimeException e){
            return  false;
        }
    }
}
