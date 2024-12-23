public class Main {
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        Node head = new Node(3);                        // Sample values
        Node node2 = new Node(1);
        Node node3 = new Node(4);
        Node node4 = new Node(2);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = head;

        System.out.println("Original List:");
        list.printList(head);

        head = list.modifiedInsertionSort(head);

        System.out.println("Sorted List:");
        list.printList(head);
    }
}
