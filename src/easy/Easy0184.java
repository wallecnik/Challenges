package easy;

/**
 * @author Wallecnik
 * @version  26.10.14
 */
public class Easy0184{

    private ListItem stackTop;
    private ListItem orderedStart;
    private ListItem orderedEnd;
    private int size;

    public Easy0184() {
        this.stackTop = null;
        this.orderedStart = null;
        this.orderedEnd = null;
        size = 0;
    }

    public void push(int value) {
        this.size++;
        ListItem item = new ListItem(value);
        insertItem(item);
    }

    public ListItem pop() {
        ListItem item = this.stackTop;

        deleteItem(item);

        return item;
    }

    public int getSize() { return this.size; }

    public void removeGreater(int value) {
        // nothing to delete
        if (this.orderedStart == null) {
            return;
        }
        ListItem item = findPrevItem(value);
        //delete everything
        if (item == null) {
            this.stackTop = null;
            this.orderedStart = null;
            this.orderedEnd = null;
            size = 0;
            return;
        }

        if (item == this.orderedEnd) return; // last item
        item = item.getNextItem();

        while (item.hasNextItem()) {
            deleteItem(item);
            item = item.getNextItem();
        }
        deleteItem(item);

    }

    public void displayStack() {
        ListItem item = this.stackTop;
        System.out.println("Showing stack:");
        if (this.stackTop == null) return;

        if (this.stackTop.isStackBottom()) {
            System.out.println(item.getValue());
            return;
        }
        do {
            System.out.println(item.getValue());
            item = item.getStackUnder();
        } while (! item.isStackBottom());
        System.out.println(item.getValue());
    }

    public void displayOrdered() {
        ListItem item = this.orderedStart;
        System.out.println("Showing ordered:");
        if (this.stackTop == null) return;

        if (! this.orderedStart.hasNextItem()) {
            System.out.println(item.getValue());
            return;
        }

        do {
            System.out.println(item.getValue());
            item = item.getNextItem();
        } while (item.hasNextItem());
        System.out.println(item.getValue());
    }

    /**
     *
     * @param value
     * @return      previous item than the given value,
     *              null if is list empty or the given value has none previous
     */
    private ListItem findPrevItem(int value) {
        ListItem item = this.orderedStart;
        if (item == null) return null;

        try {
            while (item.getValue() < value) {
                item = item.getNextItem();
            }
        } catch (NullPointerException e) {
            return this.orderedEnd;
        }
        return item.getPrevItem();
    }

    /**
     * Handles whole deleting process, including rewriting
     * the indicators of stack top and ordered start and end
     *
     * @param item ListItem to be deleted
     */
    private void deleteItem (ListItem item) {
        //empty
        if (this.stackTop == null) {
            return;
        }

        //not empty
        //decrease size
        this.size--;

        // pop from stack
        if (item == this.stackTop) {
            this.stackTop = item.getStackUnder();
            item.getStackUnder().setStackOver(null);
        }
        else if (item.isStackBottom()) {
            item.getStackOver().setStackUnder(null);
        }
        else {
            item.getStackOver().setStackUnder(item.getStackUnder());
            item.getStackUnder().setStackOver(item.getStackOver());
        }

        // replace ordered
        // has previous and next, is not only or start or end
        if (item.hasNextItem() && item.hasPrevItem()) {
            item.getNextItem().setPrevItem(item.getPrevItem());
            item.getPrevItem().setNextItem(item.getNextItem());
        }
        // is only
        else if (this.orderedStart == this.orderedEnd) {
            this.orderedStart = null;
            this.orderedEnd = null;
        }
        // is first
        else if (item == this.orderedStart) {
            this.orderedStart = item.getNextItem();
            this.orderedStart.setPrevItem(null);
        }
        // is last
        else if (item == this.orderedEnd) {
            this.orderedEnd = item.getPrevItem();
            this.orderedEnd.setNextItem(null);
        }
    }

    /**
     * Handles whole inserting process, including rewriting
     * the indicators of stack top and ordered start and end
     *
     * @param item ListItem to be inserted
     */
    private void insertItem (ListItem item) {
        // push to stack
        item.setStackUnder(this.stackTop);
        if (this.stackTop != null) this.stackTop.setStackOver(item);
        this.stackTop = item;

        // include to ordered
        //empty list
        if (this.orderedStart == null) {
            this.orderedStart = item;
            this.orderedEnd = item;
            return;
        }
        //not empty list
        ListItem prevItem = findPrevItem(item.getValue());
        if (prevItem == null) { //value is to be first
            item.setNextItem(this.orderedStart);
            this.orderedStart.setPrevItem(item);
            this.orderedStart = item;
        }
        else if (prevItem == this.orderedEnd) { // value is to be last
            item.setPrevItem(this.orderedEnd);
            this.orderedEnd.setNextItem(item);
            this.orderedEnd = item;
        }
        else { // value is to be in the middle
            item.setPrevItem(prevItem);
            item.setNextItem(prevItem.getNextItem());
            prevItem.setNextItem(item);
            item.getNextItem().setPrevItem(item);
        }
    }

}

class ListItem {

    private ListItem stackUnder;
    private ListItem stackOver;
    private ListItem orderedNext;
    private ListItem orderedPrev;
    int value;

    public ListItem (int value) {
        this.value = value;
        this.orderedNext = null;
        this.orderedPrev = null;
        this.stackUnder = null;
    }

    public void setStackUnder(ListItem item) { this.stackUnder = item; }

    public void setStackOver(ListItem item) { this.stackOver = item; }

    public ListItem getStackUnder() { return this.stackUnder; }

    public ListItem getStackOver() { return this.stackOver; }

    public int getValue() { return this.value; }

    public ListItem getNextItem() { return this.orderedNext; }

    public ListItem getPrevItem() { return this.orderedPrev; }

    public void setNextItem(ListItem item) { this.orderedNext = item; }

    public void setPrevItem(ListItem item) { this.orderedPrev = item; }

    public boolean hasNextItem() {
        if (this.orderedNext != null) return true;
        return false;
    }

    public boolean hasPrevItem() {
        if (this.orderedPrev != null) return true;
        return false;
    }

    public boolean isStackBottom() {
        if (this.stackUnder == null) return true;
        return false;
    }

}
