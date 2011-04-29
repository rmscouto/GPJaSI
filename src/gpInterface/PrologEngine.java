/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpInterface;

import gpInterface.results.PlBooleanResult;
import gpInterface.results.PlDataResult;
import gpInterface.results.PlExceptionResult;
import gpInterface.results.PlResult;
import gpInterface.threads.ProcessContainer;
import gpInterface.threads.ProcessThread;
import gpInterface.threads.ReaderThread;
import gpInterface.threads.WritterThread;

/**
 *
 * @author rmscouto
 */
public class PrologEngine {

    private final Object o;
    private ProcessBuilder pb;
    private Process p;
    private ProcessContainer pc;
    private Thread reader;
    private Thread writter;
    private Thread process;

    public PrologEngine() {
        o = new Object();
        pb = new ProcessBuilder("gprolog");
        p = null;
    }

    /**
     * Starts the engine.
     */
    public void start() {
        pc=new ProcessContainer(o);
        reader = new Thread(new ReaderThread(o,pc));
        reader.start();

        writter = new Thread(new WritterThread(o,pc));
        writter.start();

        process = new Thread(new ProcessThread(o,p,pb,pc));
        process.start();

        try {
            synchronized(o){
                while(!pc.isReady())
                    o.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendQuery(String q) throws Exception {
        //System.out.println("enviando query");
        pc.addRequest(q);
        synchronized(o) { o.notifyAll(); }
        //System.out.println("A esperar resultado");
        synchronized(o) { while(pc.getResSize()==0) o.wait(); }
        //System.out.println("resultado conseguido");
    }

    public PlResult getResult() {
        PlResult r = null;
        String str = pc.getResult();
        if(str.contains("="))
            r = new PlDataResult(str);
        else if(str.contains("yes"))
            r = new PlBooleanResult(str, true);
        else if(str.contains("no"))
            r = new PlBooleanResult(str, false);
        else if(str.contains("exception"))
            r = new PlExceptionResult(str);
        else
            r = new PlResult(str);
        return r;
    }

    public int getResultSize() {
        return pc.getResSize();
    }

    public void shutDown() {
        pc.getP().destroy();
    }
}
