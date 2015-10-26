package utro;

import aiz.list.ElemOne;
import aiz.list.IList;

import aiz.list.ListException;

/**
 *
 * @author Paweł "pewes" Dudzińśki pewes696@gmail.com 661-106-383
 */
public class ListOne<T> implements IList<T> {

    ElemOne<T> first, last;  //deklaracja zmienny typu <T>
    ElemOne<T> nowa; // deklaracja "listy" typu <T>
    int size = 0; //rozmiar listy

    public ListOne() {//konstrukor
        nowa = new ElemOne(null);
    }

    @Override
    public void addFirst(T newData) {
        if (first == null) {//jesli nie ma rzdnych elementów w liście
            nowa = new ElemOne(newData);//tworzymy nowy element listy o wartości newData typu T
            first = nowa;//jesli jest to pierwszy element listy to jednocześnie jest też ostatnim
            last = nowa;
        } else {
            first = new ElemOne(newData, first);
            //do first wrzucamy nową wartość ze wskaźnikiem na stary first
            //po tej operacji nasz stary first będzie na "2" pozycji
        }
        size++;
    }

    @Override
    public void addLast(T newData) {
        if (last == null) {
            //to samo co w addFirst, jeśli lista jest pusta, ostani element będzie również pierszym
            nowa = new ElemOne(newData);
            first = nowa;
            last = nowa;
        } else {
            nowa = new ElemOne(newData);//tworzymy nowy element listy o wartości newData typu T

            last.setNext(nowa);//w last ustawiamy next(wskaźnik) na nowy element 

            last = nowa; //teraz możemy last-owi nadać nową wartoś "nowa"
        }
        size++;
    }
    //Teraz magia o.O
    //Załóżmy że chcemy dodać coś na 5 miejsce

    //Rysunek obrazujący co chemy zrobić 
    //http://1drv.ms/1O3Gm1j
    @Override
    public void addAtPosition(T newData, int position) throws ListException {
        /*
         Pracujemy na liście jednokierunkwej, więc chcąc coś wrzucić na jakieś miejsce
         musimy przewałować całą liste aby tam się dostać
         */
        ElemOne<T> x = first;  //pobieramy element first i przypisujemu go do x 
        int coutPosition = 1; // wewnętrzny licznik

        if (position > size + 1) {//jeśli pozycja jest poza zasięgiem listy zwracamy błąd
            throw new ListException("Nie poprawby rozmiar");
        } else if (position == 1) {
            //jeśli chcemy coś dodać na pozycję 1 to to samo co 
            //byśmy wywołali funkcję addFirst() z parametrem newData 

            addFirst(newData);

        } else if (position == size + 1) {
            //jeśli przekazana pozycja jest większa o 1 od rozmiaru listy
            //to jest równoważne z tym że element bedzię dodany na ostani element

            addLast(newData);

        } else {
            while (coutPosition != (position - 1)) {
                //w tej pętli przeskakujemy po elementach listy do tej pory
                //aż znajdziemy się na pozycji przed naszym miejscem docelowym
                // np. chcąc dodać coś na miejsce 5 musimy najpierw się udac na miejsce 4
                x = x.getNext();
                coutPosition++;
            }
            //jesteśmy na 4 pozycji i z tego miejsca się już nie ruszamy

            //tworzymy nowy element o wartości podanej przez param (newData) i wskaźniku (next) 
            //pobranego z 4 miejsca (x.getNext()) które wskazuje na 5 miejsce
            //po tej opracji nasz nowy element ma wartość przekazabą przez newData i wskaźnij na 5 miejsce
            nowa = new ElemOne(newData, x.getNext());

            //teraz nasza 4 pozycji musi wskazywać na naszą nową 5 wartość, a stara 5 musi stać się 6
            //więć zmieniamy wskażnik(next) 4 pozycji aby wskazywaała na nasz nowy 5 element 
            x.setNext(nowa);

        }
            //KONIEC. teraz nasza 4 pozycja wskazuje na nową 5 . Nowa 5 skazuje na starą 5 która jest teraz 6
        //itd.
    }

    @Override
    public int size() {//tego chyba nie musze omawiać
        return size;
    }

