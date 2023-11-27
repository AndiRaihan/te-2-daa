import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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

    public static void main(String[] args) {
        SetCoverInput[] datasets = loadDataset();

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