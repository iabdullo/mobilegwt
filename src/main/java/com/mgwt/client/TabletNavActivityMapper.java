package com.mgwt.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.mgwt.client.activities.ui.UIActivity;
import com.mgwt.client.activities.ui.UIPlace;

public class TabletNavActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	public TabletNavActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	private UIActivity uiActivity;
//	private ShowCaseListActivity showCaseListActivity;
//	private AnimationActivity animationActivity;

	private Activity getUIActivity() {
		if (uiActivity == null) {
			uiActivity = new UIActivity(clientFactory);
		}
		return uiActivity;
	}

/*
	private Activity getShowCaseListActivity() {
		if (showCaseListActivity == null) {
			showCaseListActivity = new ShowCaseListActivity(clientFactory);
		}
		return showCaseListActivity;
	}
*/
//
//	private Activity getAnimationActicity() {
//		if (animationActivity == null) {
//			animationActivity = new AnimationActivity(clientFactory);
//		}
//		return animationActivity;
//	}

	@Override
	public Activity getActivity(Place place) {
	/*	if (place instanceof HomePlace || place instanceof AboutPlace) {
			return getShowCaseListActivity();
		}*/

		if (place instanceof UIPlace) {
			return getUIActivity();
		}
		return null;
	}

}
