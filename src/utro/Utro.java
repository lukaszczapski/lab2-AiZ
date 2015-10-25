
package utro;

import aiz.list.ListException;

/**
 *
 * @author Paweł "pewes" Dudzińśki
 * pewes696@gmail.com
 * 661-106-383
 */
public class Utro<T> {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ListException {
        
        ListOne<Integer> moja = new ListOne();
        
        moja.addFirst(5);
        moja.addFirst(4);
        moja.addFirst(3);
        moja.addLast(6);
        moja.addFirst(2);
        moja.addFirst(1);
        
        moja.addAtPosition(99,4);
        
        
        moja.print();
 ////////////////////////////////////////////////////////       
        moja.removeFirst();
////////////////////////////////////////////////////////        
        System.out.println("\n\n");
        moja.print();
////////////////////////////////////////////////////////        
        moja.removeLast();
        System.out.println("\n\n");
        moja.print();
////////////////////////////////////////////////////////
        int pozDoUsuniecia = 3;
        moja.remove(pozDoUsuniecia);
        
////////////////////////////////////////////////////////     
        System.out.println("\n\n");
        moja.print();
        System.out.println("\n\n");
        System.out.println("Liczba znajduje się na pozycji: "+moja.find(3)+" (jeśli -1 wtedy nie znaleziono)");
////////////////////////////////////////////////////////
        int find = 2;
        if(moja.contains(find)==true){
            System.out.println("LIczba "+find+" została znaleziona.");
        }else{
            System.out.println("LIczba "+find+" nie została znaleziona.");
        }
    }
    


    
}
