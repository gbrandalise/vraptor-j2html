package br.com.caelum.vraptor.view.j2html.util;

import javax.enterprise.inject.Vetoed;
import javax.servlet.http.HttpServletRequest;

@Vetoed
public class ViewAttributesWrapper {

	private HttpServletRequest request;

	public ViewAttributesWrapper(HttpServletRequest request) {
		this.request = request;
	}
		
	public <T> T getRequestAttribute(String attributeName, Class<T> clazz) {
		return getAttribute(attributeName, clazz, false);
	}
	
	
	public <T> T getSessionAttribute(String attributeName, Class<T> clazz) {
		return getAttribute(attributeName, clazz, true);
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getAttribute(String attributeName, Class<T> clazz, boolean session) {
		Object o = null;
		if (session) {
			o = request.getSession().getAttribute(attributeName);
		} else {
			o = request.getAttribute(attributeName);
		}
		if (o != null &&
			clazz.isAssignableFrom(o.getClass())) {
			return (T) o;
		}
		return null;
	}
	
}
