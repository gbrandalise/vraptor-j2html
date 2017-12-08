## vraptor-j2html

A simple engine for rendering html using the J2Html library, which uses class and methods for generating elements and html attributes.

The engine does not require the use of J2Html, since VRaptor conventions are still enabled.
It is only required in methods or classes that have the @J2HtmlRenderer annotation, ie it is possible to use the same view rendering class for all methods of a controller, or specifically for a method.

The library already has a default class for rendering the default container elements of an html document such as html, head, and body.
It also has a standard error page rendering.

All default classes can be overwritten using the Java CDI Api specification.


# installing

As the engine is not yet available in the maven central repository, you need to download the source code and generate .jar by running mvn clean package.
VRaptor-J2Html.jar can be added to a VRaptor project using maven:

```
		<dependency>
			<groupId>br.com.caelum.vraptor</groupId>
			<artifactId>vraptor-j2html</artifactId>
			<version>0.1.0</version> <!-- or lastest version available -->
			<scope>compile</scope>
		</dependency>
```

# view rendering

```
		public class Client {
			
			private String login;
			
			public String getLogin() {
				return login;
			}
			
			public void setLogin(String login) {
				this.login = login;
			}
		}
		
		@Controller
		public class HomeController {								
			
			@Path({"/", ""})
			@J2HtmlRenderer(HomeRenderer.class)
			public Client home() {
				Client client = new Client();
				client.setLogin("admin");
				return client;
			}
			
		}
		
		public class HomeRenderer implements ViewRenderer {
	
			private ViewAttributesWrapper attributes;
			
			public HomeRenderer(ViewAttributesWrapper attributes) {
				this.attributes = attributes;
			}
		
			@Override
			public DomContent[] getElements() throws ViewJ2HtmlException {
				Client client = attributes.getRequestAttribute("client", Client.class);
				return new DomContent[] {text(client.getLogin()), br()};
			}
		
		}
		
		
		<!-- final html -->
		<!DOCTYPE html>
		<html>
			<head>
			</head>
			<body>
				admin
			</body>
		</html>
```

# overwriting standard containers

```
		@Specializes
		@RequestScoped
		public class CustomBody extends DefaultBodyRenderer {
		
			@Override
			public ContainerTag getContainer(DomContent... content) throws ViewJ2HtmlException {
				List<DomContent> contents = Lists.newArrayList(content);
				contents.add(0, h1("Title"));
				return body(contents.toArray(new DomContent[0]));
			}
		
			@Override
			public ContainerTag getContainer(String content) throws ViewJ2HtmlException {		
				content = "<h1>Title</h1>" + content;
				return body(content);
			}
		
		}
		
		<!-- final html -->
		<!DOCTYPE html>
		<html>
			<head>
			</head>
			<body>
				<h1>Title</h1>
				admin
			</body>
		</html>
```

# help

J2Html site with many examples of use:

https://j2html.com/