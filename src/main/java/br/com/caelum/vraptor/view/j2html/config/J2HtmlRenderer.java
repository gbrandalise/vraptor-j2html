package br.com.caelum.vraptor.view.j2html.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.caelum.vraptor.view.j2html.rederers.ViewRenderer;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface J2HtmlRenderer {

	Class<? extends ViewRenderer> value();

}
