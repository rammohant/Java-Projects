/*
Tara Ram Mohan
Lab 12: HashTableQuadraticProbing Class
 */
package cmsc256;

import java.util.Iterator;

public class HashTableQuadraticProbing<K, V> extends HashTableOpenAddressing<K, V> {

    public int quadraticProbe(int index, K keyIn) {
        boolean found = false;
        int i = 1;
        int indexIn = index;
        int removedStateIndex = -1; // Index of first removed location

        if(table[index] == null) // The hash index is available
            return index;

        while (!found && (table[index] != null) && i< table.length) {
            if (table[index].isIn()) {
                if (keyIn.equals(table[index].getKey()))
                    found = true; // Key found
                else // Follow probe sequence
                    index = (indexIn + (i*i++)) % table.length;         // linear probing
            }
            else { // Skip entries that were removed
                // Save index of first location in removed state
                if (removedStateIndex == -1)
                    removedStateIndex = index;
                index = (indexIn + (i*i++)) % table.length;         // linear probing
            }
        }
        if (found || (removedStateIndex == -1) )
            return index; // Index of either key or null
        else
            return removedStateIndex; // Index of an available location
    }

    @Override
    public V put(K keyIn, V valueIn) {
        V oldValue = null;
        Entry<K, V> entry = new Entry <K, V> (keyIn, valueIn);

        if (keyIn == null || valueIn == null) {
            throw new IllegalArgumentException();
        }

        int index = super.getHashIndex(keyIn);
        index = quadraticProbe (index, keyIn);

        if (table[index] == null ||table[index].equals("REMOVED")) {
            table[index] = entry;
            numEntries++;
        } else {
            oldValue = table[index].getValue();
            table[index] = entry;
        }
        if (isFull()) {
            enlargeHashTable();
        }

        return oldValue;    }

    @Override
    public V remove(K keyIn) {
        int indexInitial = super.getHashIndex(keyIn);
        int index = quadraticProbe (indexInitial, keyIn);
        V removedValue = null;

        if ( (index != -1) && contains(keyIn) && (table[index] != null) ) {
            removedValue = table[index].getValue();
            table[index].setToRemoved();
            numEntries--;
            return removedValue;
        } return null;
    }

    @Override
    public V getValue(K keyIn) {
        int index = super.getHashIndex(keyIn);
        index = quadraticProbe (index, keyIn);

        Entry <K, V> item = table[index];

        if ((item != null) && item.isIn())
            return item.getValue();
        else {
            return null;
        }
    }

    @Override
    public boolean contains(K keyIn) {
        return getValue(keyIn) != null;
    }

    public static void main(String[] args){
        HashTableQuadraticProbing<String, Integer> purchases =
                new HashTableQuadraticProbing<>();
        String[] names = {"Pax", "Eleven", "Angel", "Abigail", "Jack"};
        purchases.put(names[0], 654);
        purchases.put(names[1], 341);
        purchases.put(names[2], 70);
        purchases.put(names[3], 867);
        purchases.put(names[4], 5309);
        System.out.println("Contents with quadratic probing:\n" + purchases.toString());
        System.out.println("Replaced old value was " + purchases.put(names[1], 170));
        System.out.println("Contents after changing Eleven to 170:\n"
                + purchases.toString());
        System.out.println("Calling getValue() on Pax, Eleven, & Angel:");
        System.out.println("\tPax: " + purchases.getValue(names[0]));
        System.out.println("\tEleven: " + purchases.getValue(names[1]));
        System.out.println("\tAngel: " + purchases.getValue(names[2]));
        purchases.remove(names[0]);
        purchases.remove(names[2]);
        System.out.println("Contents after removing Pax & Angel:\n" + purchases);
        purchases.put("Gino", 348);
        System.out.println("Contents after adding Gino:\n" + purchases);
        Iterator<String> keyIter = purchases.getKeyIterator();
        Iterator<Integer> valueIter = purchases.getValueIterator();
        System.out.println("Contents of the hash table:");
        while(keyIter.hasNext())
            System.out.println("Key-" + keyIter.next()
                    + " : Value-" + valueIter.next());
    }
}
