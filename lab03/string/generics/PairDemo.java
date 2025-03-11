class Pair<T, U> { //type
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}

class PairDemo {
    public static void main(String[] args) {
        Pair<Integer, Integer> minmax = new Pair<>(1, 100);
        Integer min = minmax.getFirst();
        Integer max = minmax.getSecond();
        System.out.println("Min: " + min + ", Max: " + max);

        Pair<String, Integer> resPair = new Pair<>("a",00);
        String fst = resPair.getFirst();
        Integer snd = resPair.getSecond();
        System.out.println("Fst: " + fst + ", Snd: " + snd);
    }
}