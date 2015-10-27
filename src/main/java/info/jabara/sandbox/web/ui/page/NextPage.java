/**
 *
 */
package info.jabara.sandbox.web.ui.page;

import org.apache.wicket.Page;
import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import info.jabara.sandbox.util.MemoryUtil;
import jabara.wicket.Models;

/**
 * @author jabaraster
 */
public class NextPage extends WebPageBase {
    private static final long serialVersionUID = -4960025142468818736L;

    /**
     * メモリを大量消費させるためのフィールド.
     */
    @SuppressWarnings("unused")
    private final Object largeObject = MemoryUtil.createLargeObject();

    private int backPageId;

    private AjaxLink<?> goBack;
    private Link<?>     goNext;

    /**
     *
     */
    public NextPage() {
        this.add(getGoNext());
        this.add(getgoBack());
    }

    /**
     * @param pPage
     */
    public void setBackPage(final Page pPage) {
        this.backPageId = pPage.getPageId();
    }

    /**
     * @see info.jabara.sandbox.web.ui.page.WebPageBase#getTitleLabelModel()
     */
    @Override
    protected IModel<?> getTitleLabelModel() {
        return Models.of("Next"); //$NON-NLS-1$
    }

    private void back() {
        final PageReference pageRef = new PageReference(this.backPageId);
        this.setResponsePage(pageRef.getPage());
    }

    @SuppressWarnings("serial")
    private AjaxLink<?> getgoBack() {
        if (this.goBack == null) {
            this.goBack = new AjaxLink<Object>("goBack") { //$NON-NLS-1$
                @SuppressWarnings("synthetic-access")
                @Override
                public void onClick(@SuppressWarnings("unused") final AjaxRequestTarget pTarget) {
                    back();
                }
            };
        }
        return this.goBack;
    }

    @SuppressWarnings("serial")
    private Link<?> getGoNext() {
        if (this.goNext == null) {
            this.goNext = new Link<Object>("goNext") { //$NON-NLS-1$
                @SuppressWarnings("synthetic-access")
                @Override
                public void onClick() {
                    final NextPage nextPage = new NextPage();
                    nextPage.setBackPageId(NextPage.this.backPageId);
                }
            };
        }
        return this.goNext;
    }

    private void setBackPageId(final int pBackPageId) {
        this.backPageId = pBackPageId;
    }
}
