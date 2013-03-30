package com.mgwt.client.activities.project;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.*;
import com.googlecode.mgwt.ui.client.widget.base.PullPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.tabbar.*;
import com.mgwt.client.DetailViewGwtImpl;
import com.mgwt.client.activities.home.Topic;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Akoya
 * Date: 3/27/13
 * Time: 9:43 AM
 */
public class ProjectViewGwtImpl extends DetailViewGwtImpl implements ProjectView {

    private Button button;
    private Carousel carousel;
    private String cars1 = "<p>First Name</p><br/><p>About</p><br/><br/>";
    private String cars2 = "Nick Name<br/><p>Age</p><br/><p>Gender</p>";

    public ProjectViewGwtImpl() {

        carousel = new Carousel();
        carousel.add(new HTML(cars1));
        carousel.add(new HTML(cars2));
        carousel.setHeight("170px");
        main.add(carousel);

        TabPanel topPanel = new TabPanel();
        topPanel.setDisplayTabBarOnTop(true);


        LayoutPanel layoutPanel1 = new LayoutPanel();
        layoutPanel1.add(carousel);
        FavoritesTabBarButton organizations = new FavoritesTabBarButton();
        organizations.setText("Organizations");
        DownloadsTabBarButton tags = new DownloadsTabBarButton();
        tags.setText("Tags");
        HistoryTabBarButton project = new HistoryTabBarButton();
        project.setText("Project");

        topPanel.add(organizations, getListPanel("Organization ", 5));
        topPanel.add(tags, getListPanel("Tags ", 40));
        topPanel.add(project, getListPanel("Project ", 20));

        layoutPanel1.add(topPanel);

        TabPanel tabBar = new TabPanel();
        ContactsTabBarButton me = new ContactsTabBarButton();
        me.setText("Me");
        HistoryTabBarButton discover = new HistoryTabBarButton();
        discover.setText("Discover");
        DownloadsTabBarButton notification = new DownloadsTabBarButton();
        notification.setText("Notifications");

        tabBar.add(me, layoutPanel1);
        tabBar.add(discover, getSecondTabBar());
        tabBar.add(notification, getListPanel("Notification Name", 15));

        main.add(tabBar);
    }

    private PullPanel getListPanel(String text, int rowcount) {

        CellList<Topic> cellList = new CellList<Topic>(new BasicCell<Topic>() {

            @Override
            public String getDisplayString(Topic model) {
                return model.getName();
            }
        });

        LinkedList<Topic> list = new LinkedList<Topic>();

        for (int i = 0; i < rowcount; i++) {
            list.add(new Topic(text + i, i));
        }

        cellList.render(list);
        cellList.setRound(true);
        PullPanel pullToRefresh = new PullPanel();
        pullToRefresh.add(cellList);
        return pullToRefresh;
    }

    private TabPanel getSecondTabBar() {

        TabPanel topPanel = new TabPanel();
        topPanel.setDisplayTabBarOnTop(true);
        final MSearchBox searchBox = new MSearchBox();
        final LayoutPanel layoutPanel = new LayoutPanel();
        layoutPanel.add(searchBox);
        searchBox.setPlaceHolder("Type Organization Name");
        final CellList<Topic> cellList = new CellList<Topic>(new BasicCell<Topic>() {

            @Override
            public String getDisplayString(Topic model) {
                return model.getName();
            }
        });

        final LinkedList<Topic> list = new LinkedList<Topic>();

        layoutPanel.add(cellList);
        searchBox.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                list.add(new Topic(searchBox.getText(), 1));
                cellList.render(list);
            }
        });
        FavoritesTabBarButton nearby = new FavoritesTabBarButton();
        nearby.setText("Near By");
        DownloadsTabBarButton byType = new DownloadsTabBarButton();
        byType.setText("By Type");

        LayoutPanel nearByPanel = new LayoutPanel();
        nearByPanel.add(new HTML("<b>Your location:</b> 1600 Pennsylvania Ave NW  Washington, DC 20500, United States"));
        nearByPanel.add(getListPanel("Organization Name X", 7));
        final LayoutPanel notificationPanel = new LayoutPanel();
//        notificationPanel.issetRound(true);
        MListBox listBox = new MListBox();
        listBox.addItem("Type 1", "Type 1");
        listBox.addItem("Type 2", "Type 2");
        listBox.addItem("Type 3", "Type 3");
        listBox.addItem("Type 4", "Type 4");
        listBox.addItem("Type 5", "Type 5");
        listBox.addItem("Type 6", "Type 6");
        notificationPanel.add(listBox);

        listBox.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {

            }
        });

        topPanel.add(new SearchTabBarButton(), layoutPanel);
        topPanel.add(nearby, nearByPanel);
        topPanel.add(byType, notificationPanel);

        return topPanel;
    }

    @Override
    public HasTapHandlers getBackbutton() {
        if (MGWT.getOsDetection().isPhone()) {
            return button;
        }
        return super.getBackbutton();
    }
}
