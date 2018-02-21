package com.imaginea.lru;

/**
 * This represents the double linked list
 * @author sudheerp
 */
public class DoublyLinkedList {

    /**This valus is total size of linked list*/
    private final int size;
    /**Current size of linked list**/
    private int currSize;

    private Node head;
    private Node tail;
    /**Initializes a doubly linked list with given size*/
    public DoublyLinkedList(int size) {
        this.size = size;
        currSize = 0;
    }

    /**
     * Get the tail value
     * @return Node
     */
    public Node getTail() {
        return tail;
    }

    /**
     * Print all the values
     */
    public void printList() {
        if(head == null) {
            return;
        }
        Node tmp = head;
        while(tmp != null) {
            System.out.print("pageNumber :"+tmp.getPageNumber());
            tmp = tmp.getNext();
        }
    }

    /**
     * Add the nodes into list
     * @param pageNumber
     * @return
     */
    public Node addPageToList(int pageNumber) {
        Node pageNode = new Node(pageNumber);
        if(head == null) {
            head = pageNode;
            tail = pageNode;
            currSize = 1;
            return pageNode;
        } else if(currSize < size) {
            currSize++;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        pageNode.setNext(head);
        head.setPrev(pageNode);
        head = pageNode;
        return pageNode;
    }

    /**
     * Move the head in list
     * @param pageNode
     */
    public void movePageToHead(Node pageNode) {
        if(pageNode == null || pageNode == head) {
            return;
        }

        if(pageNode == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
        }

        Node prev = pageNode.getPrev();
        Node next = pageNode.getNext();
        prev.setNext(next);

        if(next != null) {
            next.setPrev(prev);
        }

        pageNode.setPrev(null);
        pageNode.setNext(head);
        head.setPrev(pageNode);
        head = pageNode;
    }

    /**
     * Get the list current size
     * @return
     */
    public int getCurrSize() {
        return currSize;
    }

    public void setCurrSize(int currSize) {
        this.currSize = currSize;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }
}
