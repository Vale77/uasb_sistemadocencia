/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.utils;

import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/**
 *
 * @author victor.barba
 */
public class LDAPAuthentication {

    static String ATTRIBUTE_FOR_USER = "sAMAccountName";

    public LdapContext getLdapContext(String username, String password, String strDomain, String strHost)
            throws CommunicationException, AuthenticationNotSupportedException, AuthenticationException, NamingException {
        LdapContext ctx = null;
        try {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.SECURITY_AUTHENTICATION, "Simple");
            env.put(Context.SECURITY_PRINCIPAL, username + "@" + strDomain);
            env.put(Context.SECURITY_CREDENTIALS, password);
            // Using starndard Port, check your instalation
            env.put(Context.PROVIDER_URL, "ldap://" + strHost);
            env.put(Context.REFERRAL, "follow");
            ctx = new InitialLdapContext(env, null);
        } catch (CommunicationException ex) {
            throw new CommunicationException("Conección LDAP FALLIDA: server no valido ");
        } catch (AuthenticationNotSupportedException ex) {
            throw new AuthenticationNotSupportedException("Autenticación no soportada por el servidor");
        } catch (AuthenticationException ex) {
            throw new AuthenticationException("Autenticación LDAP FALLIDA: usuario y/o clave no validas");
        } catch (NamingException ex) {
            throw new NamingException("Error al intentar crear un contexto: " + ex.getMessage());
        }
        return ctx;
    }

    @SuppressWarnings("unchecked")
    public Attributes authenticateUser(String username, String password, String strDomain, String strHost, String dn)
            throws Exception {
        Attributes attrs = null;
        String searchFilter = "(&(objectClass=user)(" + ATTRIBUTE_FOR_USER + "=" + username + "))";
        String searchBase = dn;
        try {
            LdapContext ctxGC = this.getLdapContext(username, password, strDomain, strHost);
            if (ctxGC != null) {
                // Create the search controls
                SearchControls searchCtls = new SearchControls();
                if (dn.equals("")) {
                    searchCtls.setSearchScope(SearchControls.OBJECT_SCOPE);
                } else {
                    searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
                }
//                String[] attrIDs = {"distinguishedName", "sn", "givenname", "mail", "telephonenumber"};
//                searchCtls.setReturningAttributes(attrIDs);
                // Search for objects in the ctx using the filter
                NamingEnumeration answer = ctxGC.search(searchBase, searchFilter, searchCtls);
                while (answer.hasMoreElements()) {
                    SearchResult sr = (SearchResult) answer.next();
                    attrs = sr.getAttributes();
                    if (attrs != null) {
                        return attrs;
                    }
                }
            }
        } catch (CommunicationException ex) {
            throw new Exception(ex);
        } catch (AuthenticationNotSupportedException ex) {
            throw new Exception(ex);
        } catch (AuthenticationException ex) {
            throw new Exception(ex);
        } catch (NamingException ex) {
            throw new Exception(ex);
        }
        return attrs;
    }

    public boolean authenticateLDAPUser(String strUser, String strPass, String strDomain, String strHost,String dn) throws Exception {
        try {
            Attributes attrs = authenticateUser(strUser, strPass, strDomain, strHost, dn);
            if (attrs != null) {
//                System.out.println(attrs.get("distinguishedName"));
//                System.out.println(attrs.get("givenname"));
//                System.out.println(attrs.get("sn"));
//                System.out.println(attrs.get("department"));
//                System.out.println(attrs.get("mail"));
//                System.out.println(attrs.get("telephonenumber"));
//                System.out.println(attrs.get("physicalDeliveryOfficeName"));
                return true;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return false;
    }

}
