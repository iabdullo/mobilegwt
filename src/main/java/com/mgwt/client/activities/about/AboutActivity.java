/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.mgwt.client.activities.about;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.mgwt.client.ClientFactory;
import com.mgwt.client.DetailActivity;
import com.mgwt.client.event.ActionEvent;
import com.mgwt.client.event.ActionNames;

/**
 * @author Daniel Kurka
 * 
 */
public class AboutActivity extends DetailActivity {

	private final ClientFactory clientFactory;

	public AboutActivity(ClientFactory clientFactory) {
		super(clientFactory.getAboutView(), "nav");
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		AboutView aboutView = clientFactory.getAboutView();

		aboutView.getBackbuttonText().setText("Home");

		aboutView.getHeader().setText("About");

		aboutView.getMainButtonText().setText("Nav");

		addHandlerRegistration(aboutView.getBackbutton().addTapHandler(new TapHandler() {

			@Override
			public void onTap(TapEvent event) {
				ActionEvent.fire(eventBus, ActionNames.BACK);

			}
		}));

		panel.setWidget(aboutView);

	}

}
