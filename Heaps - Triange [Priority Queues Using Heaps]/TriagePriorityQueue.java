import java.util.ArrayList;
import java.util.List;

// This implements the triage priority queue using a max heap
public class TriagePriorityQueue {
    private List<TriageScoreData> heap;

    public TriagePriorityQueue() {
        this.heap = new ArrayList<>();
    }

    // This is to get the parent index
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // To get left child index
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // To get right child index
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // To swap two elements in the heap
    private void swap(int i, int j) {
        TriageScoreData temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // To insert a new patient into the priority queue
    public void insert(TriageScoreData triageData) {
        heap.add(triageData);
        heapifyUp(heap.size() - 1);
    }

    // To maintain heap property after insertion
    private void heapifyUp(int i) {
        int parent = parent(i);
        if (i > 0 && heap.get(i).getTriageScore() > heap.get(parent).getTriageScore()) {
            swap(i, parent);
            heapifyUp(parent);
        }
    }

    // To check the next patient in line without removing them
    public TriageScoreData checkNextInLine() {
        if (!heap.isEmpty()) {
            return heap.get(0);
        }
        return null;
    }

    // To call and remove the next patient in line
    public TriageScoreData callNextInLine() {
        if (heap.isEmpty()) {
            return null;
        }
        if (heap.size() == 1) {
            return heap.remove(0);
        }
        
        TriageScoreData maxPriority = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        heapifyDown(0);
        return maxPriority;
    }

    // To maintain heap property after removal
    private void heapifyDown(int i) {
        int maxIndex = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < heap.size() && heap.get(left).getTriageScore() > heap.get(maxIndex).getTriageScore()) {
            maxIndex = left;
        }
        if (right < heap.size() && heap.get(right).getTriageScore() > heap.get(maxIndex).getTriageScore()) {
            maxIndex = right;
        }

        if (i != maxIndex) {
            swap(i, maxIndex);
            heapifyDown(maxIndex);
        }
    }

    // To change a patient's triage score
    public boolean changeTriageScore(String lastName, int newScore) {
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).getPatientLastName().equals(lastName)) {
                int oldScore = heap.get(i).getTriageScore();
                heap.get(i).setTriageScore(newScore);
                if (newScore > oldScore) {
                    heapifyUp(i);
                } else {
                    heapifyDown(i);
                }
                return true;
            }
        }
        return false;
    }

    // To display the current state of the triage queue
    public void displayTriage() {
        if (heap.isEmpty()){
            System.out.println("No queue.");
        }else{
            System.out.println("Triage Priority Queue (in Tree-Like Representation):");
            printHeap(0, 0);
            System.out.println("\nArray representation:");
            for (TriageScoreData patient : heap) {
                System.out.println(patient.getPatientFirstName() + " " + 
                                patient.getPatientLastName() + ": " + 
                                patient.getTriageScore());
            }
        }
    }

    // To print the heap in a tree-like structure
    private void printHeap(int index, int level) {
        if (index < heap.size()) {
            System.out.println("  ".repeat(level) + heap.get(index).getPatientLastName() + 
                               ": " + heap.get(index).getTriageScore());
            printHeap(rightChild(index), level + 1);                                            // right child
            printHeap(leftChild(index), level + 1);                                             // left child
        }
    }

   
}

