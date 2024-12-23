import java.util.Random;

public class DoublyLinkedList {
    Node head;
    Node tail;
    int size;
    
    public void append(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void sort(){
        Node current = head;                                               // Sorting in an ascending manner
        while (current != null) {
            Node index = current.next;
            while (index != null) {
                if (current.value > index.value) {
                    int temp = current.value;
                    current.value = index.value;
                    index.value = temp;
                }
                index = index.next;
            }
            current = current.next;
        }
    }

    public void unsort() {                                              //  This uses the Fisher-Yates shuffle algorithm explained in the AboutFisher-Yates.txt file 

        Random rand = new Random();
        Node current = tail;

        
        while (current != head) {
    
            int randomIndex = rand.nextInt(size);
            Node randomNode = getNodeAtIndex(randomIndex);

            int temp = current.value;                                   // Swap values of current and randomNode
            current.value = randomNode.value;
            randomNode.value = temp;

            current = current.prev;
            size--;
        }
    }

    private Node getNodeAtIndex(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}
