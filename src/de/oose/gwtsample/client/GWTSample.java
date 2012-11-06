package de.oose.gwtsample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
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
			Element elem = DOM.createDiv();
			elem.setInnerHTML(new Integer(i).toString());
			elem.setId("prime" + new Integer(i).toString());
			elem.setClassName("muted span1");
			rootDiv.appendChild(elem);

		}
		
		
		class SendButtonHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				calculatePrimes();
			}

			private void calculatePrimes() {
				
				for (int i = 1000; i >= 1; i--) {
					primeService.isPrime(i,
							new AsyncCallback<Pair<Integer, Boolean>>() {

								@Override
								public void onFailure(Throwable caught) {
									
								}

								@Override
								public void onSuccess(
										Pair<Integer, Boolean> result) {
									Element elem = DOM.getElementById("prime"
											+ result.left);

									if (result.right) {
										elem.setClassName("text-success span1");
									} else {
										elem.setClassName("text-error span1");
									}

								}
							});
				}

			}
		}

		// Add a handler to send the name to the server
		SendButtonHandler handler = new SendButtonHandler();
		sendButton.addClickHandler(handler);
	}
}
