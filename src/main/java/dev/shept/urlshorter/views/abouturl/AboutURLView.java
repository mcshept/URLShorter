package dev.shept.urlshorter.views.abouturl;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dev.shept.urlshorter.views.MainLayout;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;

@PageTitle("About URL")
@Route(value = "dashboard/about", layout = MainLayout.class)
@Uses(Icon.class)
@SuppressWarnings("deprecated")
public class AboutURLView extends Composite<VerticalLayout> {

    public TextField textField = new TextField();
    public Paragraph textLarge = new Paragraph();

    public AboutURLView() {
        H1 h1 = new H1();
        Hr hr = new Hr();
        textField.addKeyPressListener(Key.ENTER, event -> sendPost());
        Button buttonSecondary = new Button();
        Hr hr2 = new Hr();
        H2 h2 = new H2();
        getContent().setHeightFull();
        getContent().setWidthFull();
        h1.setText("About Short URL");
        h1.setWidth("max-content");
        textField.setLabel("Shorted URL");
        textField.setWidth("100%");
        buttonSecondary.setText("Lookup");
        buttonSecondary.setWidth("min-content");
        h2.setText("Target:");
        h2.setWidth("max-content");
        textLarge.setText("target");
        textLarge.setWidth("100%");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        buttonSecondary.addClickListener(event -> sendPost());
        getContent().add(h1);
        getContent().add(hr);
        getContent().add(textField);
        getContent().add(buttonSecondary);
        getContent().add(hr2);
        getContent().add(h2);
        getContent().add(textLarge);
    }

    private void sendPost() {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("http://localhost:8080/api/v1/about/" + textField.getValue());
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);

            JSONObject jsonObject = new JSONObject(responseBody);
            String url = jsonObject.getString("url");
            textLarge.setText(url);
        } catch (Exception ignored) {
        }
    }

}
