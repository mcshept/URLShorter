package dev.shept.urlshorter.views.abouturl;

import com.vaadin.flow.component.Composite;
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

@PageTitle("About URL")
@Route(value = "about", layout = MainLayout.class)
@Uses(Icon.class)
public class AboutURLView extends Composite<VerticalLayout> {

    public AboutURLView() {
        H1 h1 = new H1();
        Hr hr = new Hr();
        TextField textField = new TextField();
        Button buttonSecondary = new Button();
        Hr hr2 = new Hr();
        H2 h2 = new H2();
        Paragraph textLarge = new Paragraph();
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
        getContent().add(h1);
        getContent().add(hr);
        getContent().add(textField);
        getContent().add(buttonSecondary);
        getContent().add(hr2);
        getContent().add(h2);
        getContent().add(textLarge);
    }
}
