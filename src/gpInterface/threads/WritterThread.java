/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpInterface.threads;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author rmscouto
 */
public class WritterThread implements Runnable {
    private Object o;
    private ProcessContainer pc;
    private Process p;

    /**
     * Reads commands from buffer, and sends to gprolog.
     * @param o Synchronized object.
     * @param pc ProcessContainer.
     */
    public WritterThread(Object o, ProcessContainer pc) {
        this.o=o;
        this.pc=pc;
    }

    /**
     * Starts the gprolog writter.<br/>
     * Use: new <i>Thread(new WritterThread).start();</i>,
     * DO NOT call this method.
     */
    public void run() {
        synchronized(o) {
            try { o.wait(); } catch (Exception e) { }
        }
        //System.out.println("(t) writter pronto!");
        p = pc.getP();
        PrintWriter pw = new PrintWriter(p.getOutputStream());
        synchronized(o) {
            try {o.notifyAll();pc.processReady();} catch (Exception e) { }
        }
        String req="";
        while(true) {
            try {
                while(pc.getReqSize()==0) {
                    //System.out.println("waiting for requests...");
                    synchronized(o) { o.wait(); }
                }
                req = pc.getRequest();
                pw.println(req);pw.flush();
                if(req.equals("halt.")) break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        /*
        Scanner sc = new Scanner(System.in);
        String cmd="";
        

        while(sc.hasNextLine()) {
            cmd = sc.nextLine();
            pw.println(cmd);pw.flush();
        }*/
    }


}
