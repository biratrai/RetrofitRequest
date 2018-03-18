package com.gooner10.retrofitsample.util;

import com.gooner10.retrofitsample.model.Address;
import com.gooner10.retrofitsample.model.Posts;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Utility class
 */
public class Util {
    public static String getAddress(Address address) {
        StringBuilder sb = new StringBuilder();
        sb.append("City: ");
        sb.append(address.getCity());
        sb.append("\n");
        sb.append("Street: ");
        sb.append(address.getStreet());
        sb.append("\n");
        sb.append("Suite: ");
        sb.append(address.getSuite());
        sb.append("\n");
        sb.append("Zip-Code: ");
        sb.append(address.getZipcode());
        return sb.toString();
    }

    public static List<Posts> sortAscending(List<Posts> postsList) {
        Collections.sort(postsList, new Comparator<Posts>() {
            public int compare(Posts u1, Posts u2) {
                return u1.getTitle().compareTo(u2.getTitle());
            }
        });
        return postsList;
    }

    public static List<Posts> sortDescending(List<Posts> postsList) {
        Collections.sort(postsList, new Comparator<Posts>() {
            public int compare(Posts u1, Posts u2) {
                return u2.getTitle().compareTo(u1.getTitle());
            }
        });
        return postsList;
    }
}
