class CircularQueue {
    int[] queue;
    int front, rear, size, capacity;

    CircularQueue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = size = 0;
        rear = capacity - 1;
    }

    boolean isFull() {
        return size == capacity;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void enqueue(int item) {
        if (isFull()) {
            System.out.println("Queue is Full");
            return;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = item;
        size++;
        System.out.println(item + " enqueued");
    }

    void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return;
        }
        int item = queue[front];
        front = (front + 1) % capacity;
        size--;
        System.out.println(item + " dequeued");
    }

    void display() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return;
        }
        int i = front;
        for (int count = 0; count < size; count++) {
            System.out.print(queue[i] + " ");
            i = (i + 1) % capacity;
        }
        System.out.println();
    }

    void fronte() {
        // checking whether the queue is empty or not
        if (front == -1) {
            // if the queue is empty, exit the method
            System.out.println("Queue is empty.");
            return;
        } else {
            // if queue is not empty, then display frontEnd element
            System.out.println("Front Element is: " + queue[front]);
        }
    }

    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(7);
        q.enqueue(4);
        q.enqueue(12);
        q.enqueue(13);
        q.enqueue(2);
        q.enqueue(12);
        q.enqueue(10);
        q.dequeue();
        q.fronte();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.display();
    }
}