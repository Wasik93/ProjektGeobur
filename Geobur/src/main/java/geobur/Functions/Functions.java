package geobur.Functions;

import geobur.Errors.BadSyntaxException;
import geobur.Handlers.ZoomHandler;
import geobur.Parser.Expression;
import geobur.Utils.Axes;
import geobur.Utils.Plot;

import java.util.function.Function;


public class Functions {
    Plot plot;

    Function<Double,Double> function;
    public Functions(){
        function = (x -> x);
    }
    public Functions(String F) {
        function = aDouble -> {
            Expression expression = new Expression(F);
            if(!expression.checkSyntax()){
                throw new BadSyntaxException();
            }
            return expression.calculate(aDouble);
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


    public ZoomHandler newZoomHandler(String F){
        return new ZoomHandler(F);
    }
}
