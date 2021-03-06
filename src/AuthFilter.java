import java.io.IOException;
import ru.mirea.web.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		//System.out.println("AuthFilter запустился");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(true);
		Object userAttr = session.getAttribute("user");
		HttpServletResponse response = (HttpServletResponse)resp;
		if (userAttr != null) {
			User user = (User)userAttr;
			if (user.logged_in) {
				response.sendRedirect("/Lab/SiteContentServlet");
				//System.out.println("И сделал редирект");
			}
		} else {
			chain.doFilter(req, (ServletResponse)response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
