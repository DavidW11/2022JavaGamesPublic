public class BitVector {

    // array that holds type boolean objects
    protected Boolean elementData[]; 
    // number of non-null elements in array
    protected int elementCount; 

    public BitVector() {
        this(10);
    }

    public BitVector(int initialCapacity) {
       elementData = new Boolean[initialCapacity];
       elementCount = 0;
    }

    public Boolean get(int index) {
        return elementData[index];
    }

    public Boolean set(int index, Boolean obj) {
        // set new object and return old object
        Boolean previous = elementData[index];
        elementData[index] = obj;
        return previous;
    }

    public void add(Boolean obj) {
        // check if array is full
        if (elementCount < elementData.length) {
            elementData[elementCount++] = obj;
        }
        // if array is full,
        // increase length by factor of 2
        else {
            // temporary array with twice previous length
            Boolean temp[] = new Boolean[elementData.length * 2];
            // copy all elements from previous array into temp array
            for (int i = 0; i<elementData.length; i++) {
                temp[i] = elementData[i];
            }
            // set old array equal to temp array
            elementData = temp;
        }
    }

    public void add(int index, Boolean obj) {
        if (index < elementCount) {

            int i;
            // shift all elements right of index 1 space to the right
            // copy from right to left to avoid destroying data
            for (i = elementCount; i > index; i--) {
                elementData[i] = elementData[i-1];
            }
            elementData[index] = obj;
            elementCount++;

            }
            else {
                System.out.println("Index Out of Bounds");
            }
    }

    public Boolean remove(int where) {
        // copy all elements right of index 1 space to the left
        Boolean result = get(where);
        if (where<elementCount) {
        
        
            while (where < elementCount) {
                elementData[where] = elementData[where+1];
                where++;
            }
            elementCount--;
            // set old last item to null
            // because it is now copied one space to the left
            elementData[elementCount] = null;
            return result;
            }
        else {
            System.out.println("Index Out of Bounds");
            return result;
        }
    }   

    public boolean isEmpty() {
        // returns true iff there are no elements in the vector
        return size() == 0;
    }

    public int size() {
        // post: returns the size of the vector
        return elementCount;
    }
}
    /*
    public String toString() {
        String result = "";
        for (Boolean b: elementData) {
            result += String.valueOf(b) + " ";
        }
        return result;
    }

    public static void main(String[] args) {
        BitVector test = new BitVector();
        test.add(1, false);
        test.add(2, false);
        test.add(true);
        test.remove(2);

        System.out.println(test);

    }
    */

    