package vektah.rust.cargo.project.settings;

import com.intellij.util.messages.Topic;

public class CargoTopic extends Topic<CargoProjectSettingsListener> {
    public final static CargoTopic INSTANCE = new CargoTopic();

    private CargoTopic() {
        super("Cargo-specific settings", CargoProjectSettingsListener.class);
    }
}
