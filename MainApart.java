import java.util.*;

// ---------------- SINGLY LINKED LIST ----------------

// ---------------- SINGLY LINKED LIST (NON-GENERIC) ----------------

class SinglyLinkedList implements Iterable {

    private Node head;
    private int size;

    private static class Node {

        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
        }
    }

    public void add(Object data) {

        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {

            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator iterator() {

        return new Iterator() {

            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Object next() {

                if (!hasNext())
                    throw new NoSuchElementException();

                Object data = current.data;
                current = current.next;

                return data;
            }
        };
    }
}

// ---------------- STACK (NON-GENERIC) ----------------

class CustomStack {
    private Node top;
    private int size;

    private static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
        }
    }

    public void push(Object data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public Object pop() {
        if (isEmpty())
            throw new EmptyStackException();
        Object data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public Object peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}

// ---------------- QUEUE (NON-GENERIC) ----------------

class CustomQueue {
    private Node head, tail;
    private int size;

    private static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
        }
    }

    public void enqueue(Object data) {
        Node newNode = new Node(data);
        if (tail != null)
            tail.next = newNode;
        tail = newNode;
        if (head == null)
            head = newNode;
        size++;
    }

    public Object dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        Object data = head.data;
        head = head.next;
        if (head == null)
            tail = null;
        size--;
        return data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
}

// ---------------- PRIORITY QUEUE ----------------

// ---------------- PRIORITY QUEUE (NON-GENERIC) ----------------

class PriorityQueue {

    private ArrayList heap;

    public PriorityQueue() {
        heap = new ArrayList();
    }

    public void enqueue(Comparable item) {

        heap.add(item);
        siftUp(heap.size() - 1);
    }

