package com.mgwt.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.touch.*;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ActionButton;

public abstract class DetailViewGwtImpl implements DetailView {

	protected LayoutPanel main;
	protected HeaderPanel headerPanel;
	protected HeaderButton headerBackButton;
	protected HeaderButton headerMainButton;

	protected HTML title;
    protected DecoratedPopupPanel popupPanel;

	public DetailViewGwtImpl() {
		main = new LayoutPanel();

		headerPanel = new HeaderPanel();
		title = new HTML();
		headerPanel.setCenterWidget(title);
		headerBackButton = new HeaderButton();
		headerBackButton.setBackButton(true);
		headerBackButton.setVisible(!MGWT.getOsDetection().isAndroid());

		headerMainButton = new HeaderButton();
		headerMainButton.setRoundButton(true);

		if (!MGWT.getOsDetection().isPhone()) {
			headerPanel.setLeftWidget(headerMainButton);
			headerMainButton.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getUtilCss().portraitonly());
		} else {
			headerPanel.setLeftWidget(headerBackButton);
		}
        ActionButton actionButton = new ActionButton();

        popupPanel = new DecoratedPopupPanel(true);

        MenuBar menuBar = new MenuBar(true);
        menuBar.addItem(new MenuItem("Edit Profile", new Command() {
            @Override
            public void execute() {
                popupPanel.hide();
                Window.alert("Edit Profile");
            }
        }));
        menuBar.addItem(new MenuItem("About the App", new Command() {
            @Override
            public void execute() {
                popupPanel.hide();
                Window.alert("About the App");
            }
        }));
        menuBar.addItem(new MenuItem("Share the App", new Command() {
            @Override
            public void execute() {
                popupPanel.hide();
                Window.alert("Share the App");
            }
        }));
        menuBar.addItem(new MenuItem("Send Feedback", new Command() {
            @Override
            public void execute() {
                popupPanel.hide();
                Window.alert("Send Feedback");
            }
        }));
        menuBar.addItem(new MenuItem("Logout", new Command() {
            @Override
            public void execute() {
                popupPanel.hide();
                Window.alert("Logout");
            }
        }));
        popupPanel.setWidget(menuBar);

        actionButton.addTouchHandler(new TouchHandler() {
            @Override
            public void onTouchStart(TouchStartEvent touchEvent) {

                final Widget source = (Widget) touchEvent.getSource();

                int left = source.getAbsoluteLeft() + 10;
                int top = source.getAbsoluteTop() + 30;

                popupPanel.setPopupPosition(left, top);
                popupPanel.show();
            }

            @Override
            public void onTouchCanceled(TouchCancelEvent touchCancelEvent) {

            }

            @Override
            public void onTouchEnd(TouchEndEvent touchEndEvent) {

            }

            @Override
            public void onTouchMove(TouchMoveEvent touchMoveEvent) {

            }
        });
        headerPanel.setRightWidget(actionButton);
		main.add(headerPanel);
//		main.add(scrollPanel);
	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public HasText getHeader() {
		return title;
	}

	@Override
	public HasText getBackbuttonText() {
		return headerBackButton;
	}

	@Override
	public HasTapHandlers getBackbutton() {
		return headerBackButton;
	}

	@Override
	public HasText getMainButtonText() {
		return headerMainButton;
	}

	@Override
	public HasTapHandlers getMainButton() {
		return headerMainButton;
	}

}
