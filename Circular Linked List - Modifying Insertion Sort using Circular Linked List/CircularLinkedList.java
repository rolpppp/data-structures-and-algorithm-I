public class CircularLinkedList {

    public Node modifiedInsertionSort(Node head) {
        if (head == null || head.next == head) {
            return head;
        }

        Node sortedTail = head;  
        Node current = head.next;  

        while (current != head) {
            Node nextNode = current.next;  

            if (current.value < head.value) {
                sortedTail.next = current.next;
                
                current.next = head;
                head = current; 

            } else {
                Node position = head;

                while (position.next != head && position.next.value < current.value) {
                    position = position.next;
                }

                sortedTail.next = current.next;  
                current.next = position.next;
                position.next = current;

                if (position == sortedTail) {
                    sortedTail = current;
                }
            }

            current = nextNode;
        }

        return head;
    }
    
    public void printList(Node head) {
        if (head == null) return;
        Node temp = head;
        do {
            System.out.print(temp.value + " ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    
}
