import ru.academits.nikolenko.temperature.controller.TemperatureController;
import ru.academits.nikolenko.temperature.model.Converter;
import ru.academits.nikolenko.temperature.model.TemperatureConverter;
import ru.academits.nikolenko.temperature.model.scale.CelsiusScale;
import ru.academits.nikolenko.temperature.model.scale.FahrenheitScale;
import ru.academits.nikolenko.temperature.model.scale.KelvinScale;
import ru.academits.nikolenko.temperature.model.scale.Scale;
import ru.academits.nikolenko.temperature.view.DesktopView;
import ru.academits.nikolenko.temperature.view.View;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = new Scale[]{new CelsiusScale(), new KelvinScale(), new FahrenheitScale()};

        Converter converter = new TemperatureConverter(scales);
        View view = new DesktopView();

        TemperatureController controller = new TemperatureController(converter, view);
        view.setController(controller);

        view.start();
    }
}