package com.mgwt.client;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.event.ShowMasterEvent;
import com.mgwt.client.event.ActionEvent;
import com.mgwt.client.event.ActionNames;

public class DetailActivity extends MGWTAbstractActivity {

  private final DetailView detailView;
  private final String eventId;

  public DetailActivity(DetailView detailView, String eventId) {
    this.detailView = detailView;
    this.eventId = eventId;

  }

  @Override
  public void start(AcceptsOneWidget panel, final EventBus eventBus) {
    addHandlerRegistration(detailView.getMainButton().addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
        eventBus.fireEvent(new ShowMasterEvent(eventId));

      }
    }));

    addHandlerRegistration(detailView.getBackbutton().addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
        ActionEvent.fire(eventBus, ActionNames.BACK);

      }
    }));

  }

}
