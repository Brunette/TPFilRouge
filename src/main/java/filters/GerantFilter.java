package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

/**
 * Servlet Filter implementation class GerantFilter
 */
@WebFilter(urlPatterns = { "/gerant", "/modify*" })
public class GerantFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		User user = (User) req.getSession().getAttribute("superuser");
//		System.out.println("doFilter Gerant");
//
//		if (user != null && user.getAdmin() != null) {
//			chain.doFilter(request, response);
//		} else {
//			HttpServletResponse resp = (HttpServletResponse) response;
//			resp.sendRedirect("acceuil");
//		}
		chain.doFilter(request, response);
	}
}
