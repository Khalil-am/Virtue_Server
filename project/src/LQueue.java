/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author salem
 */
public class LQueue {
    private Node firstNode;
    private Node lastNode;

    public LQueue() {
        firstNode = null;
        lastNode = null;
    }

    public void enqueue(String newEntry) {
        Node newNode = new Node(newEntry, null);
        if ((firstNode == null) && (lastNode == null)) //awwal car
        {
            firstNode = newNode;
            lastNode = newNode;
        } else {
            lastNode.address = newNode;
            lastNode = newNode;
        }

    }

    public String getFront() {
        String result = null;
        if (firstNode != null) {
            result = firstNode.custName;
        }
        return result;
    }

    public boolean isEmpty() {
        if ((firstNode == null) && (lastNode == null))
            return true;
        else
            return false;
    }

    public void clear() {
        firstNode = null;
        lastNode = null;
    }


    public String dequeue() {
        String result = null;
        if (firstNode != null) {
            if (firstNode != lastNode) {
                result = firstNode.custName;
                firstNode = firstNode.address;
            } else//last car
            {
                result = firstNode.custName;
                firstNode = null;
                lastNode = null;
            }

        }

        return result;
    }

    public void print() {
        int c = 0;
        Node temp = firstNode;
        System.out.println("Customers Name: ");
        System.out.println("----------------");
        while (temp != null) {
            c++;
            System.out.println(c + ")" + temp.custName);
            temp = temp.address;
        }
        System.out.println("----------------");
    }

}
