/**
 *
 */
package info.jabara.sandbox.web.ui;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.protocol.http.WebApplication;

import info.jabara.sandbox.web.ui.component.PanelBase;
import info.jabara.sandbox.web.ui.page.HomePage;
import info.jabara.sandbox.web.ui.page.NextPage;
import info.jabara.sandbox.web.ui.page.WebPageBase;
import jabara.wicket.MarkupIdForceOutputer;

/**
 * @author jabaraster
 */
public class WicketApplication extends WebApplication {
    private final BeanManager injector;

    /**
     * @param pInjector -
     */
    public WicketApplication(final BeanManager pInjector) {
        this.injector = pInjector;
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.protocol.http.WebApplication#init()
     */
    @Override
    protected void init() {
        super.init();

        moutPages();

        getComponentInstantiationListeners().add(new IComponentInstantiationListener() {
            @SuppressWarnings("synthetic-access")
            @Override
            public void onInstantiation(final Component pComponent) {
                if (pComponent instanceof PanelBase || pComponent instanceof WebPageBase) {
                    inject(WicketApplication.this.injector, pComponent);
                }
            }
        });
        getComponentInstantiationListeners().add(new MarkupIdForceOutputer());
    }

    private void moutPages() {
        this.mountPage("next", NextPage.class); //$NON-NLS-1$
    }

    /**
     * http://d.hatena.ne.jp/jabaraster/20120211/1328932645
     *
     * @param pBeanManager -
     * @param pComponent -
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void inject(final BeanManager pBeanManager, final Component pComponent) {
        final Class pType = pComponent.getClass();
        final Bean<Object> bean = (Bean<Object>) pBeanManager.resolve(pBeanManager.getBeans(pType));
        final CreationalContext<Object> cc = pBeanManager.createCreationalContext(bean);
        final AnnotatedType<Object> at = pBeanManager.createAnnotatedType(pType);
        final InjectionTarget<Object> it = pBeanManager.createInjectionTarget(at);
        it.inject(pComponent, cc);
    }
}
