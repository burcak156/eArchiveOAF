/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxxt.oracle.apps.xxxt.einvoice.earchive.webui;

import java.io.InputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import xxxt.oracle.apps.xxxt.einvoice.earchive.server.EArchiveAMImpl;

/**
 * Controller for ...
 */
public class EArchiveMainCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        EArchiveAMImpl am = 
            (EArchiveAMImpl)pageContext.getApplicationModule(webBean);
        am.setPolicies(pageContext);
        am.initSearchPanel();

    }

    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        EArchiveAMImpl am = 
            (EArchiveAMImpl)pageContext.getApplicationModule(webBean);
        String event = pageContext.getParameter("event");
        if ("xSearch".equals(event)) {
            am.initSearch();
        } else if ("xClear".equals(event)) {
            am.clearSearch();
        } else if ("xSend".equals(event)) {
            am.sendInvoices(pageContext);
        } else if ("xCancel".equals(event)) {
            am.cancelInvoices(pageContext);
        } else if ("xUpdateEmail".equals(event)) {
            am.updateEmailOfInvoices(pageContext);
        } else if ("xview".equals(event)) {
            String id = pageContext.getParameter("xid");
            String fileName = null;
            String fileType = null;
            HttpServletResponse response = 
                (HttpServletResponse)pageContext.getRenderingContext().getServletResponse();
            am.getEInvoicePDF(Integer.parseInt(id), response);


        } else if ("xDraftCancel".equals(event)) {
            am.cancelDraftInvoices(pageContext);
        }


    }

}
