package br.edu.iftm.ecommerce;

import br.edu.iftm.ecommerce.views.categoria.CategoryRegister;
import br.edu.iftm.ecommerce.views.menu.MenuView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.edu.iftm.ecommerce")
public class EcommerceApplication {

	public static void main(String[] args) {
            SpringApplication app = new SpringApplication(EcommerceApplication.class);
            app.setHeadless(false);
            ApplicationContext context = app.run(args);
            
            MenuView menuView = context.getBean(MenuView.class);
            menuView.setVisible(true);
        }

}
