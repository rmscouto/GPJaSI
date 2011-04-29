package gpInterface;

import gpInterface.results.PlBooleanResult;
import gpInterface.results.PlDataResult;
import gpInterface.results.PlExceptionResult;
import gpInterface.results.PlResult;

/**
 * Query engine. Allows to add and query information to prolog engine.
 * @author rmscouto
 */
public class QueryEngine {
    private PrologEngine pe;
    
    public QueryEngine() {
        pe = new PrologEngine();
    }

    public void start() {
        pe.start();
    }

    public boolean asserta(String fact) {
        boolean r = false;
        if(fact.endsWith("."))
            fact=fact.substring(0,fact.length()-1);
        try {
            pe.sendQuery("asserta("+fact+").");
            //System.out.println("command: "+"asserta("+fact+").");
            r = true;
        } catch (Exception e) {
            e.printStackTrace();
            r=false;
        }
        PlResult pr = pe.getResult();
        //System.out.println("result: " + pr);
        //System.out.println("result class: " + pr.getClass());
        if(pr.getClass() == PlBooleanResult.class)
            r = ((PlBooleanResult)pr).getValue();
        if(pr.getClass() == PlExceptionResult.class)
            r = false;

        return r;
    }

    public String quest(String fact) {
        String r = "";
        if(!fact.endsWith(".")) fact +=".";
        try {
            pe.sendQuery(fact);
            //System.out.println("command: "+"asserta("+fact+").");
        } catch (Exception e) {
            e.printStackTrace();
            r="error";
        }
        PlResult pr = pe.getResult();
        //System.out.println("result: " + pr);
        //System.out.println("result class: " + pr.getClass());
        //if(pr.getClass() == PlBooleanResult.class)
        //    r = "no";
        r = pr.toString();
        if(pr.getClass() == PlResult.class)
            r = pr.toString();
        if(pr.getClass() == PlExceptionResult.class)
            r = ((PlExceptionResult)pr).getValue();

        return r;
    }

    public String findall(String fact,char[] terms) {
        String r = "";
        String q = "findall([";
        String p ="(";
        for(char c : terms) {

            q+=Character.toUpperCase(c)+",";
            p+=Character.toUpperCase(c)+",";
        }
        q = q.substring(0, q.length()-1);
        p = p.substring(0, p.length()-1);
        q+="],"+fact+p+"),L).";
        //System.out.println(q);
        try {
            pe.sendQuery(q);
            //System.out.println("command: "+"asserta("+fact+").");
        } catch (Exception e) {
            e.printStackTrace();
            r="error";
        }
        PlResult pr = pe.getResult();
        //System.out.println("result class: " + pr.getClass());
        if(pr.getClass() == PlDataResult.class)
            r = pr.toString();
        if(pr.getClass() == PlResult.class)
            r = pr.toString();
        if(pr.getClass() == PlExceptionResult.class)
            r = ((PlExceptionResult)pr).getValue();
        return r;
    }

    

    public void stop() {
        pe.shutDown();
    }

}
