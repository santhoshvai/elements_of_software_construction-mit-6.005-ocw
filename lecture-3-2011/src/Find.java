/*
 * EXAMPLE:
 * 
 * static int find (int [] a, int val)
 * requires: val occurs exactly once in a
 * effects: returns result such that a[result] = val 
 * 
 */

public class Find {
    /**
    * Find value in an array.
    * @param a array to search; requires that val occurs in a.
    * @param val value to search for
    * @return smallest index i such that a[i] = val or returns 
    * length of the array if no such i
    */
    public static int findA (int x, int[] a) {
        for (int i = 0; i < a.length; ++i) {
            if (a[i] == x) {
                return i;
            }
        }
        return a.length;
    }
    /**
    * Find value in an array.
    * @param a array to search; requires that val occurs in a.
    * @param val value to search for
    * @return largest index i such that a[i] = val or -1 if no such i
    */
    public static int findB (int x, int[] a) {
        for (int i = a.length-1; i >= 0; --i) {
            if (a[i] == x) {
                return i;
            }
        }
        return -1;
    }
    
}
