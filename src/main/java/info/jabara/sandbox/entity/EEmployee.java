/**
 *
 */
package info.jabara.sandbox.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jabara.jpa.entity.EntityBase;

/**
 * @author jabaraster
 */
@Entity
public class EEmployee extends EntityBase<EEmployee> {
    private static final long serialVersionUID = -2379295032511313938L;

    /**
     *
     */
    public static final int MAX_CHAR_COUNT_NAME = 50;

    @Column(length = MAX_CHAR_COUNT_NAME * 3, unique = true)
    @NotNull
    @Size(min = 1, max = MAX_CHAR_COUNT_NAME)
    String name;

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param pName the name to set
     */
    public void setName(final String pName) {
        this.name = pName;
    }
}
