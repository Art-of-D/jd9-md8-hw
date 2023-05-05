import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;

public class TimezoneValidateFilter {

    protected boolean checkV(HttpServletRequest req) {
        try {
            new TimeServlet().timeChecker(req);
            return true;
        } catch (DateTimeException e){
            return  false;
        }
    }
}
