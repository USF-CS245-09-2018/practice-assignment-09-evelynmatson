public class BinaryHeap {

    // Fields
    int[] a;
    int size;

    // Constructor
    public BinaryHeap() {
        a = new int[10];
        size = 0;
    }

    // Instance Methods
    public void add(int n) {
        // Grow array if need be
        if (size == this.a.length) {
            this.grow_array();
        }

        // Initial placement
        a[size] = n;
        size++;

        // Bubble!
        for (int i = size-1; i > 0; i = (int) Math.ceil( ((double) i) / 2) -1 ) {
            if (! bubble(i)) {  // Try to bubble swap! If nothing was swapped, we can be done
                break;
            }
        }
    }

    public int remove() {
        if (size == 0) {
            throw new IllegalStateException("Cannot remove from BinaryHeap, nothing left in it.");
        }

        int toReturn = a[0];        // Grab the root (smallest element) save it to return it

        a[0] = a[size-1];   // Move last thing on the tree to the top. then shift around.
        size--;     // Size is now smaller

        shiftdown(0);         // Move down the tree, fixing the order if need be

        return toReturn;
    }

    // Private Instance Methods
    private void grow_array() {
        int[] new_array = new int[a.length*2];
        for (int i = 0; i<a.length; i++) {
            new_array[i] = a[i];
        }

        a = new_array;
    }

    private void shiftdown(int parent_index) {

        int child_index = (parent_index * 2) + 1;

        if (child_index >= size) {return;}      // "Base Case": if the child doesn't exist, we're done.

        if (a[child_index+1] < a[child_index]) {        // If the right child is actually smaller, focus on that one
            child_index++;
        }

        if (a[child_index] < a[parent_index]) {     // If the child is smaller than the parent, swap
            swap(a,child_index,parent_index);
            shiftdown(child_index);
            return;
        } else {    // IF no swapping is needed, we're done
            return;
        }
    }

    private void swap(int[] a, int item1, int item2) {
        int temp = a[item1];
        a[item1] = a[item2];
        a[item2] = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i<size; i++) {
            sb.append(a[i] + ", ");
        }
        sb.append("]");

        return sb.toString();
    }

    private boolean bubble(int child_index) {        // Bubble (index of child to possibly swap up), return true if bubbled
        int parent_index = (int) Math.ceil( ((double) child_index) / 2) -1;

        if (a[child_index] < a[parent_index]) {
            swap(a,child_index,parent_index);
            return true;
        } else {
            return false;
        }
    }

    // Main testing
    public static void main(String[] args) {
        BinaryHeap ferdinand = new BinaryHeap();

        ferdinand.add(5);
        ferdinand.add(10);
        ferdinand.add(1);
        ;
    }
}
