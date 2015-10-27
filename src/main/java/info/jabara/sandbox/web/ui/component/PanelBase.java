package info.jabara.sandbox.web.ui.component;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * @author jabaraster
 */
public abstract class PanelBase extends Panel {
    private static final long serialVersionUID = 966348284651360220L;

    /**
     * @param pId -
     */
    public PanelBase(final String pId) {
        super(pId);
    }

    /**
     * @param pId -
     * @param pModel -
     */
    public PanelBase(final String pId, final IModel<?> pModel) {
        super(pId, pModel);
    }

    /**
     * WicketのComponentのidを類推します. <br>
     * このメソッドはWicketのComponentを生成するgetterの中から呼び出して下さい. <br>
     *
     * @return このメソッドを呼び出したgetterからgetを除き、先頭文字を小文字にした文字列.
     */
    protected static String id() {
        final String getterName = new Throwable().getStackTrace()[1].getMethodName();
        if (getterName.length() < 4) {
            throw new IllegalStateException();
        }
        if (!getterName.startsWith("get")) { //$NON-NLS-1$
            throw new IllegalStateException();
        }
        final String s = getterName.substring("get".length()); //$NON-NLS-1$
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

}
