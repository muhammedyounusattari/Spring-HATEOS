package younus.attari;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@ComponentScan("younus.attari")
@EnableWebMvc
public class MainConfigurationClass implements WebApplicationInitializer {

//	@Bean
//	public DataSource getDataSource(){
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://mysql.transcriptionhub.com/dbhybrid_transcription");
//		dataSource.setUsername("hybrid_trans");
//		dataSource.setPassword("trans#123");
//		//dataSource.setSchema("dbhybrid_transcription");
//		
//		return dataSource;
//	}
	
	@Bean
	public ViewResolver getBean() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	public void onStartup(ServletContext context) throws ServletException {

		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.setConfigLocation("younus.attari.MainConfigurationClass");

		ContextLoaderListener loadListener = new ContextLoaderListener(webContext);
		context.addListener(loadListener);

		ServletRegistration.Dynamic dispatcher = context.addServlet("Dispatcher", new DispatcherServlet(webContext));
		dispatcher.addMapping("/*");
		dispatcher.setLoadOnStartup(1);

	}

}
