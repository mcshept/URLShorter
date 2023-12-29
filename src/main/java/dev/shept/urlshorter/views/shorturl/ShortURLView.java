package dev.shept.urlshorter.views.shorturl;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import dev.shept.urlshorter.views.MainLayout;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;

import static com.vaadin.flow.component.button.ButtonVariant.LUMO_TERTIARY_INLINE;

@PageTitle("Short URL")
@Route(value = "dashboard/add", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
@JsModule("./clipboard/clipboard.js")
@SuppressWarnings("deprecated")
public class ShortURLView extends Composite<VerticalLayout> {

    public ShortURLView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H1 h1 = new H1("URL Shorter");
        Hr hr = new Hr();
        TextField textField = new TextField("URL");
        Button buttonPrimary = new Button("Send");
        Hr hr2 = new Hr();
        H2 h2 = new H2("URL:");
        Paragraph textLarge = new Paragraph("N/A");
        Button buttonSecondary = new Button("Copy");
        Paragraph textLarge2 = new Paragraph("N/A");

        setupComponents(textField, buttonPrimary, buttonSecondary, textLarge, textLarge2);

        buttonPrimary.addClickListener(event -> sendRequest(textField, textLarge, textLarge2, buttonSecondary));
        buttonSecondary.addClickListener(event -> copyTextToClipboard(textLarge));
        textField.addKeyPressListener(Key.ENTER, event -> sendRequest(textField, textLarge, textLarge2, buttonSecondary));

        layoutColumn2.add(h1, hr, textField, buttonPrimary, hr2, h2, textLarge, buttonSecondary, textLarge2);
        layoutColumn2.setAlignItems(FlexComponent.Alignment.STRETCH);

        getContent().add(layoutColumn2);
        getContent().setWidthFull();
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidthFull();
        layoutColumn2.getStyle().set("flex-grow", "1");
    }

    private void setupComponents(TextField textField, Button buttonPrimary, Button buttonSecondary,
                                 Paragraph textLarge, Paragraph textLarge2) {
        textField.setWidth("100%");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.setWidth("min-content");
        buttonSecondary.setWidth("min-content");

        textLarge.setWidth("100%");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        textLarge2.setWidth("100%");
        textLarge2.getStyle().set("font-size", "var(--lumo-font-size-xl)");
    }

    private void sendRequest(TextField textField, Paragraph textLarge, Paragraph textLarge2, Button buttonSecondary) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://localhost:8080/api/v1/add");
            String jsonPayload = "{\"url\":\"" + textField.getValue() + "\"}";
            HttpEntity stringEntity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);

            JSONObject jsonObject = new JSONObject(responseBody);
            String url = jsonObject.getString("url");
            String uuid = jsonObject.getString("uuid");
            textLarge.setText("https://shept.rocks/" + uuid);
            textLarge2.setText(uuid);
            buttonSecondary.setDisableOnClick(response.getCode() != 200);
        } catch (Exception ignored) {
        }
    }

    private void copyTextToClipboard(Paragraph textLarge) {
        if (!textLarge.getText().equals("N/A")) {
            UI.getCurrent().getPage().executeJs("window.copyToClipboard($0)", textLarge.getText());
            Notification copySuccess = createSubmitSuccess("Copied URL successfully!");
            copySuccess.open();
        }
    }

    public static Notification createSubmitSuccess(String text) {
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        Icon icon = VaadinIcon.CHECK_CIRCLE.create();

        Button viewBtn = new Button("View", clickEvent -> notification.close());
        viewBtn.getStyle().setMargin("0 0 0 var(--lumo-space-l)");

        var layout = new HorizontalLayout(icon, new Text(text), viewBtn,
                createCloseBtn(notification));
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);

        return notification;
    }

    public static Notification createReportError(String text) {
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        Icon icon = VaadinIcon.WARNING.create();
        Button retryBtn = new Button("Retry",
                clickEvent -> notification.close());
        retryBtn.getStyle().setMargin("0 0 0 var(--lumo-space-l)");

        var layout = new HorizontalLayout(icon, new Text(text), retryBtn,
                createCloseBtn(notification));
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);

        return notification;
    }

    public static Button createCloseBtn(Notification notification) {
        Button closeBtn = new Button(VaadinIcon.CLOSE_SMALL.create(),
                clickEvent -> notification.close());
        closeBtn.addThemeVariants(LUMO_TERTIARY_INLINE);

        return closeBtn;
    }

}