package com.mgwt.client.activities;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class UIEntrySelectedEvent extends Event<UIEntrySelectedEvent.Handler> {

	public enum UIEntry {
		ABOUT, PROJECT
	}

	public interface Handler {
		void onAnimationSelected(UIEntrySelectedEvent event);
	}

	private static final Type<Handler> TYPE = new Type<Handler>();
	private final UIEntry entry;

	public static void fire(EventBus eventBus, UIEntry entry) {
		eventBus.fireEvent(new UIEntrySelectedEvent(entry));
	}

	public static HandlerRegistration register(EventBus eventBus, Handler handler) {
		return eventBus.addHandler(TYPE, handler);
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	protected UIEntrySelectedEvent(UIEntry entry) {
		this.entry = entry;

	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onAnimationSelected(this);

	}

	public static Type<Handler> getType() {
		return TYPE;
	}

	public UIEntry getEntry() {
		return entry;
	}
}
