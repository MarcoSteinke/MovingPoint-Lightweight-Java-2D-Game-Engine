public class LinkedList {
    public Node head = null;

    public void insert(Node n){
        n.next = head;
        head = n;
    }

    public void dump(){
        Node current = head;

        System.out.println("Ausgabe der Liste");

        while(current != null){
            // ruft die toString()-Methode des gespeicherten generischen Objekts auf!!!
            System.out.print(current.element.toString() + ", ");
            current = current.next;
        }
    }
}