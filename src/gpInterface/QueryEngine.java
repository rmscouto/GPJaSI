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

    /**
     * Query engine constructor. Prepares the environment.
     */
    public QueryEngine() {
        pe = new PrologEngine();
    }

    /**
     * Starts the gprolog environment.
     */
    public void start() {
        pe.start();
    }

    /**
     * Asserts a fact to the knowledge base.
     * @param fact Fact to assert.
     * @return True if success, false otherwise.
     */
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
        if(pr.getClass() == PlBooleanResult.class)
            r = ((PlBooleanResult)pr).getValue();
        if(pr.getClass() == PlExceptionResult.class)
            r = false;
        return r;
    }

    /**
     * Sends a question to the knowledge base.
     * @param fact The question.
     * @return String with the result.
     */
    public String quest(String fact) {
        String r = "";
        if(!fact.endsWith(".")) fact +=".";
        try {
            pe.sendQuery(fact);
        } catch (Exception e) {
            e.printStackTrace();
            r="error";
        }
        PlResult pr = pe.getResult();
        r = pr.toString();
        if(pr.getClass() == PlResult.class)
            r = pr.toString();
        if(pr.getClass() == PlExceptionResult.class)
            r = ((PlExceptionResult)pr).getValue();
        return r;
    }

    /**
     * Find all solutions to a question.
     * @param fact Term to search (e.g. father).
     * @param terms List of variables to query. Note that 'a' (fact) is different from 'A' (variable).
     * @return
     */
    public String findall(String fact,char[] terms) {
        String r = "";
        String q = "findall([";
        String p ="(";
        for(char c : terms) {
            q+=c+",";
            p+=c+",";
        }
        q = q.substring(0, q.length()-1);
        p = p.substring(0, p.length()-1);
        q+="],"+fact+p+"),L).";
        //System.out.println(q);
        try {
            pe.sendQuery(q);
        } catch (Exception e) {
            e.printStackTrace();
            r="error";
        }
        PlResult pr = pe.getResult();
        if(pr.getClass() == PlDataResult.class)
            r = pr.toString();
        if(pr.getClass() == PlResult.class)
            r = pr.toString();
        if(pr.getClass() == PlExceptionResult.class)
            r = ((PlExceptionResult)pr).getValue();
        return r;
    }

    /**
     * Consults (imports) a file.
     * @param filename Prolog file name.
     * @return True if succeed, false otherwise.
     */
    public boolean consult(String filename) {
        boolean r = false;
        if(filename.endsWith("."))
            filename=filename.substring(0,filename.length()-1);
        try {
            pe.sendQuery("consult('"+filename+"').");
            r = true;
        } catch (Exception e) {
            e.printStackTrace();
            r=false;
        }
        PlResult pr = pe.getResult();
        if(pr.getClass() == PlBooleanResult.class)
            r = ((PlBooleanResult)pr).getValue();
        if(pr.getClass() == PlExceptionResult.class)
            r = false;
        return r;
    }

    /**
     * Stops the environment.
     */
    public void stop() {
        pe.shutDown();
    }

}
