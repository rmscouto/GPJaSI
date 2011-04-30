/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpInterface.threads;

import java.util.Scanner;
import example.Main;

/**
 *
 * @author rmscouto
 */
public class ReaderThread implements Runnable {
    private Object o;
    private Process p;
    private ProcessContainer pc;

    /**
     * Reads the gprolog output.
     * @param o Synchronizes object.
     * @param pc Process container.
     */
    public ReaderThread(Object o, ProcessContainer pc) {
        this.o=o;
        this.pc = pc;
    }

    /**
     * Starts the gprolog reader.<br/>
     * Use: new <i>Thread(new ReaderThread).start();</i>,
     * DO NOT call this method.
     */
    public void run() {
        synchronized(o) {
            try { o.wait(); } catch (Exception e) {} //waiting for process to start
        }
        
        p = pc.getP();
        Scanner sc = new Scanner(p.getInputStream());
        String ln="";
        String rb="";
        //System.out.println("(t) reader pronto");
        synchronized(o) {
            try {  o.notifyAll();pc.processReady();} catch (Exception e) {} //waiting for process to start
        }
        
        ln = sc.nextLine();
        ln = sc.nextLine();
        ln = sc.nextLine();
        ln = sc.nextLine();
        while(sc.hasNextLine())  {
            ln = sc.nextLine();
            if(!ln.equals(""))
                rb=rb+"|"+ln;
            if(ln.contains("yes") || ln.contains("no") || ln.contains("exception")) {
                pc.addResult(rb);
                rb="";
            }
        }
    }

}
