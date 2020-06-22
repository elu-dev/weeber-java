package elu.jsf.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthMiddleware", urlPatterns = { "*.xhtml" })
public class AuthMiddleware implements Filter {

	public AuthMiddleware() {}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	@Override
	public void destroy() {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			if (reqURI.startsWith("/") || reqURI.startsWith("/home")
					|| (ses != null && ses.getAttribute("username") != null)
					|| reqURI.indexOf("/public/") >= 0
					|| reqURI.contains("javax.faces.resource")) {
				
				if ((reqURI.endsWith("/feed.xhtml")) && (ses == null || ses.getAttribute("username") == null)) {
					resp.sendRedirect(reqt.getContextPath() + "/home.xhtml");
				} else chain.doFilter(request, response);
			
			} else
				resp.sendRedirect(reqt.getContextPath() + "/home.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}