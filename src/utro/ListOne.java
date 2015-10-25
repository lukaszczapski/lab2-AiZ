package utro;

import aiz.list.ElemOne;
import aiz.list.IList;

import aiz.list.ListException;

/**
 *
 * @author Paweł "pewes" Dudzińśki
 * pewes696@gmail.com
 * 661-106-383
 */
public class ListOne<T> implements IList<T> {

    ElemOne<T> first, last;
    ElemOne<T> nowa;
    int size = 0;

    public ListOne() {
        nowa = new ElemOne(null);
    }

    @Override
    public void addFirst(T newData) {
        if (first == null) {
            nowa = new ElemOne(newData);
            first = nowa;
            last = nowa;
        } else {
            first = new ElemOne(newData, first);

        }
        size++;
    }

    @Override
    public void addLast(T newData) {
        if (last == null) {
            nowa = new ElemOne(newData);
            first = nowa;
            last = nowa;
        } else {
            nowa = new ElemOne(newData);

            last.setNext(nowa);

            last = nowa;
        }
        size++;
    }

    @Override
    public void addAtPosition(T newData, int position) throws ListException {
        ElemOne<T> x = first;
        int coutPosition = 1;

        if (position > size + 1) {
            throw new ListException("Nie poprawby rozmiar");
        } else if (position == 1) {

            addFirst(newData);

        } else if (position == size + 1) {

            addLast(newData);

        } else {
            while (coutPosition != (position - 1)) {
                //x.getData();
                x = x.getNext();
                coutPosition++;
            }
            nowa = new ElemOne(newData, x.getNext());
            //nowa.setNext(x.getNext());
            x.setNext(nowa);

        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() throws ListException {

        if (size == 0) {
            throw new ListException("Brak elementów");
        } else {

            ElemOne<T> x = first;
            x = x.getNext();
            first.setData(x.getData());
            x = x.getNext();
            first.setNext(x);
        }
        size--;
        return null;

    }

    @Override
    public T removeLast() throws ListException {
        int counter = 1;

        if (size == 0) {
            throw new ListException("Brak elementów");
        } else {
            ElemOne<T> x = first;

            while (counter < (size)) {
                x = x.getNext();
                counter++;
            }

            last.setData(x.getData());
            x.setNext(null);

        }
        size--;
        return null;

    }

    @Override
    public T remove(int position) throws ListException {
        int counter = 1;
        ElemOne<T> x = first;
        ElemOne<T> z = first;
        if ((size == 0) || (position > size)) {
            throw new ListException("Brak elementów");
        } else if ((size == 1) || (position == 1)) {
            removeFirst();
            size--;
        } else {
            while (counter != position - 1) {
                x = x.getNext();
                counter++;
            }

            counter = 1;
            while (counter != position) {
                z = z.getNext();
                counter++;
            }
            x.setNext(z.getNext());
            size--;
        }

        return null;

    }

    @Override
    public int find(T dataToFind) {
        int spr = -1;
        int counter = 1;
        ElemOne<T> x = first;
        if (size != 0) {
            while (x != null) {
                if (dataToFind != x.getData()) {
                    x = x.getNext();
                    counter++;

                } else {
                    spr = counter;
                    break;
                }
            }
        }
        return spr;

    }

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
        ElemOne<T> x = first;

        while (x != null) {
            System.out.println(x.getData());
            x = x.getNext();
        }

    }

}
