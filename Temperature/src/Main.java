import ru.academits.nikolenko.temperature.model.scale.CelsiusScale;
import ru.academits.nikolenko.temperature.model.scale.FahrenheitScale;
import ru.academits.nikolenko.temperature.model.scale.KelvinScale;
import ru.academits.nikolenko.temperature.model.scale.Scale;
import ru.academits.nikolenko.temperature.view.DesktopView;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = new Scale[]{new CelsiusScale(), new FahrenheitScale(), new KelvinScale()};

        DesktopView view = new DesktopView(scales);
        view.run();
        view.setVisible(true);
    }
}