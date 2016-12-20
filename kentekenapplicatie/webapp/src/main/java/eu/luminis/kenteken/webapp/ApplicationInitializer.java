package eu.luminis.kenteken.webapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Web application Java configuration class. The usage of web application initializer requires Spring Framework 3.1 and
 * Servlet 3.0.
 */
public class ApplicationInitializer implements WebApplicationInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationInitializer.class);
    
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        LOG.info("Initializing Kenteken web application");

        final AnnotationConfigWebApplicationContext rootContext = this.createApplicationContext();
        addApacheCxfServlet(servletContext);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        LOG.info("Initialized Kenteken web application");
    }

    private void addApacheCxfServlet(final ServletContext servletContext) {
        final CXFServlet cxfServlet = new CXFServlet();
        final ServletRegistration.Dynamic appServlet = servletContext.addServlet("CXFServlet", cxfServlet);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/*");
    }

    private AnnotationConfigWebApplicationContext createApplicationContext() {
        final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationContext.class);
        return rootContext;
    }
}
