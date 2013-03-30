package com.mgwt.client.activities.project;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: Akoya
 * Date: 3/27/13
 * Time: 9:42 AM
 */
public class ProjectPlace extends Place {
    public static class Tokenizer implements PlaceTokenizer<ProjectPlace> {

        @Override
        public ProjectPlace getPlace(String token) {
            return new ProjectPlace();
        }

        @Override
        public String getToken(ProjectPlace place) {
            return "";
        }

    }

}