    public Object dequeue() {

        if (isEmpty())
            throw new NoSuchElementException();

        Object root = heap.get(0);

        Object lastItem = heap.remove(heap.size() - 1);

        if (!isEmpty()) {

            heap.set(0, lastItem);
            siftDown(0);
        }

        return root;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void siftUp(int index) {

        while (index > 0) {

            int parentIndex = (index - 1) / 2;

            Comparable child = (Comparable) heap.get(index);
            Comparable parent = (Comparable) heap.get(parentIndex);

            if (child.compareTo(parent) >= 0)
                break;

            swap(index, parentIndex);

            index = parentIndex;
        }
    }

    private void siftDown(int index) {

        int size = heap.size();

        while (true) {

            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;

            int smallest = index;

            if (leftChild < size &&
                    ((Comparable) heap.get(leftChild)).compareTo(heap.get(smallest)) < 0)
                smallest = leftChild;

            if (rightChild < size &&
                    ((Comparable) heap.get(rightChild)).compareTo(heap.get(smallest)) < 0)
                smallest = rightChild;

            if (smallest == index)
                break;

            swap(index, smallest);

            index = smallest;
        }
    }

    private void swap(int i, int j) {

        Object temp = heap.get(i);

        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}

// ---------------- HASH TABLE ----------------

class HashTable {

    private static final int INITIAL_CAPACITY = 16;

    private LinkedList[] table;

    private int size;

    private static class Entry {

        Object key;
        Object value;

        Entry(Object key, Object value) {

            this.key = key;
            this.value = value;
        }
    }

    public HashTable() {

        table = new LinkedList[INITIAL_CAPACITY];

        for (int i = 0; i < INITIAL_CAPACITY; i++)
            table[i] = new LinkedList();
    }

    public void put(Object key, Object value) {

        int index = getIndex(key);

        for (Object obj : table[index]) {
            Entry entry = (Entry) obj;
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        table[index].add(new Entry(key, value));

        size++;
    }

    public Object get(Object key) {

        int index = getIndex(key);

        for (Object obj : table[index]) {
            Entry entry = (Entry) obj;
            if (entry.key.equals(key))
                return entry.value;
        }

        return null;
    }

    private int getIndex(Object key) {

        return Math.abs(key.hashCode() % table.length);
    }

    public int size() {
        return size;
    }
}

// ---------------- SORTING ----------------

class SortAlgorithms {

    public static void quickSort(List list, int low, int high) {

        if (low < high) {

            int pi = partition(list, low, high);

            quickSort(list, low, pi - 1);

            quickSort(list, pi + 1, high);
        }
    }

    private static int partition(List list, int low, int high) {

        Comparable pivot = (Comparable) list.get(high);

        int i = (low - 1);

        for (int j = low; j < high; j++) {

            if (((Comparable) list.get(j)).compareTo(pivot) < 0) {

                i++;

                Object temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        Object temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

    // Binary Search Implementation
    public static int binarySearch(List list, Comparable key) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = ((Comparable) list.get(mid)).compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // Key found
        }
        return -1; // Key not found
    }
}

// ---------------- COMPLAINT MODEL ----------------

class Complaint implements Comparable {

    private static int idCounter = 1000;

    private int complaintID;
    private String flatNumber;
    private String description;
    private String category;
    private int urgency;

    private String status;

    public Complaint(String flatNumber, String description, String category, int urgency) {

        this.complaintID = idCounter++;
        this.flatNumber = flatNumber;
        this.description = description;
        this.category = category;
        this.urgency = urgency;
        this.status = "OPEN";
    }

    // Dummy constructor for searching by ID
    public Complaint(int id) {
        this.complaintID = id;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getUrgency() {
        return urgency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Object obj) {
        Complaint other = (Complaint) obj;
        // Compare by urgency (1=High, 3=Low). So 1 is "smaller" (higher priority)
        return Integer.compare(this.urgency, other.urgency);
    }

    // Helper for sorting/searching by ID
    public static class IDComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Complaint c1 = (Complaint) o1;
            Complaint c2 = (Complaint) o2;
            return Integer.compare(c1.getComplaintID(), c2.getComplaintID());
        }
    }

    @Override
    public String toString() {

        return "[" + complaintID + "] Flat: " + flatNumber +
                " | Cat: " + category +
                " | Urgency: " + urgency +
                " | Status: " + status +
                "\nDesc: " + description;
    }
}

// ---------------- SERVICE ----------------

class ComplaintService {

    private SinglyLinkedList masterList;
    private PriorityQueue priorityQueue;
    private CustomQueue fifoQueue;
    private CustomStack resolutionHistory;
    private HashTable flatLookup;

    public ComplaintService() {
        masterList = new SinglyLinkedList();
        priorityQueue = new PriorityQueue();
        fifoQueue = new CustomQueue();
        resolutionHistory = new CustomStack();
        flatLookup = new HashTable();
    }

    public void registerComplaint(String flat, String desc, String cat, int urgency) {
        Complaint c = new Complaint(flat, desc, cat, urgency);
        masterList.add(c);
        priorityQueue.enqueue(c);
        fifoQueue.enqueue(c);

        List list = (List) flatLookup.get(flat);
        if (list == null) {
            list = new ArrayList();
            flatLookup.put(flat, list);
        }
        list.add(c);

        System.out.println("\nComplaint Registered Successfully! ID: " + c.getComplaintID());
    }

    public Complaint resolveByPriority() {
        if (priorityQueue.isEmpty())
            return null;
        Complaint c = (Complaint) priorityQueue.dequeue();
        markAsResolved(c);
        return c;
    }

    public Complaint resolveByFIFO() {
        if (fifoQueue.isEmpty())
            return null;
        Complaint c = (Complaint) fifoQueue.dequeue();
        markAsResolved(c);
        return c;
    }

    private void markAsResolved(Complaint c) {
        c.setStatus("RESOLVED");
        resolutionHistory.push(c);
    }

    public CustomStack getResolutionHistory() {
        return resolutionHistory;
    }

    public List searchByFlat(String flat) {
        return (List) flatLookup.get(flat);
    }

    public Complaint searchByID(int id) {
        List all = getAllSortedByID();
        int index = SortAlgorithms.binarySearch(all, new Complaint(id));
        if (index != -1)
            return (Complaint) all.get(index);
        return null;
    }

    public List getAllSortedByID() {
        List all = new ArrayList();
        for (Object obj : masterList) {
            all.add(obj);
        }
        if (!all.isEmpty()) {
            // Sort by ID for binary search
            Collections.sort(all, new Complaint.IDComparator());
        }
        return all;
    }
}

// ---------------- MAIN ----------------

public class MainApart {

    private static ComplaintService service = new ComplaintService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== APARTMENT COMPLAINT SYSTEM (DSA ENHANCED) =====");
            System.out.println("1. Register Complaint");
            System.out.println("2. Search by Flat Number (Hashing)");
            System.out.println("3. Search by ID (Binary Search)");
            System.out.println("4. Resolve by Priority (Priority Queue)");
            System.out.println("5. Resolve by Arrival (FIFO Queue)");
            System.out.println("6. View All Complaints (Sorted)");
            System.out.println("7. View Resolution History (Stack)");
            System.out.println("8. Exit");
            System.out.print("Choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        register();
                        break;
                    case 2:
                        searchByFlat();
                        break;
                    case 3:
                        searchByID();
                        break;
                    case 4:
                        resolve(true);
                        break;
                    case 5:
                        resolve(false);
                        break;
                    case 6:
                        viewAll();
                        break;
                    case 7:
                        viewHistory();
                        break;
                    case 8:
                        System.out.println("Exiting System. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid Choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void register() {
        System.out.print("Flat Number: ");
        String flat = scanner.nextLine();
        System.out.print("Category (Plumbing/Electrical/Sanitation/Lift/Security): ");
        String cat = scanner.nextLine();
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Urgency (1:High, 2:Medium, 3:Low): ");
        int urg = scanner.nextInt();
        service.registerComplaint(flat, desc, cat, urg);
    }

    private static void searchByFlat() {
        System.out.print("Enter Flat Number: ");
        String flat = scanner.nextLine();
        List results = service.searchByFlat(flat);
        displayList(results, "No complaints found for this flat.");
    }

    private static void searchByID() {
        System.out.print("Enter Complaint ID: ");
        int id = scanner.nextInt();
        Complaint c = service.searchByID(id);
        if (c != null)
            System.out.println("\nFound:\n" + c);
        else
            System.out.println("Complaint ID not found.");
    }

    private static void resolve(boolean usePriority) {
        Complaint c = usePriority ? service.resolveByPriority() : service.resolveByFIFO();
        if (c == null) {
            System.out.println("No pending complaints.");
        } else {
            System.out.println("\nResolved (" + (usePriority ? "Priority" : "FIFO") + "):");
            System.out.println(c);
            System.out.println("Status updated to RESOLVED and added to history.");
        }
    }

    private static void viewAll() {
        List all = service.getAllSortedByID();
        displayList(all, "No complaints registered yet.");
    }

    private static void viewHistory() {
        CustomStack history = service.getResolutionHistory();
        if (history.isEmpty()) {
            System.out.println("No resolved complaints in history.");
        } else {
            System.out.println("\n--- RESOLUTION HISTORY (LAST RESOLVED FIRST) ---");
            // Since we can't iterate a stack easily without popping,
            // and we want to keep history, let's use a temporary stack.
            CustomStack temp = new CustomStack();
            while (!history.isEmpty()) {
                Complaint c = (Complaint) history.pop();
                System.out.println(c);
                temp.push(c);
            }
            // Put them back
            while (!temp.isEmpty()) {
                history.push(temp.pop());
            }
        }
    }

    private static void displayList(List list, String emptyMsg) {
        if (list == null || list.isEmpty()) {
            System.out.println(emptyMsg);
        } else {
            for (Object obj : list)
                System.out.println((Complaint) obj);
        }
    }
}
