package cu.uci.auctoritas;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by bichos on 7/06/16.
 */
public class MessageSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {


    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfiguration.class };
    }
}
