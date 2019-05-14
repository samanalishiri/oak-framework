package ir.navaco.core.vo;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public abstract class AbstractVo<ID> {

    private ID id;

    private Integer version;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
