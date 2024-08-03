package jspboard.listener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import jspboard.dao.HikariConnector;
public class ContextLoaderListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		
		application.setAttribute("hikari" ,new HikariConnector(
				application.getInitParameter("drivername"),
				application.getInitParameter("jdbcUrl"),
				application.getInitParameter("username"),
				application.getInitParameter("password")
				));
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		
		((HikariConnector)application.getAttribute("hikari")).close();
	}
}
