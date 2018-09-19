package store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("LoginInterceptor.afterHandle\trequest:" + request + "\tresponse:" + response);
		System.out.println("\thandler:" + handler);
		System.out.println("\tException:" + ex);
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("LoginInterceptor.postHandle\trequest:" + request + "\tresponse:" + response);
		System.out.println("\thandler:" + handler);
		System.out.println("\tmodelAndView:" + modelAndView);
	}

	/**
	 * 处理请求之前
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("LoginInterceptor.preHandle\trequest:" + request + "\tresponse:" + response);
		System.out.println("\thandler:" + handler);
		// 获取session对象
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			// 没有登录，则重定向到主页
			String url = request.getContextPath() + "/main/index.do";
			response.sendRedirect(url);
			return false;
		}
		return true;
	}

}
