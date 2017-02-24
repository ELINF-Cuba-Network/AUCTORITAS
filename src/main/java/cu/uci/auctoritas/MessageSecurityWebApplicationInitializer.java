package cu.uci.auctoritas;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class MessageSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {


    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfiguration.class };
    }
}
