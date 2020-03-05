/**
 * counter class.
 */
public class Counter {
    private int counters;

    /**
     * Counter - constructor.
     *
     * @param counter1 - counter.
     */
    public Counter(int counter1) {
        this.counters = counter1;
    }

    /**
     * add number to current count.
     *
     * @param number - the number we want to add to the counter.
     */
    public void increase(int number) {
        int counter = this.counters;
        counter = counter + number;
        this.counters = counter;
    }

    /**
     * subtract number from current count.
     *
     * @param number - the number we want to decrease from the counter.
     */
    public void decrease(int number) {
        int counter = this.counters;
        counter = counter - number;
        this.counters = counter;
    }

    /**
     * getter - get the current count.
     *
     * @return - the counter.
     */
    public int getValue() {
        return this.counters;
    }

    /**
     * setCounters - setter.
     *
     * @param counters1 the number.
     */
    public void setCounters(int counters1) {
        this.counters = counters1;
    }
}