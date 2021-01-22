/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author victor.barba
 */
public class SecurityCheckFilter implements Filter {

    private final static String FILTER_APPLIED = "_security_filter_applied";
//    private List nonRestrictedPages;

//    /**
//     * This method returns true if the page is not restricted like the landing
//     * page
//     */
//    public boolean isNonRestrictedPage(String page) {
//        Iterator it = nonRestrictedPages.iterator();
//        boolean result = false;
//        while (it.hasNext()) {
//            String nonrestrictedPage = (String) it.next();
//            System.out.println("isNonRestrictedPage " + nonrestrictedPage);
//            if (page.endsWith(nonrestrictedPage)) {
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        nonRestrictedPages = new ArrayList();
//        // index.jsp is the landing page of the application
//        // and needs no logging in
//        nonRestrictedPages.add("index.xhtml");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
HttpServletRequest hreq = (HttpServletRequest) request;
        HttpServletResponse hres = (HttpServletResponse) response;
//        HttpSession session = hreq.getSession();
//        Usuario user = (Usuario) session.getAttribute("user");
//        System.out.println("doFilter " + user);
        String url = hreq.getRequestURI().trim();
        if (url.contains("reportes/") 
                || url.contains("general/") 
                || url.contains("resources/css")
                || url.contains("resources/js")                
                || url.contains("pages/")
                || url.contains("sessionExpired")) {
            String pagPrincipal = hreq.getRequestURL().substring(0, hreq.getRequestURL().indexOf(url)) + hreq.getContextPath() + "/principal.xhtml";
            hres.sendRedirect(pagPrincipal);
        } else {
            try {
                chain.doFilter(request, response);
            } catch (IOException e) {
                if (!e.getClass().getSimpleName().equals("ClientAbortException")) {
                    throw e;
                }
            }
        }
    }

    @Override
    public void destroy() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//            HttpServletRequest hreq = (HttpServletRequest) request;
//        HttpServletResponse hres = (HttpServletResponse) response;
//        HttpSession session = hreq.getSession();
//        String requestedSession = hreq.getRequestedSessionId();
//        String currentWebSession = hreq.getSession().getId();
//        String currentpage = hreq.getPathTranslated();
//        System.out.println("doFilter requestedSession " + requestedSession + " currentWebSession " + currentWebSession + " currentpage " + currentpage);
//        Usuario user = (Usuario) session.getAttribute("user");
//        System.out.println("doFilter " + user);
//            if (user != null) {
//            chain.doFilter(request, response);
//        } else {
//            hres.sendRedirect(hreq.getContextPath() + "/index.jsf");
//        }
    // if currentpage is null or is not a jsp page don't do any filtering
//        if (currentpage == null || !(currentpage.endsWith(".xhtml"))) {
//            if (chain != null) {
//                chain.doFilter(request, response);
//            }
//            return;
//        }
//
//        // if the page is restricted and this filter is not
//        // already applied do this block
//        if (isNonRestrictedPage(currentpage) == false && request.getAttribute(FILTER_APPLIED) == null) {
//
//            request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
//            //If the session bean is not null get the session bean property username.
//            Usuario user = (Usuario) session.getAttribute("user");
//            // if the user is not set and the current page is not
//            // the login page then redirect back to login page
//            System.out.println("user "+user);
//            if (user == null && (!currentpage.endsWith("principal.xhtml"))) {
//                hres.sendRedirect("principal.xhtml");
//                return;
//            }
//        }
//        //deliver request to next filter
//        if (chain != null) {
//            chain.doFilter(request, response);
//        }
}
