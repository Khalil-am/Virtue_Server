/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 */

import java.util.PriorityQueue;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        // TODO code application logic here


        //khalil
        PriorityQueue<String> check = new PriorityQueue<String>(10);


        int type = 0;
        LQueue q1 = new LQueue();
        LQueue q2 = new LQueue();

        do {
            System.out.println("**********************************");
            System.out.println("Main Menu");
            System.out.println("1) Transfer Money");
            System.out.println("2) Money Exchange");
            System.out.println("3) Exit");
            System.out.println("***********************************");
            System.out.print("Please choose Service Type: ");
            Scanner s1 = new Scanner(System.in);
            type = s1.nextInt();

            switch (type) {
                case 1:
                    System.out.println("<<<< Transfer Money Menu >>>>");
                    System.out.println("1) Add Customer");
                    System.out.println("2) Get Front");
                    System.out.println("3) Serve Customer");
                    System.out.println("4) Print Queue");
                    System.out.println("5) Clear Queue");
                    int no = s1.nextInt();
                    switch (no) {
                        case 1:
                            System.out.print("Enter customer name: ");
                            s1 = new Scanner(System.in);
                            String name = s1.nextLine();

                            //khalil-----------------------------------------------------------
                            if (check.contains(name)) {
                                System.out.println("customer already exists");
                                break;
                            }
                            q1.enqueue(name);
                            check.add(name);
                            //-----------------------------------------------------------------
                            System.out.println("Done: Customer Added");
                            break;
                        case 2:
                            System.out.println("Front: " + q1.getFront());
                            break;
                        case 3:
                            System.out.println("Customer name = " + q1.dequeue());
                            System.out.println("This customer is removed from queue");
                            break;
                        case 4:
                            q1.print();
                            break;
                        case 5:
                            q1.clear();
                            System.out.println("Queue is Empty");
                            break;
                        default:
                            System.out.println("Error: Invalid Service Type");

                    }
                    break;
                case 2:
                    System.out.println("<<<< Transfer Money Menu >>>>");
                    System.out.println("1) Add Customer");
                    System.out.println("2) Get Front");
                    System.out.println("3) Serve Customer");
                    System.out.println("4) Print Queue");
                    System.out.println("5) Clear Queue");
                    no = s1.nextInt();
                    switch (no) {
                        case 1:
                            System.out.print("Enter new customer name: ");
                            s1 = new Scanner(System.in);
                            String name = s1.nextLine();
                            //khalil-----------------------------------------------------------
                            if (check.contains(name)) {
                                System.out.println("customer already exists");
                                break;
                            }
                            q2.enqueue(name);
                            check.add(name);
                            //-----------------------------------------------------------------

                            System.out.println("Done: Customer Added");
                            break;
                        case 2:
                            System.out.println("Front: " + q2.getFront());
                            break;
                        case 3:
                            System.out.println("Customer name = " + q2.dequeue());
                            System.out.println("This customer is removed from queue");
                            break;
                        case 4:
                            q2.print();
                            break;
                        case 5:
                            q2.clear();
                            System.out.println("Queue is Empty");
                            break;
                        default:
                            System.out.println("Error: Invalid Service Type");
                    }
                    break;
                case 3:
                    System.out.println("Thank you Dr. Kholid for visiting");
                    System.out.println("I hope I get a full mark");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error: Invalid Service Type");
            }// switch
        } while (type != 3); // do
    }

}
