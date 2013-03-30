package com.mgwt.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.mgwt.client.activities.about.AboutActivity;
import com.mgwt.client.activities.project.ProjectActivity;
import com.mgwt.client.activities.project.ProjectPlace;
import com.mgwt.client.places.HomePlace;

public class TabletMainActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	private Place lastPlace;

	public TabletMainActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public Activity getActivity(Place place) {
		Activity activity = getActivity(lastPlace, place);
		lastPlace = place;
		return activity;

	}

	private AboutActivity aboutActivity;
	private ProjectActivity projectActivity;

	private AboutActivity getAboutActivity() {
		if (aboutActivity == null) {
			aboutActivity = new AboutActivity(clientFactory);
		}

		return aboutActivity;
	}

	private ProjectActivity getProjectActivity() {
		if (projectActivity == null) {
			projectActivity = new ProjectActivity(clientFactory);
		}

		return projectActivity;
	}

	private Activity getActivity(Place lastPlace, Place newPlace) {
		if (newPlace instanceof HomePlace) {
//			return getAboutActivity();
            return getProjectActivity();
		}

        if(newPlace instanceof ProjectPlace){
            return getProjectActivity();
        }
		return null;
	}

}
