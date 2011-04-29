/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpInterface.threads;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author rmscouto
 */
public class ProcessThread implements Runnable{
    private ProcessBuilder pb;
    private Process p;
    private Object o;
    private ProcessContainer pc;

    /**
     * Inits the gprolog process.
     * @param o Sinchronized object.
     * @param p gprolog process.
     * @param pb Process builder.
     * @param pc process contanier.
     */
    public ProcessThread(Object o, Process p, ProcessBuilder pb,ProcessContainer pc) {
        this.o=o;
        this.pb = pb;
        this.p = p;
        this.pc = pc;
    }

    /**
     * Starts the gprolog process.<br/>
     * Use: new <i>Thread(new ProcessThread).start();</i>,
     * DO NOT call this method.
     */
    public void run() {
        try {
            p = pb.start();
            pc.setP(p);
            //System.out.println("(t) process started!");
            synchronized(o) {
                o.notifyAll();
                pc.processReady();
            }
        } catch (Exception e) {}
    }
}
