import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.System.gc;

public class Main {
    public static SetCoverInput parseFileIntoData(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int n = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            List<Set<Integer>> subsets = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                String[] line = reader.readLine().split(" ");
                Set<Integer> subset = new HashSet<>();
                for (String num : line) {
                    subset.add(Integer.parseInt(num));
                }
                subsets.add(subset);
            }
            String[] costLine = reader.readLine().split(" ");
            int[] costs = new int[costLine.length];
            for (int i = 0; i < costLine.length; i++) {
                costs[i] = Integer.parseInt(costLine[i]);
            }
            Set<Integer> universe = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                universe.add(i);
            }
            return new SetCoverInput(universe, subsets, costs);
        }
    }

    public static SetCoverInput[] loadDataset() {
        SetCoverInput[] datasets = new SetCoverInput[3];
        try {
            datasets[0] = parseFileIntoData("data/small.txt");
            datasets[1] = parseFileIntoData("data/medium.txt");
            datasets[2] = parseFileIntoData("data/large.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datasets;
    }

    public static void runTimeExperimentGreedy(SetCoverInput[] datasets, FileWriter writer) throws IOException {
        int i = 0;
        for (SetCoverInput dataset : datasets) {
            switch (i) {
                case 0:
                    writer.write("Running greedy algorithm on small dataset...\n");
                    System.out.println("Running greedy algorithm on small dataset...");
                    break;
                case 1:
                    writer.write("Running greedy algorithm on medium dataset...\n");
                    System.out.println("Running greedy algorithm on medium dataset...");
                    break;
                case 2:
                    writer.write("Running greedy algorithm on large dataset...\n");
                    System.out.println("Running greedy algorithm on large dataset...");
                    break;
            }
            long start = System.currentTimeMillis();
            ResultGreedy res = Greedy.greedy(dataset.universe, dataset.subsets, dataset.costs);
            long end = System.currentTimeMillis();
            writer.write("cost: " + res.cost + "\n");
            System.out.println("cost: " + res.cost);
            writer.write("Time taken for greedy algorithm on dataset of size " + dataset.universe.size() + ": " + (end - start) + "ms\n");
            System.out.println("Time taken for greedy algorithm on dataset of size " + dataset.universe.size() + ": " + (end - start) + "ms");
            i++;
        }
    }
    public static void runTimeExperimentBranchAndBound(SetCoverInput[] datasets, FileWriter writer) throws IOException {
        int i = 0;
        for (SetCoverInput dataset : datasets) {
            switch (i) {
                case 0:
                    writer.write("Running branch and bound algorithm on small dataset...\n");
                    System.out.println("Running branch and bound algorithm on small dataset...");
                    break;
                case 1:
                    writer.write("Running branch and bound algorithm on medium dataset...\n");
                    System.out.println("Running branch and bound algorithm on medium dataset...");
                    break;
                case 2:
                    writer.write("Running branch and bound algorithm on large dataset...\n");
                    System.out.println("Running branch and bound algorithm on large dataset...");
                    break;
            }
            long start = System.currentTimeMillis();
            Result res = BranchAndBound.BB(dataset.universe, dataset.subsets, dataset.costs);
            long end = System.currentTimeMillis();
            writer.write("cost: " + res.cost + "\n");
            System.out.println("cost: " + res.cost);
            writer.write("Time taken for branch and bound algorithm on dataset of size " + dataset.universe.size() + ": " + (end - start) + "ms\n");
            System.out.println("Time taken for branch and bound algorithm on dataset of size " + dataset.universe.size() + ": " + (end - start) + "ms");
            i++;
        }
    }
    public static void runMemoryExperimentGreedy(SetCoverInput[] datasets, FileWriter writer) throws IOException {
        int i = 0;
        for (SetCoverInput dataset : datasets) {
            switch (i) {
                case 0:
                    writer.write("Running greedy algorithm on small dataset...\n");
                    System.out.println("Running greedy algorithm on small dataset...");
                    break;
                case 1:
                    writer.write("Running greedy algorithm on medium dataset...\n");
                    System.out.println("Running greedy algorithm on medium dataset...");
                    break;
                case 2:
                    writer.write("Running greedy algorithm on large dataset...\n");
                    System.out.println("Running greedy algorithm on large dataset...");
                    break;
            }
            gc();
            gc();
            gc();
            long start = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            ResultGreedy res = Greedy.greedy(dataset.universe, dataset.subsets, dataset.costs);
            long end = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            writer.write("cost: " + res.cost + "\n");
            System.out.println("cost: " + res.cost);
            writer.write("Memory used for greedy algorithm on dataset of size " + dataset.universe.size() + ": " + (double) (end - start) / 1024 + "KB\n");
            System.out.println("Memory used for greedy algorithm on dataset of size " + dataset.universe.size() + ": " + (double) (end - start) / 1024 + "KB");
            i++;
        }
    }

    public static void runMemoryExperimentBranchAndBound(SetCoverInput[] datasets, FileWriter writer) throws IOException {
        int i = 0;
        for (SetCoverInput dataset : datasets) {
            switch (i) {
                case 0:
                    writer.write("Running branch and bound algorithm on small dataset...\n");
                    System.out.println("Running branch and bound algorithm on small dataset...");
                    break;
                case 1:
                    writer.write("Running branch and bound algorithm on medium dataset...\n");
                    System.out.println("Running branch and bound algorithm on medium dataset...");
                    break;
                case 2:
                    writer.write("Running branch and bound algorithm on large dataset...\n");
                    System.out.println("Running branch and bound algorithm on large dataset...");
                    break;
            }
            gc();
            gc();
            gc();
            long start = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            Result res = BranchAndBound.BB(dataset.universe, dataset.subsets, dataset.costs);
            long end = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            System.out.println("cost: " + res.cost);
            writer.write("cost: " + res.cost + "\n");
            System.out.println("Memory used for branch and bound algorithm on dataset of size " + dataset.universe.size() + ": " + (double) (end - start) / 1024 + "KB");
            writer.write("Memory used for branch and bound algorithm on dataset of size " + dataset.universe.size() + ": " + (double) (end - start) / 1024 + "KB\n");
            i++;
        }
    }

    public static void main(String[] args) {
        SetCoverInput[] datasets = loadDataset();
        for (int i = 0; i < 2; i++) {
            try (FileWriter writer = new FileWriter("res/output" + i + ".txt")) {
                runTimeExperimentGreedy(datasets, writer);
                runMemoryExperimentGreedy(datasets, writer);
                runTimeExperimentBranchAndBound(datasets, writer);
                runTimeExperimentBranchAndBound(datasets, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class SetCoverInput{
    Set<Integer> universe;
    List<Set<Integer>> subsets;
    int[] costs;

    public SetCoverInput(Set<Integer> universe, List<Set<Integer>> subsets, int[] costs) {
        this.universe = universe;
        this.subsets = subsets;
        this.costs = costs;
    }
}