package com.mgwt.client.activities.project;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.mgwt.client.ClientFactory;
import com.mgwt.client.DetailActivity;
import com.mgwt.client.event.ActionEvent;
import com.mgwt.client.event.ActionNames;

/**
 * Created by IntelliJ IDEA.
 * User: Akoya
 * Date: 3/27/13
 * Time: 9:42 AM
 */
public class ProjectActivity extends DetailActivity {

    private final ClientFactory clientFactory;

    public ProjectActivity(ClientFactory clientFactory) {
        super(clientFactory.getAboutView(), "nav");
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, final EventBus eventBus) {
        super.start(panel, eventBus);
        ProjectView aboutView = clientFactory.getProjectView();

        aboutView.getBackbuttonText().setText("Project");

        aboutView.getHeader().setText("Organization Name");

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
