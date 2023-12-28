package dev.shept.urlshorter.views.shorturl;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
import com.vaadin.flow.router.RouteAlias;
import dev.shept.urlshorter.views.MainLayout;

@PageTitle("Short URL")
@Route(value = "add", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class ShortURLView extends Composite<VerticalLayout> {

    public ShortURLView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H1 h1 = new H1();
        Hr hr = new Hr();
        TextField textField = new TextField();
        Button buttonPrimary = new Button();
        Hr hr2 = new Hr();
        H2 h2 = new H2();
        Paragraph textLarge = new Paragraph();
        Button buttonSecondary = new Button();
        Paragraph textLarge2 = new Paragraph();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        h1.setText("URL Shorter");
        h1.setWidth("max-content");
        textField.setLabel("URL");
        textField.setWidth("100%");
        buttonPrimary.setText("Send");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        h2.setText("URL:");
        h2.setWidth("max-content");
        textLarge.setText("url");
        textLarge.setWidth("100%");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        buttonSecondary.setText("Copy");
        buttonSecondary.setWidth("min-content");
        textLarge2.setText("id");
        textLarge2.setWidth("100%");
        textLarge2.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h1);
        layoutColumn2.add(hr);
        layoutColumn2.add(textField);
        layoutColumn2.add(buttonPrimary);
        layoutColumn2.add(hr2);
        layoutColumn2.add(h2);
        layoutColumn2.add(textLarge);
        layoutColumn2.add(buttonSecondary);
        layoutColumn2.add(textLarge2);
    }
}
