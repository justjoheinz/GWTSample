package de.oose.gwtsample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import de.oose.gwtsample.shared.Pair;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTSample implements EntryPoint {

	private final PrimeServiceAsync primeService = GWT
			.create(PrimeService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");

		// We can add style names to widgets
		sendButton.setStyleName("btn btn-primary");

		RootPanel.get("sendButtonContainer").add(sendButton);

		Element rootDiv = DOM.getElementById("primeContainer");
		for (int i = 1; i <= 1000; i++) {
			rootDiv.appendChild(createPrimeDiv(i));
		}

		// Add a handler to send the name to the server
		SendButtonHandler handler = new SendButtonHandler();
		sendButton.addClickHandler(handler);
	}

	class SendButtonHandler implements ClickHandler {
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		public void onClick(ClickEvent event) {
			calculatePrimes();
		}

	}

	private void calculatePrimes() {

		for (int i = 1000; i >= 1; i--) {
			setStyleOfPrimeDiv(i, "muted span1");
			final int prime = i;
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {

				@Override
				public void execute() {
					setStyleOfPrimeDiv(prime, "muted span1");
					primeService.isPrime(prime,
							new AsyncCallback<Pair<Integer, Boolean>>() {

								@Override
								public void onFailure(Throwable caught) {

								}

								@Override
								public void onSuccess(
										final Pair<Integer, Boolean> result) {

									if (result.right) {
										setStyleOfPrimeDiv(result.left,
												"text-success span1");
									} else {
										setStyleOfPrimeDiv(result.left,
												"text-error span1");
									}

								}
							});
				}
			});
		}
	}

	private void setStyleOfPrimeDiv(Integer prime, String style) {
		Element elem = DOM.getElementById("prime" + prime);
		elem.setClassName(style);
	}

	private Element createPrimeDiv(Integer prime) {
		Element elem = DOM.createDiv();
		elem.setInnerHTML(prime.toString());
		elem.setId("prime" + prime);
		elem.setClassName("muted span1");
		return elem;
	}
}
