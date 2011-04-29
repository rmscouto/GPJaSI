/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpInterface.results;

/**
 *
 * @author rmscouto
 */
public class PlExceptionResult extends PlResult {
    
    private String msg;

    public PlExceptionResult(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getValue() {
        return msg;
    }
}
