package com.mgwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.googlecode.mgwt.mvp.client.history.MGWTPlaceHistoryHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.mgwt.client.css.AppBundle;
import com.mgwt.client.places.HomePlace;

/**
 * Created with IntelliJ IDEA.
 * User: Akoya
 * Date: 3/16/13
 * Time: 8:38 AM
 */
public class DemoViewEntryPoint implements EntryPoint {

    public void onModuleLoad() {

        // set viewport and other settings for mobile
        MGWTSettings.ViewPort viewPort = new MGWTSettings.ViewPort();
        viewPort.setTargetDensity(MGWTSettings.ViewPort.DENSITY.MEDIUM);
        viewPort.setUserScaleAble(false).setMinimumScale(1.0).setMinimumScale(1.0).setMaximumScale(1.0);

        MGWTSettings settings = new MGWTSettings();
        settings.setViewPort(viewPort);
        settings.setFullscreen(true);
        settings.setPreventScrolling(true);
        MGWT.applySettings(settings);
        StyleInjector.inject(AppBundle.INSTANCE.css().getText());

        final ClientFactory clientFactory = new ClientFactoryImpl();

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        createTabletDisplay(clientFactory);

        AppHistoryObserver historyObserver = new AppHistoryObserver();

        MGWTPlaceHistoryHandler historyHandler = new MGWTPlaceHistoryHandler(historyMapper, historyObserver);

        historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new HomePlace());
        historyHandler.handleCurrentHistory();
    }

    private void createTabletDisplay(ClientFactory clientFactory) {
        SimplePanel mainContainer = new SimplePanel();
        mainContainer.getElement().setId("main");
        AnimatableDisplay mainDisplay = GWT.create(AnimatableDisplay.class);

        TabletMainActivityMapper tabletMainActivityMapper = new TabletMainActivityMapper(clientFactory);

        AnimationMapper tabletMainAnimationMapper = new TabletMainAnimationMapper();

        AnimatingActivityManager mainActivityManager = new AnimatingActivityManager(tabletMainActivityMapper, tabletMainAnimationMapper, clientFactory.getEventBus());

        mainActivityManager.setDisplay(mainDisplay);
        mainContainer.setWidget(mainDisplay);

        RootPanel.get().add(mainContainer);

    }

}