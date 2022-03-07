import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class DLLJuniIspit {

    static class DLLNode<E> {
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

    static class DLL<E> {
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
                ret += tmp + " ";
                while (tmp.succ != null) {
                    tmp = tmp.succ;
                    ret += tmp + " ";
                }
            } else
                ret = "Prazna lista!!!";
            return ret;
        }

        public String toStringR() {
            String ret = new String();
            if (last != null) {
                DLLNode<E> tmp = last;
                ret += tmp + " ";
                while (tmp.pred != null) {
                    tmp = tmp.pred;
                    ret += tmp + " ";
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

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Integer n=Integer.parseInt(br.readLine());
        DLL<Integer> list = new DLL<>();
//        for(int i=0;i<n;i++){
//            list.insertLast(Integer.parseInt(br.readLine()));
//        }

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for(int i=0; i<N; i++){
            list.insertLast(scanner.nextInt());
        }

        DLLNode<Integer> first = list.getFirst();
        DLLNode<Integer> last = list.getLast();
        //ArrayList<Integer> lista = new ArrayList<>();
        //lista.sort();
        int lenght = list.length();
        int bigger=0, sum=0;
        for(int i=0;i<lenght/2;i++){
            if(first.element>last.element){
                bigger=first.element;
            }else if(first.element<last.element)
                bigger=last.element;

            if(first == last)
                break;

            sum = first.element + last.element;

            list.insertBefore(sum, first);

            list.insertAfter(bigger, first);

            list.delete(first);
            first = first.succ.succ;
            last = last.pred;
        }

        System.out.println(list.toString());
    }

}
