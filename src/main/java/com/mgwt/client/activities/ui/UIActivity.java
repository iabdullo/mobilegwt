/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.mgwt.client.activities.ui;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.mgwt.client.ClientFactory;
import com.mgwt.client.activities.Item;
import com.mgwt.client.activities.UIEntrySelectedEvent;
import com.mgwt.client.activities.UIEntrySelectedEvent.UIEntry;
import com.mgwt.client.event.ActionEvent;
import com.mgwt.client.event.ActionNames;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Kurka
 * 
 */
public class UIActivity extends MGWTAbstractActivity {

  private final ClientFactory clientFactory;

  private int oldIndex;

  private List<Item> items;

  public UIActivity(ClientFactory clientFactory) {
    this.clientFactory = clientFactory;

  }

  @Override
  public void start(AcceptsOneWidget panel, final EventBus eventBus) {
    final UIView view = clientFactory.getUIView();

    view.setBackButtonText("Home");
    view.setTitle("UI");

    addHandlerRegistration(view.getBackButton().addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
        ActionEvent.fire(eventBus, ActionNames.BACK);

      }
    }));
    items = createItems();
    view.renderItems(items);

    addHandlerRegistration(view.getList().addCellSelectedHandler(new CellSelectedHandler() {

      @Override
      public void onCellSelected(CellSelectedEvent event) {
        int index = event.getIndex();

        view.setSelectedIndex(oldIndex, false);
        view.setSelectedIndex(index, true);
        oldIndex = index;

        UIEntrySelectedEvent.fire(eventBus, items.get(index).getEntry());

      }
    }));

    panel.setWidget(view);

  }

  /**
   * @return
   */
  private List<Item> createItems() {
    ArrayList<Item> list = new ArrayList<Item>();
    list.add(new Item("About",UIEntry.ABOUT));
    return list;
  }

}
