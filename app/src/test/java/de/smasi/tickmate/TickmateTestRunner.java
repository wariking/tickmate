package de.smasi.tickmate;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.FileFsFile;
import org.robolectric.res.FsFile;

/**
 * Created by avanpelt on 10/9/15.
 */
public class TickmateTestRunner extends RobolectricGradleTestRunner {
    private static final String MODULE_ROOT = "app";

    public TickmateTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    protected AndroidManifest getAppManifest(Config config) {
        AndroidManifest appManifest = super.getAppManifest(config);
        FsFile androidManifestFile = appManifest.getAndroidManifestFile();

        if (androidManifestFile.exists()) {
            return appManifest;
        } else {
            androidManifestFile = FileFsFile.from(MODULE_ROOT, "src/main/AndroidManifest.xml");
            FsFile resDirectory = FileFsFile.from(MODULE_ROOT, "src/main/res");
            FsFile assetsDirectory = FileFsFile.from(MODULE_ROOT, "src/main/assets");

            return new AndroidManifest(androidManifestFile, resDirectory, assetsDirectory);
        }
    }
}