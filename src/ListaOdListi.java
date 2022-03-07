import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ListaOdListi {

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
            return "<-" + element.toString() + "->";
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
            if (after == last) {
                insertLast(o);
                return;
            }
            DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
            after.succ.pred = ins;
            after.succ = ins;
        }

        public void insertBefore(E o, DLLNode<E> before) {
            if (before == first) {
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

        @Override
        public String toString() {
            String ret = new String();
            if (first != null) {
                DLLNode<E> tmp = first;
                ret += tmp + "<->";
                while (tmp.succ != null) {
                    tmp = tmp.succ;
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

    }

    public static double proizvod(DLL<DLL<Integer>> list){
        int zbir;
        double proizvod=1;
        DLLNode<DLL<Integer>> node = list.getFirst();
        DLLNode<Integer> node1 = list.getFirst().element.getFirst();
        while(node!=null){
            zbir = 0;
            while(node1!=null) {
                zbir = zbir + node1.element;
                node1 = node1.succ;
            }
            proizvod = proizvod * zbir;
            node=node.succ;
            try{
                node1=node.element.getFirst();
            } catch (Exception e) {
                continue;
            }
        }
        return proizvod;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(br.readLine());
        Integer m = Integer.parseInt(br.readLine());

//        Integer n = sc.nextInt();
//        Integer m = sc.nextInt();

        DLL<DLL<Integer>> list = new DLL<>();

//        for(int i=0;i<n;i++){
//            DLL<Integer> tmp = new DLL<>();
//            for(int j=0;j<m;j++){
//                tmp.insertLast(sc.nextInt());
//            }
//            list.insertLast(tmp);
//        }

        for(int i=0;i<n;i++){
            DLL<Integer> tmp = new DLL<>();
            String asd = br.readLine();
            String[] niza = asd.split(" ");
            for(int b=0;b<niza.length;b++){
                tmp.insertLast(Integer.parseInt(niza[b]));
            }
            list.insertLast(tmp);
        }

        System.out.println(String.format("%.0f", proizvod(list)));

    }

}
