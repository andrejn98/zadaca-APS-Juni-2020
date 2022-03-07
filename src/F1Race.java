import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class F1Race {

    public static class DLL<E> {
        private DLLNode<E> first, last;

        public DLL() {
            // Construct an empty SLL
            this.first = null;
            this.last = null;
        }

        public void deleteList() {
            first = null;
            last = null;
        }

        public int length() {
            int ret;
            if (first != null) {
                DLLNode<E> tmp = first;
                ret = 1;
                while (tmp.succ != null) {
                    tmp = tmp.succ;
                    ret++;
                }
                return ret;
            } else
                return 0;

        }

        public DLLNode<E> find(E o) {
            if (first != null) {
                DLLNode<E> tmp = first;
                while (tmp.element != o && tmp.succ != null)
                    tmp = tmp.succ;
                if (tmp.element == o) {
                    return tmp;
                } else {
                    System.out.println("Elementot ne postoi vo listata");
                }
            } else {
                System.out.println("Listata e prazna");
            }
            return first;
        }

        public void insertFirst(E o) {
            DLLNode<E> ins = new DLLNode<E>(o, null, first);
            if (first == null)
                last = ins;
            else
                first.pred = ins;
            first = ins;
        }

        public void insertLast(E o) {
            if (first == null)
                insertFirst(o);
            else {
                DLLNode<E> ins = new DLLNode<E>(o, last, null);
                last.succ = ins;
                last = ins;
            }
        }

        public void insertAfter(E o, DLLNode<E> after) {
            if(after==last){
                insertLast(o);
                return;
            }
            DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
            after.succ.pred = ins;
            after.succ = ins;
        }

        public void insertBefore(E o, DLLNode<E> before) {
            if(before == first){
                insertFirst(o);
                return;
            }
            DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
            before.pred.succ = ins;
            before.pred = ins;
        }

        public E deleteFirst() {
            if (first != null) {
                DLLNode<E> tmp = first;
                first = first.succ;
                if (first != null) first.pred = null;
                if (first == null)
                    last = null;
                return tmp.element;
            } else
                return null;
        }

        public E deleteLast() {
            if (first != null) {
                if (first.succ == null)
                    return deleteFirst();
                else {
                    DLLNode<E> tmp = last;
                    last = last.pred;
                    last.succ = null;
                    return tmp.element;
                }
            }
            // else throw Exception
            return null;
        }

        public E delete(DLLNode<E> node) {
            if(node==first){
                deleteFirst();
                return node.element;
            }
            if(node==last){
                deleteLast();
                return node.element;
            }
            node.pred.succ = node.succ;
            node.succ.pred = node.pred;
            return node.element;

        }

        @Override
        public String toString() {
            String ret = new String();
            if (first != null) {
                DLLNode<E> tmp = first;
                ret += tmp + " -> ";
                while (tmp.succ != null) {
                    tmp = tmp.succ;
                    ret += tmp + " -> ";
                }
            } else
                ret = "Prazna lista!!!";
            return ret;
        }

        public String toStringR() {
            String ret = new String();
            if (last != null) {
                DLLNode<E> tmp = last;
                ret += tmp + "<->";
                while (tmp.pred != null) {
                    tmp = tmp.pred;
                    ret += tmp + "<->";
                }
            } else
                ret = "Prazna lista!!!";
            return ret;
        }

        public DLLNode<E> getFirst() {
            return first;
        }

        public DLLNode<E> getLast() {

            return last;
        }

        public void izvadiDupliIPrebroj(){

        }
    }

    public static class DLLNode<E> {
        protected E element;
        protected DLLNode<E> pred, succ;

        public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
            this.element = elem;
            this.pred = pred;
            this.succ = succ;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    public static class Vozaci{
        String ime;
        int gorivo;

        public Vozaci(String ime, int gorivo) {
            this.ime = ime;
            this.gorivo = gorivo;
        }

        public String getIme() {
            return ime;
        }

        public void setIme(String ime) {
            this.ime = ime;
        }

        public int getGorivo() {
            return gorivo;
        }

        public void setGorivo(int gorivo) {
            this.gorivo = gorivo;
        }

        @Override
        public String toString() {
            return ime + " " + "(" + gorivo + ")";
        }
    }

    public static void update(String ime, int gorivo, DLL<Vozaci> lista){
        DLLNode<Vozaci> node = lista.getFirst();
        boolean done = false;
        while(node!=null) {
            if(node.element.ime.equals(ime)) {
                if (node.element.gorivo < gorivo) {
                    lista.delete(node);
                }
            }
            node=node.succ;
        }

        node = lista.getFirst();

        while(node!=null && !done){

            if(node.element.ime.equals(ime)){
                int tmp = node.element.gorivo-gorivo;
                node.element.setGorivo(tmp);
                DLLNode<Vozaci> node1 = node;
                while(node!=null && gorivo!=0){
                    node = node.succ;
                    gorivo--;
                }

                if(node==null){
                    lista.insertLast(node1.element);
                    lista.delete(node1);
                    done=true;
                }else{
                    lista.insertAfter(node1.element, node);
                    lista.delete(node1);
                    done=true;
                }
            }
            if(node!=null)
                node = node.succ;

        }

        System.out.println(lista.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Vnesi broj na vozaci: ");
        int n = Integer.parseInt(br.readLine());
        System.out.println("Vnes na vozac / gorivo:");
        DLL<Vozaci> lista = new DLL<>();
        String vozac="";
        String[] tmp={};
        int gorivo;
        for(int i=0;i<n;i++) {
            vozac = br.readLine();
            tmp = vozac.split(" ");
            gorivo = Integer.parseInt(tmp[1]);
            Vozaci vozaci = new Vozaci(tmp[0], gorivo);
            lista.insertLast(vozaci);
        }

        System.out.println(lista.toString());
        System.out.println("Vnesi ime na vozac / gorivo:");

        vozac = br.readLine();
        while(!vozac.equals("kraj")){
            tmp = vozac.split(" ");
            gorivo = Integer.parseInt(tmp[1]);
            update(tmp[0], gorivo, lista);
            System.out.println("Vnesi ime na vozac / gorivo:");
            vozac = br.readLine();
        }

        System.out.println(lista.toString());
    }

}
