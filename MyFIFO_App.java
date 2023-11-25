package lab10;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyFIFO_App {
    // method stutter that accepts a queue of integers as a parameter and replaces
    // every element of the queue with two copies of that
    //    element
    // front [1, 2, 3] back
    // becomes
    // front [1, 1, 2, 2, 3, 3] back
    public static <E> void stutter(Queue<E> input) {
        Queue<E> queue =  new LinkedList<>();
        int size = input.size();
        for(int i =0;i < size ; i++){
            queue.add(input.peek());
            queue.add(input.poll());
        }
        for(int i =0; i < size*2 ; i++){
            input.add(queue.poll());
        }
    }
    // Method mirror that accepts a queue of strings as a
    // parameter and appends the
    // queue's contents to itself in reverse order
    // front [a, b, c] back


    // becomes
    // front [a, b, c, c, b, a] back
    public static <E> void mirror(Queue<E> input) {
        Queue<E> tempQueue = new LinkedList<>();
        Stack<E> tempStack = new Stack<>();
        int sizeInput = input.size();
        for(int i =0; i< sizeInput; i++){
            tempQueue.add(input.peek());
            tempStack.push(input.poll());
        }
        for(int i =0; i< sizeInput*2; i++){
            if(!tempQueue.isEmpty()){
                input.add(tempQueue.poll());
            }else {
                input.add(tempStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        stutter(queue);
        System.out.println(queue.toString());
    }
}
