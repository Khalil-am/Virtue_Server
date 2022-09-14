/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.PriorityQueue;

/**
 * @author salem
 */
public class Node {
    public String custName;
    public Node address;

    public Node(String cn, Node ad) {
        custName = cn;
        address = ad;
    }
}
