/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpInterface.threads;

import java.util.ArrayList;

/**
 *
 * @author rmscouto
 */
public class ProcessContainer {
    private Process p;
    private ArrayList<String> resBuffer;
    private ArrayList<String> reqBuffer;
    private final Object sync;
    private int ptw;

    /**
     * Contains the process information, request/result buffer and synchronizes processes.
     * @param sync Synchronized object.
     */
    public ProcessContainer(Object sync) {
        this.sync = sync;
        reqBuffer = new ArrayList<String>();
        resBuffer = new ArrayList<String>();
        ptw = 3;
    }

    /**
     * Set the container process.
     * @param p gprolog process.
     */
    public void setP(Process p) {
        this.p = p;
    }

    /**
     * Returns the gprolog process contained.
     * @return
     */
    public Process getP() {
        return p;
    }

    /**
     * Add a prolog request to the buffer.
     * @param req Request.
     */
    public void addRequest(String req) {
        reqBuffer.add(req);
        synchronized(sync) {
            sync.notifyAll();
        }
    }

    /**
     * Add an output result to the result buffer.
     * @param res
     */
    public void addResult(String res) {
        resBuffer.add(res);
        synchronized(sync) {
            sync.notifyAll();
        }
    }

    /**
     * Gets a result from buffer and removes it.
     * @return
     */
    public String getResult() {
        String r = resBuffer.get(0);
        resBuffer.remove(0);
        return r;
    }

    /**
     * Gets a request from buffer and removes it.
     * @return
     */
    public String getRequest() {
        String r = reqBuffer.get(0);
        reqBuffer.remove(0);
        return r;
    }

    /**
     * Get the request buffer size.
     * @return
     */
    public int getReqSize() {
        return reqBuffer.size();
    }

    /**
     * Get the result buffer size.
     * @return
     */
    public int getResSize() {
        return resBuffer.size();
    }

    /**
     * Reduce the waiting queue. Call after a process is ready.
     */
    public synchronized void processReady() {
        ptw--;
    }

    /**
     * Tells if all process are ready.
     * @return True if all processes are ready, False otherwise.
     */
    public boolean isReady() {
        //System.out.println(">>"+ptw);
        if(ptw==0) return true;
        else return false;
    }

}
