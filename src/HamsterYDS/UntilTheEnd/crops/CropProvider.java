package HamsterYDS.UntilTheEnd.crops;

import HamsterYDS.UntilTheEnd.UntilTheEnd;
import HamsterYDS.UntilTheEnd.internal.UTEi18n;

import java.util.HashMap;
import java.util.List;

public class CropProvider {
    public static HashMap<String, HashMap<String, Double>> seasonCrops = new HashMap<String, HashMap<String, Double>>();

    public static void loadConfig() {
        for (String path : Crops.yaml.getKeys(true)) {
            if (path.equalsIgnoreCase("seasonCrops")) continue;
            if (path.startsWith("seasonCrops")) {
                List<String> seasons = Crops.yaml.getStringList(path + ".seasons");
                List<Double> percents = Crops.yaml.getDoubleList(path + ".percents");
                HashMap<String, Double> crop = new HashMap<String, Double>();
                int index = 0;
                for (String name : seasons) {
                    crop.put(name, percents.get(index));
                    index++;
                }
                UntilTheEnd.getInstance().getLogger().info(UTEi18n.parse("crops.provider.rule", path.replace("seasonCrops.", ""), String.valueOf(crop)));
                seasonCrops.put(path.replace("seasonCrops.", ""), crop);
            }
        }
    }
}
