import java.util.*;

public class Greedy {
    public static ResultGreedy greedy(Set<Integer> universe, List<Set<Integer>> subsets, int[] costs) {
        int cost = 0;
        Set<Integer> elements = new HashSet<>();
        for (Set<Integer> subset : subsets) {
            elements.addAll(subset);
        }
        if (!elements.equals(universe)) {
            return null;
        }
        Set<Integer> covered = new HashSet<>();
        List<Set<Integer>> cover = new ArrayList<>();
        while (!covered.equals(elements)) {
            Set<Integer> subset = null;
            double maxVal = -1;
            for (Set<Integer> s : subsets) {
                Set<Integer> diff = new HashSet<>(s);
                diff.removeAll(covered);
                double val = (double) diff.size() / costs[subsets.indexOf(s)];
                if (val > maxVal) {
                    subset = s;
                    maxVal = val;
                }
            }
            cover.add(subset);
            cost += costs[subsets.indexOf(subset)];
            covered.addAll(subset);
        }
        return new ResultGreedy(cost, cover);
    }
    public static void runner(int a, List<Set<Integer>> b, int[] c) {
        long z = System.currentTimeMillis();
        int m = a;
        List<Set<Integer>> S = b;
        int[] C = c;
        Set<Integer> F = new HashSet<>();
        for (int i = 1; i <= m; i++) {
            F.add(i);
        }
        ResultGreedy X = greedy(F, S, C);
        int cost = X.cost;
        List<Set<Integer>> sets = X.subset;
        System.out.println("Covering sets: " + sets);
        System.out.println("Total cost: " + cost + "$");
        System.out.println("Time: " + (System.currentTimeMillis() - z) + "ms");
    }

