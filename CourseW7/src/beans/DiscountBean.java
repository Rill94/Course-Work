package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DiscountBean
{
    private int discountId;
    private String name;
    private int discountSize;
    private String discountSite;

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscountSize() {
        return discountSize;
    }

    public void setDiscountSize(int discountSize) {
        this.discountSize = discountSize;
    }

    public String getDiscountSite() {
        return discountSite;
    }

    public void setDiscountSite(String discountSite) {
        this.discountSite = discountSite;
    }
}
