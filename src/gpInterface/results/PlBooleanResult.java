/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gpInterface.results;

/**
 *
 * @author rmscouto
 */
public class PlBooleanResult extends PlResult {

    private boolean bool;

    public PlBooleanResult(String res, boolean bool) {
        super(res);
        this.bool = bool;
    }

    public boolean getValue() {
        return bool;
    }

    @Override
    public String toString() {
        return bool? "true" : "false";
    }

    public String getResult() {
        return super.toString();
    }
    
}
