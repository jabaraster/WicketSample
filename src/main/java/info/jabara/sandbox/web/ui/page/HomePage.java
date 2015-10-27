/**
 *
 */
package info.jabara.sandbox.web.ui.page;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import info.jabara.sandbox.util.MemoryUtil;
import jabara.wicket.ComponentCssHeaderItem;
import jabara.wicket.Models;

/**
 * @author jabaraster
 */
public class HomePage extends WebPageBase {
    private static final long serialVersionUID = -1017140620750071969L;

    /**
     * メモリを大量消費させるためのフィールド.
     */
    private final Serializable[] largeObject = MemoryUtil.createLargeObject();

    private Link<?>                goNext;
    private Label                  instantiationTime;
    private ListView<Serializable> list;

    /**
     *
     */
    public HomePage() {
        this.add(getInstantiationTime());
        this.add(getGoNext());
        this.add(getList());
    }

    /**
     * @see info.jabara.sandbox.web.ui.page.WebPageBase#renderHead(org.apache.wicket.markup.head.IHeaderResponse)
     */
    @Override
    public void renderHead(final IHeaderResponse pResponse) {
        super.renderHead(pResponse);
        pResponse.render(ComponentCssHeaderItem.forType(HomePage.class));
    }

    /**
     * @return
     */
    @Override
    protected IModel<?> getTitleLabelModel() {
        return Models.of("Home"); //$NON-NLS-1$
    }

    @SuppressWarnings("serial")
    private Link<?> getGoNext() {
        if (this.goNext == null) {
            this.goNext = new Link<Object>("goNext") { //$NON-NLS-1$
                @Override
                public void onClick() {
                    final NextPage nextPage = new NextPage();
                    nextPage.setBackPage(HomePage.this);
                    setResponsePage(nextPage);
                }
            };
        }

        return this.goNext;
    }

    private Label getInstantiationTime() {
        if (this.instantiationTime == null) {
            this.instantiationTime = new Label("instantiationTime",              //$NON-NLS-1$
                    Models.of(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()))); //$NON-NLS-1$
        }
        return this.instantiationTime;
    }

    @SuppressWarnings("serial")
    private ListView<Serializable> getList() {
        if (this.list == null) {
            this.list = new ListView<Serializable>("list", Arrays.asList(this.largeObject)) { //$NON-NLS-1$
                @Override
                protected void populateItem(final ListItem<Serializable> pItem) {
                    pItem.add(new Label("obj", pItem.getModelObject())); //$NON-NLS-1$
                }
            };
        }

        return this.list;
    }
}
