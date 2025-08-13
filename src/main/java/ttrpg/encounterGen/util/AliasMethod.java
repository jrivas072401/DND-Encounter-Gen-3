package ttrpg.encounterGen.util;

import java.util.List;
import java.util.Random;

public class AliasMethod {
    private AliasTable lootTable;
    private Random random;

    public AliasMethod(List<Double> weights) {
        double[] probabilities = convertWeightsToProbabilities(weights);
        lootTable = new AliasTable(probabilities.length);
        random = new Random();
        initialize(probabilities);
    }

    private double[] convertWeightsToProbabilities(List<Double> weights) {
        double total = weights.stream().mapToDouble(Double::doubleValue).sum();
        double[] probs = new double[weights.size()];
        for (int i = 0; i < weights.size(); i++) {
            probs[i] = weights.get(i) / total;
        }
        return probs;
    }

    private void initialize(double[] probabilities) {
        int size = probabilities.length;
        double[] scaled = new double[size];
        int[] small = new int[size];
        int[] large = new int[size];
        int smallIndex = 0, largeIndex = 0;

        for (int i = 0; i < size; i++) scaled[i] = probabilities[i] * size;

        for (int i = 0; i < size; i++) {
            if (scaled[i] < 1.0) small[smallIndex++] = i;
            else large[largeIndex++] = i;
        }

        while (smallIndex > 0 && largeIndex > 0) {
            int less = small[--smallIndex];
            int more = large[--largeIndex];

            lootTable.getProbability()[less] = scaled[less];
            lootTable.getAlias()[less] = more;

            scaled[more] = scaled[more] + scaled[less] - 1.0;

            if (scaled[more] < 1.0) small[smallIndex++] = more;
            else large[largeIndex++] = more;
        }

        while (smallIndex > 0) lootTable.getProbability()[small[--smallIndex]] = 1.0;
        while (largeIndex > 0) lootTable.getProbability()[large[--largeIndex]] = 1.0;
    }

    public int next() {
        int column = random.nextInt(lootTable.getProbability().length);
        return random.nextDouble() < lootTable.getProbability()[column] ? column : lootTable.getAlias()[column];
    }
}