    public static void main(String[] args) {
        // Call the setCover function with your parameters here
        int m1 = 5;
        int[][] S1 = {
                {1, 3}, {2}, {1, 2, 5}, {3, 5}, {4},
                {5}, {1, 3}, {2, 4, 5}, {1, 2}, {2, 3}
        };
        int[] P1 = {11, 4, 9, 12, 5, 4, 13, 12, 8, 9};

        int m2 = 15;
        int[][] S2 = {
                {2, 7, 8, 10, 12, 13}, {1, 3, 5, 8, 10, 11, 12, 15}, {1, 2, 3, 4, 5, 6, 7, 12, 13},
                {2, 6, 7, 11, 12, 13}, {9, 10, 12, 13}, {1, 3, 7, 9, 11, 12, 13},
                {1, 3, 5, 6, 8, 9, 10, 11, 12, 13}, {1, 3, 4, 5, 6, 7, 12, 14, 15},
                {1, 2, 3, 6, 11, 12}, {1, 2, 4, 5, 7, 8}, {5, 9, 10, 11, 15},
                {3, 5, 6, 7, 8, 9, 12, 13, 14}, {1, 3, 4, 5, 6, 7, 9, 11, 13, 14, 15},
                {1, 3, 5, 6, 8, 12, 14}, {2, 4, 7, 9, 10, 12, 14}, {1, 3, 5, 6, 11, 15},
                {2, 3, 4, 5, 6, 8, 10, 11, 12, 13, 14, 15}, {1, 2, 4, 6, 7, 11, 13, 14, 15},
                {1, 2, 8, 12, 13, 14}, {1, 2, 6, 7, 8, 13}, {1, 2, 3, 5, 7, 8, 10, 12, 14, 15},
                {4, 5, 7, 12, 15}, {1, 2, 3, 5, 11, 14}, {1, 6, 8, 11, 13}, {1, 6, 7, 8, 9, 10, 13},
                {1, 2, 3, 4, 5, 9, 11, 15}, {2, 3, 4, 7, 9, 11, 12}, {1, 3, 4, 5, 8, 10, 11, 12, 13},
                {2, 8, 9, 10}, {6, 11, 13}, {2, 5, 6, 8, 9, 11, 12, 13, 15},
                {2, 4, 6, 7, 8, 9, 10, 11, 13, 15}, {1, 2, 3, 4, 5, 7, 8, 10, 11},
                {1, 2, 6, 9, 11, 13, 14, 15}, {1, 4, 9, 10, 11, 13, 15},
                {1, 2, 3, 4, 6, 8, 12, 14, 15}, {4, 5, 7, 8, 10, 13, 14},
                {2, 4, 8, 9, 11, 14}, {2, 3, 4, 5, 6, 7, 10, 11, 14},
                {1, 2, 4, 5, 6, 7, 9, 11, 13, 14, 15}, {1, 2, 6, 7, 9, 10, 12, 15},
                {1, 3, 6, 9, 10, 15}, {2, 3, 5, 7, 8, 9, 11},
                {2, 3, 4, 5, 8, 10, 11, 12, 15}, {1, 3, 4, 5, 6, 7, 9, 10, 12, 15}
        };
        int[] P2 = {16, 7, 16, 39, 29, 35, 19, 27, 27, 33, 38, 8, 41, 16, 12, 7, 41, 6, 34, 48, 23, 16, 31, 18, 35, 31, 41, 21, 50, 21, 12, 37, 35, 44, 48, 18, 14, 26, 22, 13, 29, 34, 28, 45, 50};

        int m3 = 5;
        int[][] S3 = {
                {1}, {1, 2, 3, 4, 5}, {2, 3}, {2, 3, 4, 5}, {1, 3, 4}, {5}, {1, 2, 4}, {1, 3, 4, 5}, {3, 5},
                {4, 5}, {3}, {2, 5}, {4}, {1, 5}, {2}, {1, 2, 4}, {1, 3}, {1, 3, 5}, {2, 4, 5}, {2}, {1, 2, 5}
        };
        int[] P3 = {44, 44, 39, 24, 5, 30, 26, 42, 28, 12, 6, 45, 37, 33, 5, 42, 26, 6, 38, 11, 28};

        int m4 = 40;
        int[][] S4 = {
                {1, 3, 4, 6, 7, 9, 10, 15, 16, 18, 26, 31, 32, 35, 36, 38, 39, 40},
                {1, 2, 3, 4, 5, 7, 9, 11, 13, 15, 17, 18, 19, 20, 23, 24, 25, 27, 31, 32, 37, 39, 40},
                {4, 7, 8, 10, 11, 14, 16, 17, 18, 20, 23, 24, 27, 28, 29, 34, 36, 37, 39, 40},
                {2, 3, 4, 7, 9, 11, 17, 20, 22, 25, 26, 27, 28, 32, 34, 35, 36, 37, 39, 40},
                {1, 2, 4, 6, 7, 10, 12, 13, 22, 23, 24, 26, 28, 30, 32, 33, 35, 36, 39},
                {1, 3, 4, 5, 6, 8, 9, 10, 12, 13, 16, 24, 25, 30, 34, 35, 36, 37, 38, 39},
                {2, 3, 5, 10, 11, 12, 14, 18, 20, 22, 24, 25, 27, 28, 30, 31, 33, 34, 40},
                {1, 3, 11, 12, 18, 19, 21, 22, 24, 25, 26, 30, 33, 35},
                {1, 2, 7, 9, 10, 11, 14, 16, 18, 20, 22, 25, 28, 33, 35, 38},
                {3, 4, 9, 10, 14, 15, 17, 18, 19, 23, 24, 26, 28, 29, 30, 32, 34, 35, 38, 39, 40},
                {1, 2, 3, 4, 5, 6, 7, 9, 10, 13, 14, 15, 16, 19, 20, 22, 23, 29, 30, 31, 36, 38, 39},
                {2, 4, 5, 7, 13, 14, 15, 17, 20, 23, 24, 25, 27, 28, 29, 30, 34, 35},
                {1, 2, 4, 8, 9, 11, 14, 15, 16, 17, 18, 19, 20, 21, 23, 24, 26, 27, 31, 32, 33, 34, 35, 36, 37, 39, 40},
                {1, 4, 5, 6, 8, 10, 14, 17, 20, 21, 23, 24, 25, 29, 30, 40},
                {3, 5, 6, 10, 12, 14, 16, 17, 18, 19, 20, 22, 23, 24, 26, 28, 29, 30, 31, 32, 33, 34, 37, 39},
                {2, 3, 5, 6, 7, 9, 14, 15, 16, 17, 20, 21, 23, 27, 28, 29, 31, 32, 34, 35, 39, 40},
                {2, 5, 7, 10, 11, 13, 14, 18, 20, 22, 23, 29, 32, 33, 34, 35, 38, 39},
                {1, 3, 6, 7, 8, 9, 10, 12, 13, 24, 29, 30, 33, 34, 35, 36, 37, 39, 40},
                {1, 2, 5, 6, 8, 9, 10, 11, 12, 13, 14, 16, 20, 21, 26, 29, 30, 32, 33, 35, 36, 38, 39, 40},
                {3, 4, 7, 8, 11, 14, 16, 17, 19, 20, 21, 22, 23, 24, 25, 26, 29, 30, 31, 33, 34, 36, 38, 39},
                {2, 3, 4, 6, 7, 9, 11, 13, 14, 15, 16, 19, 21, 24, 25, 26, 27, 28, 29, 30, 31, 33, 36, 39},
                {1, 2, 3, 6, 8, 10, 11, 13, 15, 16, 17, 19, 20, 21, 22, 25, 26, 34, 35, 36, 39, 40},
                {1, 2, 7, 12, 15, 17, 21, 24, 25, 27, 28, 30, 35, 37, 39, 40}
        };
        int[] P4 = {59, 68, 56, 50, 75, 95, 71, 66, 30, 28, 42, 50, 68, 34, 29, 52, 70, 85, 27, 40, 76, 82, 38};
        int[][] tempS1 = Arrays.copyOf(S1, S1.length);
        List<Set<Integer>> newS1 = new ArrayList<>();
        for (int[] subset : S1) {
            Set<Integer> temp = new HashSet<>();
            for (int i : subset) {
                temp.add(i);
            }
            newS1.add(temp);
        }
        int[][] tempS2 = Arrays.copyOf(S2, S2.length);
        List<Set<Integer>> newS2 = new ArrayList<>();
        for (int[] subset : S2) {
            Set<Integer> temp = new HashSet<>();
            for (int i : subset) {
                temp.add(i);
            }
            newS2.add(temp);
        }
        int[][] tempS3 = Arrays.copyOf(S3, S3.length);
        List<Set<Integer>> newS3 = new ArrayList<>();
        for (int[] subset : S3) {
            Set<Integer> temp = new HashSet<>();
            for (int i : subset) {
                temp.add(i);
            }
            newS3.add(temp);
        }
        int[][] tempS4 = Arrays.copyOf(S4, S4.length);
        List<Set<Integer>> newS4 = new ArrayList<>();
        for (int[] subset : S4) {
            Set<Integer> temp = new HashSet<>();
            for (int i : subset) {
                temp.add(i);
            }
            newS4.add(temp);
        }
        runner(m1, newS1, P1);
        runner(m2, newS2, P2);
        runner(m3, newS3, P3);
        runner(m4, newS4, P4);
    }
}

class ResultGreedy {
    public int cost;
    public List<Set<Integer>> subset;

    public ResultGreedy(int cost, List<Set<Integer>> subset) {
        this.cost = cost;
        this.subset = subset;
    }
}