    @Override
    public T removeFirst() throws ListException {

        if (size == 0) {//oczywiście jeśli lista jest pusta to nie ma czego usuwać
            throw new ListException("Brak elementów");//wyrzucamy wyjątek
        } else {//jeśli jednak coś tam jest...

            ElemOne<T> x = first;//pobieramy sobie wartość i wskaźnik 1 elementu
            x = x.getNext();//przeskakujemy do 2 elementu
            first.setData(x.getData());//przypisujemy do first-a dane 2 elementu
            x = x.getNext();//przechodzimy do 3 elemntu
            first.setNext(x);//ustawiamy wskaźnik(next) first-a aby wskazywał na 3 element

            //w ten sposób nasz first ma teraz wartość 2 elementu i wskaźnik na 3 element
            //co daje nam usunięcie 1 elementu, 2 staje się 1, 3->2,itd.
        }
        size--;//oczywiście rozmiar listy zmiejsza się o 1
        return null;

    }

    @Override
    public T removeLast() throws ListException {
        int counter = 1;

        if (size == 0) {// jeśli lista jest pusta to nie ma czego usuwać
            throw new ListException("Brak elementów");//wyjątek
        } else {
            ElemOne<T> x = first;//pobieramy wartość i wskaźnik 1 elementu

            while (counter < size) {//lecimy na przedostani element
                x = x.getNext();
                counter++;
            }

            //teraz do last przypisujemy wartość przedostaniego elem i ustawiamy wskażnik na null
            //dlaczego null ???
            //bo to last, a ostani elem nie wskazuje na nic ,czyli koniec listy
            last.setData(x.getData());
            x.setNext(null);

        }
        size--;//zmniejszamy rozmair 
        return null;

    }

    //funcke remove zrobiłem na około bo moje początkowe założenie ni h*ja nie chciało diałać
    @Override
    public T remove(int position) throws ListException {
        int counter = 1;

        //elem first pobieram dwa razy z racji tego że potrzebne mi jest miejsce które mam usunąć
        //i miejsce poprzedzające miejsce do usunięcia
        ElemOne<T> x = first;
        ElemOne<T> z = first;
        if ((size == 0) || (position > size)) {//pusta lista lub pozycja poza zasięgiem powoduje wurzucenie wyjątku
            throw new ListException("Brak elementów");
        } else if ((size == 1) || (position == 1)) {//jeśli chcemy usunąć 1 pozycje to to samo co użyć fuckcji removeFirst()
            removeFirst();
            size--;
        } else {
            
            while (counter != position - 1) {//w tej pętli skaczemy do miejsca poprzedającego miejsce do usuniecia
                x = x.getNext();//przypisane do x
                counter++;
            }

            counter = 1;
            while (counter != position) {//w tej pętli skaczemy do miejsca do usunięcia
                z = z.getNext();
                counter++;
            }
            //teraz elementowi poprzedzającemu zmieniamy wskaźnij(next) ma wskaźnilk pobranego z elem do usunięcia
            //np. jeśli chcemy usunąć 5 pozycje to 4 pozycji przypisujemy wskaźnik z 5 pozycji który wskazuje
            //na 6 pozycje
            //po tym 4 pozycja będzie wskazywać na 6 pozycje 
            x.setNext(z.getNext());
            size--;
        }

        return null;

    }

    @Override
    public int find(T dataToFind) {
        int spr = -1;//domyślnie szukaba dana nie istnieje
        int counter = 1;
        ElemOne<T> x = first;//pobieramy dane i wskaźnij elem 1
        if (size != 0) {//jeśli lista jest pusta to znaczy też że szukanej wartości nie ma
            while (x != null) {//dopuki wskaźnik nie będzie wskazywał na null. Jedyny wskaźnik elementu który wskazuje na null to last, czyli koniec listy
                if (dataToFind != x.getData()) {//jesli szukanej nie odnaleziono
                    x = x.getNext();
                    counter++;

                } else {//jeśli znaleziono
                    spr = counter;//do wartości zwracanej przypisujemy pozycje szukanej
                    break;//powoduje wyjście z pętli while
                }
            }
        }
        return spr;//zwracamy pozycje szukanej, w przypadku nie znalezienia zwraca -1

    }
    
    //To samo co funcja find() tyle żę zwraca (true|false) dla szukanej  
    @Override
    public boolean contains(T data) {
        boolean spr = false;
        int counter = 1;
        ElemOne<T> x = first;
        if (size != 0) {
            while (x != null) {
                if (data != x.getData()) {
                    x = x.getNext();
                    counter++;

                } else {
                    spr = true;
                    break;
                }
            }
        }
        return spr;
    }

    @Override
    public void print() {
        ElemOne<T> x = first;//pobieramy dane i wartość 1 elem

        while (x != null) {//dopuki wskaźnik nie ma wartości null idz dalej (wskaźnik równy null ma ostani elem listy czylu last)
            System.out.println(x.getData());//wypisz wartość elementu x
            x = x.getNext();//przeskakujemy do następnego elementu
        }

    }

}
