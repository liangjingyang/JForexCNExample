package com.jforexcn.wiki.indicator;

/**
 * Created by simple(simple.continue@gmail.com) on 05/12/2017.
 *
 * https://www.dukascopy.com/wiki/en/development/get-started-api/use-in-jforex/create-indicator
 * https://www.jforexcn.com/development/getting-started/general/create-a-indicator.html
 */


import com.dukascopy.api.indicators.*;

public class Indicator implements IIndicator {
    private IndicatorInfo indicatorInfo;
    private InputParameterInfo[] inputParameterInfos;
    private OptInputParameterInfo[] optInputParameterInfos;
    private OutputParameterInfo[] outputParameterInfos;
    private double[][] inputs = new double[1][];
    private int timePeriod = 2;
    private double[][] outputs = new double[1][];

    public void onStart(IIndicatorContext context) {
        indicatorInfo = new IndicatorInfo("EXAMPIND", "Sums previous values", "My indicators",
                false, false, false, 1, 1, 1);
        inputParameterInfos = new InputParameterInfo[] {
                new InputParameterInfo("Input data", InputParameterInfo.Type.DOUBLE)
        };
        optInputParameterInfos = new OptInputParameterInfo[] {
                new OptInputParameterInfo("Time cPeriod", OptInputParameterInfo.Type.OTHER,
                        new IntegerRangeDescription(2, 2, 100, 1))
        };
        outputParameterInfos = new OutputParameterInfo[] {
                new OutputParameterInfo("out", OutputParameterInfo.Type.DOUBLE,
                        OutputParameterInfo.DrawingStyle.LINE)
        };
    }

    public IndicatorResult calculate(int startIndex, int endIndex) {
        //calculating startIndex taking into account lookback value
        if (startIndex - getLookback() < 0) {
            startIndex = getLookback();
        }
        if (startIndex > endIndex) {
            return new IndicatorResult(0, 0);
        }
        int i, j;
        for (i = startIndex, j = 0; i <= endIndex; i++, j++) {
            double value = 0;
            for (int k = timePeriod; k > 0; k--) {
                value += inputs[0][i - k];
            }
            outputs[0][j] = value;
        }
        return new IndicatorResult(startIndex, j);
    }

    public IndicatorInfo getIndicatorInfo() {
        return indicatorInfo;
    }

    public InputParameterInfo getInputParameterInfo(int index) {
        if (index < inputParameterInfos.length) {
            return inputParameterInfos[index];
        }
        return null;
    }

    public int getLookback() {
        return timePeriod;
    }

    public int getLookforward() {
        return 0;
    }

    public OptInputParameterInfo getOptInputParameterInfo(int index) {
        if (index < optInputParameterInfos.length) {
            return optInputParameterInfos[index];
        }
        return null;
    }

    public OutputParameterInfo getOutputParameterInfo(int index) {
        if (index < outputParameterInfos.length) {
            return outputParameterInfos[index];
        }
        return null;
    }

    public void setInputParameter(int index, Object array) {
        inputs[index] = (double[]) array;
    }

    public void setOptInputParameter(int index, Object value) {
        timePeriod = (Integer) value;
    }

    public void setOutputParameter(int index, Object array) {
        outputs[index] = (double[]) array;
    }
}

