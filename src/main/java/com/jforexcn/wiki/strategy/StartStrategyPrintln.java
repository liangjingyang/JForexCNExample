package com.jforexcn.wiki.strategy;

/**
 * Created by simple(simple.continue@gmail.com) on 05/12/2017.
 *
 * https://www.dukascopy.com/wiki/en/development/get-started-api/use-in-jforex/strategy-tutorial
 * https://www.jforexcn.com/development/getting-started/general/strategy-tutorial.html
 */


import java.util.*;

import com.dukascopy.api.*;

public class StartStrategyPrintln implements IStrategy {
    private IEngine engine;
    private IConsole console;
    private IHistory history;
    private IContext context;
    private IIndicators indicators;
    private IUserInterface userInterface;

    public void onStart(IContext context) throws JFException {
        this.engine = context.getEngine();
        this.console = context.getConsole();
        this.history = context.getHistory();
        this.context = context;
        this.indicators = context.getIndicators();
        this.userInterface = context.getUserInterface();

        //added code:
        console.getOut().println("Method onStart() called");
    }

    public void onAccount(IAccount account) throws JFException {
    }

    public void onMessage(IMessage message) throws JFException {
    }

    public void onStop() throws JFException {
    }

    public void onTick(Instrument instrument, ITick tick) throws JFException {
    }

    public void onBar(Instrument instrument, Period period, IBar askBar, IBar bidBar) throws JFException {
    }
}
