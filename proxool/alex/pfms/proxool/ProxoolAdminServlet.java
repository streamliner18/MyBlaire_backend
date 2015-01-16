package alex.pfms.proxool;


import javax.servlet.http.HttpServlet;

import org.logicalcobwebs.proxool.ProxoolFacade;
import org.logicalcobwebs.proxool.admin.servlet.AdminServlet;

import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;


/**
 * 关闭proxool连接池
 * @author long
 *
 */

public class ProxoolAdminServlet extends AdminServlet {
	private static final long serialVersionUID = 3205095536164467920L;
	@Override
	public void destroy() {
		ProxoolFacade.shutdown();
		super.destroy();
	}
}
