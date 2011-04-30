/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package example;

import gpInterface.QueryEngine;

/**
 *
 * @author rmscouto
 */
public class Main {
    public static void main(String[] args) throws Exception {
        QueryEngine qe = new QueryEngine();
        qe.start();

        
        




        /*
        String r;

        
        if(qe.consult("p.pl")) {
            System.out.println("ficheiro ok");
        } else {
            System.out.println("erro ao ler");
        }

        r = qe.findall("father", new char[]{'A','B'});
        System.out.println(r);
        /**/



        /*
        qe.asserta("subclass(proxy, subject)");
        
        qe.asserta("subclass(b,a)");
        qe.asserta("subclass(f,c)");
        qe.asserta("subclass(e,d)");
        qe.asserta("subclass(concreteMediator, mediator)");
        qe.asserta("subclass(concreteColleague, colleague)");

        qe.asserta("calls(a,b)");
        qe.asserta("calls(b,c)");
        qe.asserta("calls(c,d)");
        qe.asserta("calls(e,f)");
        qe.asserta("calls(proxy, realsubject)");
        qe.asserta("calls(subject,proxy)");
        qe.asserta("calls(colleague,mediator)");
        qe.asserta("calls(concreteMediator,concreteColleague)");       

        qe.asserta("(proxyP(A,B,C) :- subclass(B,A) , calls(B,C), calls(A,B))");

        qe.asserta("(mediatorP(A,B,C,D) :- subclass(D,A) , subclass(C,B) , calls(A,B) , calls(C,D))");
        /**/

        /*
        r = qe.findall("proxyP", new char[]{'A','B','C'});
        System.out.println(r);


        String b = qe.quest("proxyP(a,b,c)");
        System.out.println(">"+b);

        b = qe.quest("proxyP(c,b,c)");
        System.out.println(">"+b);
        /**/
        //b = qe.quest("proxyP(c,b,a)");
        //System.out.println(">"+b);



//vproxy :- proxyP(subject,proxy,realsubject).
//vmediator :- mediatorP(colleague,mediator, concreteMediator, concreteColleague).

/*
        boolean b = qe.asserta("pai(a,b)");
        
        if(b)
            System.out.println("assert!");

        boolean k = qe.asserta("pai(A).");

        if(k)
            System.out.println("assert!");
        else
            System.out.println("Não assert!");

        
        String r = qe.findall("pai", new char[]{'a','b'});
        System.out.println(r);
        */

        /*
        PrologEngine pe = new PrologEngine();
        pe.start();

        pe.sendQuery("asserta(pai(a,b)).");
        System.out.println(pe.getResult());


        pe.shutDown();
        System.out.println("--ok");
        */


        /*
        Object o = new Object();
        
        ProcessBuilder pb = new ProcessBuilder("gprolog");
        Process p= null;
        ProcessContainer pc=new ProcessContainer(o);

        Thread t = new Thread(new ReaderThread(o,pc));
        t.start();
        
        t = new Thread(new WritterThread(o,pc));
        t.start();

        t = new Thread(new ProcessThread(o,p,pb,pc));
        t.start();


        try {
            synchronized(o){
                while(!pc.isReady())
                    o.wait();   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("---Todos as threads prontas! ---");

        sendQuery(o,pc,"asserta(pai(a,b)).");
        System.out.println(">> " + pc.getResult());

        sendQuery(o,pc,"findall([A,B],pai(A,B),L).");
        System.out.println(">> " + pc.getResult());

        sendQuery(o,pc,"cenas.");
        System.out.println(">> " + pc.getResult());

        sendQuery(o,pc,"pai(a,X).");
        System.out.println(">> " + pc.getResult());

        sendQuery(o,pc,"pai(A,B).");
        System.out.println(">> " + pc.getResult());

        sendQuery(o, pc, "halt.");

        System.out.println("---end---");
*/
        //System.out.println("resultados");
        //while(pc.getResSize()>0) {
        //System.out.println(">> " + pc.getResult());
        //}
        
/*
        System.out.println("a adicionar asserção");
        pc.addRequest("asserta(pai(a,b)).");
        synchronized(o) { o.notifyAll(); }
        System.out.println("A esperar resultado");
        synchronized(o) { while(pc.getResSize()==0) o.wait(); }
        
        System.out.println("Resultado conseguido:"+pc.getResult());
*/
         
/*
        //FileInputStream fis = new FileInputStream(new File("p.pl"));
        //Scanner scr = new Scanner(fis);
        Process p = pb.start();
        PrintWriter pw = new PrintWriter(p.getOutputStream());
        pw.println("consult('p.pl').");
        pw.flush();

        pw.println("asserta(pai(joao,manel)).");pw.flush();
        pw.println("asserta(pai(joao,tone)).");pw.flush();
        pw.println("asserta(irmao(A,B) :- pai(X,A), pai(X,B)).");pw.flush();


        pw.println("findall([A,B],irmao(A,B),L).");
        pw.flush();
*/
        /*
        while(scr.hasNextLine())  {
            pw.println(scr.nextLine());
            pw.flush();
        }

        p.getOutputStream().write(new byte[] { 0x04 });
        p.getOutputStream().flush();
        */
        //for (int i=0; i<256; i++) {
        //    pw.print("" + ((char)i) + ((char)i));
        //    pw.flush();
        //}


/*
        pw.println("findall([A,B],father(A,B),L).");
        pw.flush();

        pw.println("findall([A,B],father(A,B),L).");
        pw.flush();
        //pw.println((char)-1);
        //pw.flush();

        Scanner sc = new Scanner(p.getInputStream());


        while(sc.hasNextLine())  {
           System.out.println(sc.nextLine());
        }

        pw.println("halt.");
        pw.flush();
        p.destroy();

 */
    }
/*
    private static void sendQuery(Object o, ProcessContainer pc, String q) throws Exception {
        //System.out.println("enviando query");
        pc.addRequest(q);
        synchronized(o) { o.notifyAll(); }
        //System.out.println("A esperar resultado");
        synchronized(o) { while(pc.getResSize()==0) o.wait(); }
        //System.out.println("resultado conseguido");
    }
 */

}
