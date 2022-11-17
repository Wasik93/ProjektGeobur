package geobur.Functions;

import geobur.Errors.BadSyntaxException;
import geobur.Handlers.ZoomHandler;
import geobur.Utils.Axes;
import geobur.Utils.Plot;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.function.Function;


public class Functions {
    Plot plot;

    Function<Double,Double> function;
    public Functions(){
        function = (x -> x);
    }
    public Functions(String F) {
        function = aDouble -> {
            Argument x = new Argument("x");
            Expression expression = new Expression(F, x);
            if(!expression.checkSyntax()){
                throw new BadSyntaxException();
            }
            x.setArgumentValue(aDouble);
            return expression.calculate();
        };
    }

    public Plot plotChart(double zoomFactor) {
        Axes axes = new Axes(
                750, 500,
                -8 * zoomFactor, 8 * zoomFactor, 1,
                -6 * zoomFactor, 6 * zoomFactor, 1
        );

        plot = new Plot(
                function,
                -8 * zoomFactor, 8 * zoomFactor, 0.05,
                axes
        );

        return plot;
    }

    public ZoomHandler newZoomHandler(){
        return new ZoomHandler();
    }

    public ZoomHandler newZoomHandler(String F){
        return new ZoomHandler(F);
    }
}
