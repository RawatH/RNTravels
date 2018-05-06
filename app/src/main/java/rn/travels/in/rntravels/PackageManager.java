package rn.travels.in.rntravels;

import rn.travels.in.rntravels.models.PackageVO;

/**
 * Created by demo on 28/02/18.
 */

public class PackageManager {
    private static PackageManager INSTANCE;
    private PackageVO selectedPackage;

    private PackageManager() {
    }

    public static PackageManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PackageManager();
        }
        return INSTANCE;
    }

    public PackageVO getSelectedPackage() {
        return selectedPackage;
    }

    public void setSelectedPackage(PackageVO selectedPackage) {
        this.selectedPackage = selectedPackage;
        this.selectedPackage.populatePdfList();
    }
}
